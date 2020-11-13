package cn.lth.webSocket;

import lombok.Data;

@Data
public class WebSocketMsg {
    private Integer msgType;
    private String msgContent;
    private String msgTime;

}
