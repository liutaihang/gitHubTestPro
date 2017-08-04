package liu.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestFilter implements Filter{

	public void testFilter(){
		
	}

	@Override
	public void destroy() {
		System.out.println("filter销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		System.out.println(request2.getLocalName() + ":" + request2.getLocalPort() + request2.getContextPath());
//		System.out.println(session.getServletContext().getContextPath());
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println(config.getFilterName() + config.getServletContext().getContextPath());
	}
}
