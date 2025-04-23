package com.example.demo.exception;

import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Hidden;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {
	
	@Autowired
   private MessageSource messageSource;
	
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ProblemDetail handleNoDataFoundException(NoDataFoundException ex) {
		 ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		//  problemDetail.setTitle("No Data Found");
		  problemDetail.setTitle(messageSource.getMessage("api.error.user.not.found", null, Locale.ENGLISH));
		  problemDetail.setDetail(ex.getMessage()); 
	      problemDetail.setInstance(URI.create("/resource/not-found")); // Set URI of the error instance
	  //    problemDetail.setType(URI.create("https://example.com/not-found")); // 
		 return problemDetail;
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorsMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
        errorsMap.put(error.getField(), error.getDefaultMessage()));
		 ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
			  problemDetail.setTitle("Parameter Validation Fails");
		//	  problemDetail.setDetail(ex.getMessage()); 
		      problemDetail.setInstance(URI.create("/resource/not-found")); // Set URI of the error instance
		      problemDetail.setProperties(errorsMap);
			 return problemDetail;
    }
	
	@ExceptionHandler(value = {IllegalArgumentException.class,NullPointerException.class})
	public ResponseEntity<Object> handleBadRequest(Exception ex){
		return new ResponseEntity<>("Bad Request: "+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}