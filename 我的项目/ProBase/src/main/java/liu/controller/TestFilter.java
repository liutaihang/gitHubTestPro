package liu.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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

		
		System.out.println(request.getServletContext().getContextPath());
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println(config.getFilterName() + config.getServletContext().getContextPath());
	}
}
