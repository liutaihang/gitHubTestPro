package com.tw.liu.constructpro.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RemoteProxy
public class DwrReceiveInfo {

    @RemoteMethod
    public String sayHi(String hi){
        demo();
        return "hi " + hi;
    }

    public void demo(){
        ScriptBuffer script = new ScriptBuffer();
        script.appendCall("sendmessage", "后台发送消息");
        Collection<ScriptSession> targetSessions = Browser.getTargetSessions();
        targetSessions.stream().forEach(tem -> tem.addScript(script));
        System.out.println("前端方法调用！");
    }
}
