package liu.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnLineCountListener implements HttpSessionListener, ServletContextListener, ServletContextAttributeListener{

	/**
	 * session创建时激发
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext context = session.getServletContext();
		System.out.println(context.getContextPath());
	}

	/**
	 * session销毁时激发
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
	}

	/**
	 * context删除时激发  
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	/**
	 * context删除时激发  
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	
}
