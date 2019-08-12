package com.tw.liu.constructpro.interceptor;

import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/**", filterName = "filter")
public class SysFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(SysFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.warn("------------------------filter-init--------------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setAttribute("mypage", "测试");
        if("/".equals(((RequestFacade) servletRequest).getRequestURI())){
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect("/index");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
        long end = System.currentTimeMillis() - start;
        log.warn("------------------------filter-dofilter(" + end + ")--------------------------");
    }

    @Override
    public void destroy() {
        log.warn("------------------------filter-destroy--------------------------");
    }
}
