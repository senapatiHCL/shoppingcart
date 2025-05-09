package com.wu.shopping.intercepter;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.wu.shopping.controller.ProfileController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogIntercepter implements HandlerInterceptor{

	Logger logger=LoggerFactory.getLogger(LogIntercepter.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("PreHandle: Intercepting request...");
		 UUID uuid = UUID.randomUUID();
	     request.setAttribute("start" , System.currentTimeMillis());
	     request.setAttribute("request-id", uuid );
	     logger.info( "{} - calling {}" , uuid , request.getRequestURI() );
		logger.info("Request URI: " + request.getRequestURI()+", Request Method: "+request.getMethod());
		 Map<String,String[]> parameterMap = request.getParameterMap();
		 parameterMap.forEach((k,v) -> {
			 logger.info(k + "->" + Arrays.toString(v));
			});
		// email
//		 if ("POST".equalsIgnoreCase(request.getMethod())) 
//		 {
//		String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//		System.out.println("test "+test);
//		 }
//		 Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
//	        System.out.println(s.hasNext() ? s.next() : "");
		 
		return true;
	}
	 @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
		try {
			logger.info("PostHandle: Processing request completed. "+response.getStatus());
			logger.info( "{} - response in {}ms", 
			           request.getAttribute("request-id"),  
			           System.currentTimeMillis() - (long) request.getAttribute("start") );
	    }catch (Exception e) {
	    	logger.info("PostHandle: throw Exception ... "+e.getMessage());
		}
		}

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	    	logger.info("AfterCompletion: Request processing completed: {} {}", response.getStatus(), request.getRequestURI());
	    	logger.info( "{} - completed in {}ms", 
	    	           request.getAttribute("request-id"),  
	    	           System.currentTimeMillis() - (long) request.getAttribute("start") );
	    	if (ex != null) {
	        // Logs any exception
	            logger.info("Exception: ", ex); 
	        }
	    	
	    }
}
