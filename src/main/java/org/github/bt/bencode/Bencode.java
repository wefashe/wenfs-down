package org.github.bt.bencode;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * bencode的编码与解码
 *
 * @author wenfs
 * @date 2020/6/2 19:47
 */
public class Bencode {
    /**
     * {@value} 字典、数字、列表结尾
     */
    public static final char CHAR_E = 'e';

    /**
     * {@value} 字典开头
     */
    public static final char CHAR_D = 'd';

    /**
     * {@value} 数字开头
     */
    public static final char CHAR_I = 'i';

    /**
     * {@value} 列表开头
     */
    public static final char CHAR_L = 'l';

    /**
     * {@value} 数字范围开始0
     */
    public static final char CHAR_0 = '0';

    /**
     * {@value} 数字范围结束9
     */
    public static final char CHAR_9 = '9';

    /**
     * {@value} 数字小数点
     */
    public static final char CHAR_DOT = '.';

    /**
     * {@value} 字符串长度内容分隔符
     */
    public static final char SEPARATOR = ':';

    private static final Charset encoding = StandardCharsets.UTF_8;

    /**
     * 编码
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static String encode(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);
        new Bencode().bencode(obj, outputStream);
        return byteArrayOutputStream.toString();
    }

    private void bencode(Object obj, DataOutputStream out) throws IOException {
        if (obj == null) {
            throw new NullPointerException("Object is null");
        }
        if (obj instanceof byte[]) {
            byte[] var = (byte[]) obj;
            out.write(Integer.toString(var.length).getBytes(encoding));
            out.write(SEPARATOR);
            out.write(var);
        } else if (obj instanceof Number) {
            String var = obj.toString();
            out.write(CHAR_I);
            out.write(var.getBytes(encoding));
            out.write(CHAR_E);
        } else if (obj instanceof String) {
            String var = (String) obj;
            bencode(var.getBytes(encoding), out);
        } else if (obj instanceof List) {
            List var = (List) obj;
            out.write(CHAR_L);
            for (Object o : var) {
                bencode(o, out);
            }
            out.write(CHAR_E);
        } else if (obj instanceof Map) {
            out.write(CHAR_D);
            Map map = (Map) obj;
            Set<String> s = map.keySet();
            List<String> l = new ArrayList<String>(s);
            Collections.sort(l);
            for (String key : l) {
                bencode(key, out);
                bencode(map.get(key), out);
            }
            out.write(CHAR_E);
        } else {
            throw new IOException("Cannot bencode type : " + obj.getClass());
        }
    }


    /**
     * 解码
     *
     * @return
     */
    public static Object decode(String s) throws IOException {
        if (s == null) {
            throw new NullPointerException("Object is null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(s.getBytes(encoding));
        DataInputStream inputStream = new DataInputStream(byteArrayInputStream);
        return new Bencode().bencode(-2, inputStream);
    }


    private Object bencode(int var, DataInputStream in) throws IOException {
        if (var == -2) {
            var = in.read();
        }
        if (var == -1) {
            throw new EOFException();
        }
        if (var == CHAR_D) {
            //字典
            TreeMap map = new TreeMap();
            while ((var = in.read()) != CHAR_E) {
                String key = new String((byte[]) bencode(var, in), encoding);
                Object value = bencode(-2, in);
                if (value instanceof byte[]) {
                    if (!"pieces".equals(key) && !"filehash".equals(key) && !"ed2k".equals(key)) {
                        value = new String((byte[]) value, encoding);
                    } else {
                        value = DigestUtils.sha1Hex((byte[]) value);
                    }
                }
                map.put(key, value);
            }
            return map;
        } else if (var == CHAR_L) {
            //集合
            List list = new ArrayList();
            while ((var = in.read()) != CHAR_E) {
                Object value = bencode(var, in);
                if (value instanceof byte[]) {
                    value = new String((byte[]) value, encoding);
                }
                list.add(value);
            }
            return list;
        } else if (var == CHAR_I) {
            //数字
            StringBuilder number = new StringBuilder();
            boolean isMoney = false;
            while ((var = in.read()) != CHAR_E) {
                if (var == CHAR_DOT) {
                    isMoney = true;
                }
                number.append((char) var);
            }
            try {
                if (isMoney) {
                    //金额
                    return new BigDecimal(number.toString());
                } else {
                    //数字
                    return new BigInteger(number.toString());
                }
            } catch (NumberFormatException var5) {
                throw new IOException("NumberFormatException", var5);
            }
        } else if (CHAR_0 <= var && var <= CHAR_9) {
            //字符串
            StringBuilder string = new StringBuilder();
            string.append((char) var);
            while ((var = in.read()) != SEPARATOR) {
                string.append((char) var);
            }
            byte[] stringBytes = new byte[Integer.parseInt(string.toString())];
            in.readFully(stringBytes);
//            return new String(stringBytes, encoding);
            return stringBytes;
        } else {
            throw new IOException("Not implemented: " + var);
        }
    }
}
