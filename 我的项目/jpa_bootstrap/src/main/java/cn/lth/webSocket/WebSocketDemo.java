package cn.lth.webSocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketDemo {
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketDemo> webSocketDemos = new CopyOnWriteArraySet<>();
    private Session session;


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketDemos.add(this);
        addOnlineCount();
        System.err.println("当前在线人数为：" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.err.println("客户端【" + session.getId() + "】消息 ：" + message);
        for (WebSocketDemo demo : webSocketDemos) {
            demo.sendMessage(this.session, "服务端消息：hello world");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("SYSTEM.ERROR，客户端【" + session.getId() + "】消息发送失败");
        error.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        subOnlineCount();
        System.err.println("客户端 :【" + session.getId() + "】关闭，当前在线人数【" + getOnlineCount() + "】");
    }

    public synchronized void sendMessage(Session session, String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addOnlineCount() {
        WebSocketDemo.onlineCount++;
    }

    public static int getOnlineCount() {
        return onlineCount;
    }

    public static void subOnlineCount() {
        WebSocketDemo.onlineCount--;
    }
}
