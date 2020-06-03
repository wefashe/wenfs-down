package org.github.bt.tracker;

import lombok.extern.slf4j.Slf4j;
import org.github.bt.entity.Peer;
import org.github.bt.tracker.http.TrackerHttpClient;
import org.github.bt.tracker.udp.TrackerUdpClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Announce类
 *
 * @author wenfs
 * @date 2020/5/7 20:37
 */
@Slf4j
public class Announce extends Thread {

    private Peer peer;
    private List<TrackerClient> trackerClientList;

    public Announce(Peer peer, Set<URI> announceURIList) {
        this.peer = peer;
        this.trackerClientList = new ArrayList<>();
        for (URI trackerURI : announceURIList) {

            String scheme = trackerURI.getScheme();
            if ("http".equals(scheme) || "https".equals(scheme)) {
                trackerClientList.add(new TrackerHttpClient(this.peer, trackerURI));
            } else if ("udp".equals(scheme)) {
                trackerClientList.add(new TrackerUdpClient(this.peer, trackerURI));
            } else {
                log.info("当前不支持协议：" + scheme + ",无法使用该地址：" + trackerURI.toString());
            }
        }
        Collections.shuffle(this.trackerClientList);
    }

    @Override
    public void run() {
//        this.setName(String.format("bt-announce( %s )", this.peer.getId()));
//        super.run();
    }
}
