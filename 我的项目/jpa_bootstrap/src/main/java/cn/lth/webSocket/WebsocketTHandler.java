package cn.lth.webSocket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Slf4j
public class WebsocketTHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> clients;
    private static final ConcurrentSkipListSet<String> userNames;
    private static ThreadLocal<String> current = new ThreadLocal<>();

    static {
        clients = new ConcurrentHashMap<>();
        userNames = new ConcurrentSkipListSet<>();
    }

    @Override //连接成功时候，会触发UI上onopen方法
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userName = session.getAttributes().get("HTTP.SESSION.ID").toString();
        log.info("client 【{}】 connect to the server success......", userName);
        clients.put(userName, session);
        userNames.add(userName);
        current.set(userName);

        broadcast(WebSocketRes.serverReply(userNames));
    }

    @Override //在UI在用js调用websocket.send()时候，会调用该方法
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        log.info("client 【{}】 : {}", session.getAttributes().get("HTTP.SESSION.ID") , msg);
        try {
            JSONObject object = JSONObject.parseObject(msg);
            String receiveUser = object.getString("receiveUser");
            if(StringUtils.isNotEmpty(receiveUser)){
                sendByuser(receiveUser, WebSocketRes.respSuc(current.get(), object.get("msgContent")));
            }else{
                sendByuser(current.get(), WebSocketRes.serverReply("用户token不能为空！"));
            }
        }catch (Exception e ){
            StringWriter w = new StringWriter();
            e.printStackTrace(new PrintWriter(w));
            log.info(w.toString());
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userName = session.getAttributes().get("HTTP.SESSION.ID").toString();
        log.info("websocket client 【{}】 connection closed......", userName);
        clients.remove(userName);
        userNames.remove(userName);

        broadcast(WebSocketRes.serverReply(userNames));
    }

    public static void sendMessage(WebSocketSession socketSession, String s){
        try {
            socketSession.sendMessage(new TextMessage(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播
     *
     * @param msg
     */
    public static void broadcast(String msg){
        clients.entrySet().forEach(item -> sendMessage(item.getValue(), msg));
    }

    public static void sendByuser(String user, String msg){
        WebSocketSession webSocketSession = clients.get(user);
        if(null != webSocketSession){
            sendMessage(webSocketSession, msg);
        }else{
            sendMessage(clients.get(current.get()), "该用户已下线！");
        }
    }
}
