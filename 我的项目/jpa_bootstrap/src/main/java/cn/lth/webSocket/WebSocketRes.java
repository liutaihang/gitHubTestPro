package cn.lth.webSocket;

import cn.lth.util.JTimeUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketRes {
    public static Integer sucCode = 200;
    public static Integer errCode = 100;


    public WebSocketMsg getMsg(String jsonStr){
        return JSONObject.parseObject(jsonStr, WebSocketMsg.class);
    }

    public static String resp(String sendUser, Object msgContent, Integer respCode){
        JSONObject res = new JSONObject(){{
            put("sendUser", sendUser);
            put("msgContent", msgContent);
            put("timestamp", JTimeUtils.getTimestamp());
            put("respCode", respCode);
        }};
        return res.toJSONString();
    }

    public static String respSuc(String sendUser, Object msgContent){
        return resp(sendUser, msgContent, sucCode);
    }

    public static String respErr(String sendUser, Object msgContent){
        return resp(sendUser, msgContent, errCode);
    }

    public static String serverReply(Object msgContent){
        return respSuc( "server", msgContent);
    }

    public static String getReq(WebSocketSession ses, String key){
        Object o = ses.getAttributes().get(key);
        return o == null ? null : o.toString();
    }
}
