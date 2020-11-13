package cn.lth;

import cn.lth.interceptor.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "cn.lth.dao")
public class WebJapApplication extends WebMvcConfigurationSupport {
	@Bean
	public LoginInterceptor demoInterceptor(){
		return new LoginInterceptor();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
		super.addInterceptors(registry);
	}

	@Bean
	public DefaultServletHttpRequestHandler handler(){
		return new DefaultServletHttpRequestHandler();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebJapApplication.class, args);
	}
}
