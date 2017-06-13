package com.zookeeper;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookTest {
	public static void main(String[] args) throws IOException {
		ZooKeeper keeper = new ZooKeeper("localhost:2181", 10000, new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println("触发了--》" + event.getType() + "事件！");
			}
		});
		
		String str = "123123123/123";
		System.out.println(str.substring(str.indexOf("/")+1) + "\n" + str.indexOf("/"));
	}
}
