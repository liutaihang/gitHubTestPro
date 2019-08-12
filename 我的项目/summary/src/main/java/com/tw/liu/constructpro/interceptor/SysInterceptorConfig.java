package com.tw.liu.constructpro.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SysInterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SysInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new SysFilter());
        bean.addUrlPatterns("/*");
        bean.setName("SysFilter");
        bean.setOrder(1);
        return bean;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
