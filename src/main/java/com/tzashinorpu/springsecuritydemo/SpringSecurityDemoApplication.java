package com.tzashinorpu.springsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
@MapperScan("com.tzashinorpu.springsecuritydemo.mapper")
public class SpringSecurityDemoApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityDemoApplication.class, args);
		/*RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
		handlerMapping.getHandlerMethods().forEach((RequestMappingInfo k, HandlerMethod v) -> {
			System.out.println("映射路径:" + k + "\t方法信息:" + v);
		});*/
	}
}
