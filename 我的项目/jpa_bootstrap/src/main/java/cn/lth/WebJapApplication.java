package cn.lth;

import cn.lth.base.DemoInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class WebJapApplication extends WebMvcConfigurationSupport {
	@Bean
	public DemoInterceptor demoInterceptor(){
		return new DemoInterceptor();
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
		super.addInterceptors(registry);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebJapApplication.class, args);
	}
}
