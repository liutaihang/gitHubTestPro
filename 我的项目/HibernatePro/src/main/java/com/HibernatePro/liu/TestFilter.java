package com.HibernatePro.liu;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestFilter implements Filter {

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		System.out.println(paramFilterConfig.getFilterName() + "init------TestFilter");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain Chain)
			throws IOException, ServletException {
		System.out.println(request.getServletContext().getContextPath() + "doFilter--" + request.getLocalAddr());
		Chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destroy-------TestFilter");
	}

}
