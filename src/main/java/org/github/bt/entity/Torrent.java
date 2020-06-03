package org.github.bt.entity;

import java.util.HashMap;

/**
 * 种子信息
 *
 * @author wenfs
 * @date 2020/5/9 20:44
 */
public class Torrent extends HashMap {

    /**
     * {@value}：Tracker服务器
     */
    public static final String ANNOUNCE = "announce";
    /**
     * {@value}：Tracker服务器
     */
    public static final String ANNOUNCE_LIST = "announce-list";
    /**
     * {@value}：文件信息
     */
    public static final String INFO = "info";
    /**
     * {@value}：注释
     */
    public static final String COMMENT = "comment";
    /**
     * {@value}：注释UTF8
     */
    public static final String COMMENT_UTF8 = "comment.utf-8";
    /**
     * {@value}：编码
     */
    public static final String ENCODING = "encoding";
    /**
     * {@value}：创建时间
     */
    public static final String CREATION_DATE = "creation date";
    /**
     * {@value}：创建者
     */
    public static final String CREATED_BY = "created by";
    /**
     * {@value}：DHT节点
     */
    public static final String NODES = "nodes";
    /**
     * {@value}：piece大小
     */
    public static final String PIECE_LENGTH = "piece length";
    /**
     * {@value}：特征码
     */
    public static final String PIECES = "pieces";
    /**
     * {@value}：名称
     */
    public static final String NAME = "name";
    /**
     * {@value}：名称UTF8
     */
    public static final String NAME_UTF8 = "name.utf-8";
    /**
     * {@value}：私有种子
     */
    public static final String PRIVATE = "private";
    /**
     * {@value}：发布者
     */
    public static final String PUBLISHER = "publisher";
    /**
     * {@value}：发布者UTF8
     */
    public static final String PUBLISHER_UTF8 = "publisher.utf-8";
    /**
     * {@value}：发布者URL
     */
    public static final String PUBLISHER_URL = "publisher-url";
    /**
     * {@value}：发布者URL UTF8
     */
    public static final String PUBLISHER_URL_UTF8 = "publisher-url.utf-8";
    /**
     * {@value}：MD5校验码
     */
    public static final String MD5SUM = "md5sum";
    /**
     * {@value}：文件HASH
     */
    public static final String FILEHASH = "filehash";
    /**
     * {@value}：ed2k信息
     */
    public static final String ED2K = "ed2k";
    /**
     * {@value}：文件大小
     */
    public static final String LENGTH = "length";
    /**
     * {@value}：文件列表
     */
    public static final String FILES = "files";
    /**
     * {@value}：路径
     */
    public static final String PATH = "path";
    /**
     * {@value}：路径UTF8
     */
    public static final String PATH_UTF8 = "path.utf-8";

}
