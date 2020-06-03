package org.github.bt.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class TorrentTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void torrentTest() throws JsonProcessingException {

        String pathname = this.getClass().getClassLoader().getResource("torrent/1.torrent").getPath();
        Torrent torrent = new Torrent(pathname);
        String json = mapper.writeValueAsString(torrent);
        System.out.println(json);

    }

}