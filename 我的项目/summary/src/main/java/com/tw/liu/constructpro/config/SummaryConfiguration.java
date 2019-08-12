package com.tw.liu.constructpro.config;

import com.tw.liu.constructpro.dwr.DwrScriptSessionManagerUtils;
import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SummaryConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        DwrSpringServlet dwr = new DwrSpringServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dwr, "/dwr/*");
        //设置成true使DWR能够debug和进入测试页面。
        registrationBean.addInitParameter("debug", "true");
        //pollAndCometEnabled 设置成true能增加服务器的加载能力，尽管DWR有保护服务器过载的机制。
        registrationBean.addInitParameter("pollAndCometEnabled", "true");

        registrationBean.addInitParameter("activeReverseAjaxEnabled", "true");
        registrationBean.addInitParameter("maxWaitAfterWrite", "60");
        return registrationBean;
    }
}
