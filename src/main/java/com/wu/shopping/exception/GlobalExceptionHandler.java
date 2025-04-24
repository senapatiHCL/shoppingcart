package com.wu.shopping.exception;

import java.net.URI;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.wu.shopping.controller.ProductController;

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Hidden;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {
	Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@Autowired
   private MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");

            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(402), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(405), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
        }
        if (exception instanceof NoDataFoundException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406), exception.getMessage());
            errorDetail.setProperty("description", "no user found");
        }
        if (exception instanceof IllegalArgumentException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(407), exception.getMessage());
            errorDetail.setProperty("description", "Illigal arguments");
        }
        if (exception instanceof NullPointerException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(409), exception.getMessage());
            errorDetail.setProperty("description", "null Pointer");
        }
        if (exception instanceof EmailAlreadyExistException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(409), exception.getMessage());
            errorDetail.setProperty("description", "This email Already Exists");
        }
        if (exception instanceof MethodArgumentNotValidException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(408), exception.getMessage());
            Map<String, Object> errorsMap = new HashMap<>();
            ((BindException) exception).getBindingResult().getFieldErrors().forEach(error ->
          errorsMap.put(error.getField(), error.getDefaultMessage()));
            errorDetail.setProperty("description", errorsMap);
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }
System.out.println("errorDetail--"+errorDetail);
        return errorDetail;
    }
	
	
//	@ExceptionHandler(NoDataFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ProblemDetail handleNoDataFoundException(NoDataFoundException ex,WebRequest request) {
//		
//		 ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
//		//  problemDetail.setTitle("No Data Found");
//		  problemDetail.setTitle(messageSource.getMessage("Error.WU404", null, Locale.ENGLISH));
//		  problemDetail.setDetail(ex.getMessage()); 
//	      problemDetail.setInstance(URI.create("/resource/not-found")); // Set URI of the error instance
//	  //    problemDetail.setType(URI.create("https://example.com/not-found")); // 
//	      logger.info("Invalid argument: {}, Request Details: {}", ex.getMessage(), request.getDescription(false), ex);
//			
//		 return problemDetail;
//	}
//	
//	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
//        Map<String, Object> errorsMap = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//        errorsMap.put(error.getField(), error.getDefaultMessage()));
//		 ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//			  problemDetail.setTitle(messageSource.getMessage("Error.WU400", null, Locale.ENGLISH));
//		//	  problemDetail.setDetail(ex.getMessage()); 
//		      problemDetail.setInstance(URI.create("/resource/not-found")); // Set URI of the error instance
//		      problemDetail.setProperties(errorsMap);
//		      logger.info("Invalid argument: {}, Request Details: {}", ex.getMessage(), request.getDescription(false), ex);
//			 return problemDetail;
//    }
//	
//	@ExceptionHandler(value = {IllegalArgumentException.class,NullPointerException.class})
//	public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request){
//		logger.info("Invalid argument: {}, Request Details: {}", ex.getMessage(), request.getDescription(false), ex);
//		return new ResponseEntity<>("Bad Request: "+ex.getMessage(),HttpStatus.BAD_REQUEST);
//	}
}