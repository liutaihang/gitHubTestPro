package com.tw.liu.constructpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@ImportResource("classpath*:dwr.xml")
public class ConstructproApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConstructproApplication.class, args);
    }

}
