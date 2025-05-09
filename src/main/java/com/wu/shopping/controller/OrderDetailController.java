package com.wu.shopping.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.wu.shopping.constant.WUConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.dto.PlaceOrderDto;
import com.wu.shopping.exception.SomeThingWentWrongException;
import com.wu.shopping.model.OrderDetail;
import com.wu.shopping.service.OrderDetailService;
import com.wu.shopping.service.jwt.JwtService;

import static com.wu.shopping.constant.WUConstant.*;

@CrossOrigin(origins = WUConstant.CORS_DOMAIN)
@RestController
@RequestMapping(WUConstant.ORDER_CONTROLLER_CONTEXT)
public class OrderDetailController {
	
	Logger logger = LoggerFactory.getLogger(OrderDetailController.class);
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private JwtService jwtService;
	
	
	@PostMapping(value = ORDER_PLACE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> placeorder(@RequestBody PlaceOrderDto pod) {
		logger.info("inside placeorder() begine for user "+pod.getUserid());
		if(pod.getMode().equalsIgnoreCase(CREDIT_CARD)) {
			boolean creditCardValidate = Pattern.matches("^[0-9]*$",pod.getCardNumber());
			boolean expiryValidate = Pattern.matches("^[0-9/]*$",pod.getCardNumber());
			if(pod.getCardNumber().length()!=16 || pod.getCvv().length()!=3 || pod.getExpiry().length()!=5 ||!creditCardValidate ||!expiryValidate) {
				throw new SomeThingWentWrongException(ERROR_INCORRECT_CARD_DETAIL);
			}
		}
		Map responseMap = new HashMap<>();
		responseMap.put(DESCRIPTION, ORDER_PLACED);
		responseMap.put(DETAIL, orderDetailService.placeorder(pod));
		responseMap.put(STATUS, HttpStatus.OK.value());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@GetMapping(value = ORDER_DETAIL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOrderDetail(@RequestParam String userId) {
		logger.info("inside getOrderDetail() begine for user ");
		return new ResponseEntity<>(orderDetailService.findOrderDetailByUserId(userId), HttpStatus.OK);
	}
	
	@PutMapping(value = ORDER_CANCEL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cancelOrder(@RequestParam String userId,@RequestParam String orderId) {
		logger.info("inside cancelOrder() begine for user ");
		OrderDetail oderdetl=orderDetailService.cancelOrder(orderId,userId);
		Map responseMap = new HashMap<>();
		responseMap.put(DESCRIPTION, ORDER_CANCEELED);
		responseMap.put(DETAIL, oderdetl.getId());
		responseMap.put(STATUS, HttpStatus.OK.value());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@GetMapping(value = GET_ALL_ORDER_DETAIL_FOR_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllOrderDetailForAdmin(@RequestHeader("Authorization") String authHeader) {
		logger.info("inside getOrderDetail() begine for user ");
		 final String jwt = authHeader.substring(7);
         final String userEmail = jwtService.extractUsername(jwt);
         System.out.println("userEmail "+userEmail);
         if(userEmail.contains(CONTAINS_ADMIN))
		return new ResponseEntity<>(orderDetailService.getAllOrderDetailForAdmin(), HttpStatus.OK);
         else 
        	 return new ResponseEntity<>(ACCESS_DENIED, HttpStatus.OK);
	}
	
	@GetMapping(value = UPDATE_ORDER_STATUS_BY_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateOrderStatusByAdmin(@RequestHeader ("Authorization") String authHeader,@RequestParam String OrderId,@RequestParam String status) {
		logger.info("inside updateOrderStatusByAdmin() begine for user ");
		
		 final String jwt = authHeader.substring(7);
         final String userEmail = jwtService.extractUsername(jwt);
         System.out.println("userEmail "+userEmail);
         if(userEmail.contains(CONTAINS_ADMIN))
     		return new ResponseEntity<>(orderDetailService.updateOrderStatusByAdmin(OrderId, status), HttpStatus.OK);
         else 
        	 return new ResponseEntity<>(ACCESS_DENIED, HttpStatus.OK);
	}
	
	
}
