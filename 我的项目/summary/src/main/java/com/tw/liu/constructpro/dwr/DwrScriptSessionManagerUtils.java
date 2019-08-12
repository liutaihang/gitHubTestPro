package com.tw.liu.constructpro.dwr;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class DwrScriptSessionManagerUtils extends DwrServlet {

    private static final long serialVersionUID = -5066499161506341656L;

    Container container = ServerContextFactory.get().getContainer();
    ScriptSessionManager manager = container .getBean(ScriptSessionManager.class);

    public void init(final String key, final String value) throws ServletException {
        ScriptSessionListener scriptSessionListener = new ScriptSessionListener() {
            @Override
            public void sessionCreated(ScriptSessionEvent event) {
                HttpSession session = WebContextFactory.get().getSession();
                String userId = (String)session.getAttribute("userId");

                System.out.println("a ScriptSession is created!");
                event.getSession().setAttribute(key, value);
            }

            @Override
            public void sessionDestroyed(ScriptSessionEvent scriptSessionEvent) {
                System.out.println("a ScriptSession is distroyed");
            }
        };
        manager.addScriptSessionListener(scriptSessionListener);
    }
}
