package org.github.bt;

import lombok.extern.slf4j.Slf4j;
import org.github.bt.entity.Peer;
import org.github.bt.entity.Torrent;
import org.github.bt.tracker.Announce;

import java.io.File;
import java.util.Observable;

/**
 * bt下载客户端
 * <p>
 * 客户端整体是一个线程
 * 进行统计一些下载信息
 *
 * @author wenfs
 * @date 2020/5/1 22:59
 */
@Slf4j
public class BTClient extends Observable implements Runnable {

    private Torrent torrent;
    private File localPath;
    private Peer self;
    private Announce announce;
    private Thread clientThread;

    public BTClient(String pathname, String localPath) throws Exception {
//        if (localPath == null) {
//            throw new IllegalArgumentException("Invalid local directory!");
//        }
//        this.localPath = new File(localPath);
//        if (!this.localPath.isDirectory()) {
//            throw new IllegalArgumentException("Invalid local directory!");
//        }
//        localPath = this.localPath.getCanonicalPath();
//        if (pathname == null) {
//            throw new IllegalArgumentException("Invalid torrent directory!");
//        }
////        this.torrent = new Torrent(pathname);
//        String id = "-AZ2060-" + UUID.randomUUID().toString().split("-")[4];
//        this.self = new Peer(id, "192.168.0.101", 18888);
////        this.announce = new Announce(this.self, this.torrent.getAnnounceURIList());
    }


    public void download() {
//        if (this.clientThread == null || !this.clientThread.isAlive()) {
//            this.clientThread = new Thread(this);
//            this.clientThread.setName("bt-client(" + this.self.getId() + ")");
//            this.clientThread.start();
//        }
    }

    @Override
    public void run() {
////        torrent.init();
//        announce.start();
    }
}
