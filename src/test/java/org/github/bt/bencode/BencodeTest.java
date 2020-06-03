package org.github.bt.bencode;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BencodeTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void encode() throws IOException {

        //字符串
        String str = "i love coding!";

        //数字
        int i = 1234567890;

        //列表
        List<Object> list = new ArrayList<>();
        list.add("列表1");
        list.add("list 2");
        list.add(str);
        list.add(i);

        //字典
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value2");
        map.put("obj", list);
        map.put("str", str);
        map.put("i", i);

        //编码
        String encodeString = Bencode.encode(map);

        System.out.println(encodeString);


    }

    @Test
    public void decode() throws IOException {
        String s = "d1:ii1234567890e4:key16:value23:objl7:列表16:list 214:i love coding!i1234567890ee3:str14:i love coding!e";
        Map map = (Map) Bencode.decode(s);
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
    }
}