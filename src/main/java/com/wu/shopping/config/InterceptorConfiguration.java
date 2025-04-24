package com.wu.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wu.shopping.intercepter.LogIntercepter;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{
	
	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(new LogIntercepter()).
	      addPathPatterns("/profile/*","/product/*","/auth/*")
          .excludePathPatterns("/api/health", "/api/docs");;
	   }
	
}
