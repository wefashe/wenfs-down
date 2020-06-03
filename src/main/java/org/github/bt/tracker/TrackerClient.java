package org.github.bt.tracker;


import org.github.bt.entity.Peer;

import java.net.URI;

/**
 * tracker客户端
 *
 * @author wenfs
 * @date 2020/5/5 21:49
 */
public abstract class TrackerClient {

    protected Peer peer;
    protected URI trackerURI;

    public TrackerClient(Peer peer, URI trackerURI) {
        this.peer = peer;
        this.trackerURI = trackerURI;
    }

    public abstract void buildAnnounceURL();

    public enum Event {
        NONE(0),
        COMPLETED(1),
        STARTED(2),
        STOPPED(3);

        private final int id;

        Event(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name().toLowerCase();
        }

    }

}
