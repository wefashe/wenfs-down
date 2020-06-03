package org.github.bt.tracker.http;

import org.github.bt.entity.Peer;
import org.github.bt.tracker.TrackerClient;

import java.net.URI;

/**
 * tracker的HTTP客户端
 *
 * @author wenfs
 * @date 2020/5/5 21:55
 */
public class TrackerHttpClient extends TrackerClient {

    public TrackerHttpClient(Peer peer, URI trackerURI) {
        super(peer, trackerURI);
    }

    @Override
    public void buildAnnounceURL() {

    }
}
