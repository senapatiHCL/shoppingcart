package com.wu.shopping.intercepter;

import java.util.Arrays;
import java.util.Map;

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
		logger.info("Request URI: " + request.getRequestURI()+", Request Method: "+request.getMethod());
		 Map<String,String[]> parameterMap = request.getParameterMap();
		 parameterMap.forEach((k,v) -> {
			 logger.info(k + "->" + Arrays.toString(v));
			});
		return true;
	}
	 @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
		try {
			logger.info("PostHandle: Processing request completed. "+response.getStatus());
	    }catch (Exception e) {
	    	logger.info("PostHandle: throw Exception ... "+e.getMessage());
		}
		}

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	    	logger.info("AfterCompletion: Request processing completed: {} {}", response.getStatus(), request.getRequestURI());
	        if (ex != null) {
	        // Logs any exception
	            logger.info("Exception: ", ex); 
	        }
	    	
	    }
}
