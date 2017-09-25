package com.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class ZookTest {
	public static void main(String[] args) throws IOException {
		ZooKeeper keeper = new ZooKeeper("localhost:2181", 10000, new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println("触发了--》" + event.getType() + "事件！");
			}
		});
		
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").connectionTimeoutMs(1000).
				sessionTimeoutMs(1000).canBeReadOnly(false).retryPolicy(retryPolicy).build();
		client.start();
		ZooKeeper zookeeper = null;
		try {
			zookeeper = client.getZookeeperClient().getZooKeeper();
			System.out.println(zookeeper.getState());
			
			CountDownLatch connectedLatch = new CountDownLatch(1);
	        Watcher watcher = new ConnectedWatcher(connectedLatch);
	        zookeeper.register(watcher);
	        if (States.CONNECTING == zookeeper.getState()) {
	            System.out.println("连接中");
	            try {
	                connectedLatch.await();
	            } catch (InterruptedException e) {
	                throw new IllegalStateException(e);
	            }
	        }
	        System.out.println("连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		System.out.println(keeper.getSessionTimeout());
		String str = "123123123/123";
		System.out.println(str.substring(str.indexOf("/")+1) + "\n" + str.indexOf("/"));
//		int [] nums = new int[]{1};
//		int [] s = {1};
//		int [] th = new int[3];
	}
	
	static class ConnectedWatcher implements Watcher {

        private CountDownLatch connectedLatch;

        ConnectedWatcher(CountDownLatch connectedLatch) {
            this.connectedLatch = connectedLatch;
        }

        public void process(WatchedEvent event) {
            if (event.getState() == KeeperState.SyncConnected) {
                connectedLatch.countDown();
            }
        }
    }
}
