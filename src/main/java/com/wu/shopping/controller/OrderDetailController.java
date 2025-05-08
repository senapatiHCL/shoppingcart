package com.wu.shopping.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.dto.CartProductDto;
import com.wu.shopping.dto.PlaceOrderDto;
import com.wu.shopping.exception.SomeThingWentWrongException;
import com.wu.shopping.model.CartProduct;
import com.wu.shopping.model.CartResponse;
import com.wu.shopping.model.OrderDetail;
import com.wu.shopping.model.PaymentDetail;
import com.wu.shopping.model.Product;
import com.wu.shopping.model.UserWallet;
import com.wu.shopping.repo.OrderDetailRepo;
import com.wu.shopping.repo.ProductRepo;
import com.wu.shopping.repo.WalletRepo;
import com.wu.shopping.service.CartProductService;
import com.wu.shopping.service.OrderDetailService;
import com.wu.shopping.service.jwt.JwtService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order")
public class OrderDetailController {
	
	Logger logger = LoggerFactory.getLogger(OrderDetailController.class);
	
	@Autowired
	private CartProductService cartProductService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping(value = "placeorder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> placeorder(@RequestBody PlaceOrderDto pod) {
		logger.info("inside placeorder() begine for user "+pod.getUserid());
		if(pod.getMode().equalsIgnoreCase("creditcard")) {
			boolean matcher = Pattern.matches("^[0-9-]*$",pod.getCardNumber());
			if(pod.getCardNumber().length()!=16 || pod.getCvv().length()!=3 || pod.getExpiry().length()!=5 ||!matcher) {
				throw new SomeThingWentWrongException("Error.incorrectCardDetail");
			}
		}
		Map responseMap = new HashMap<>();
		responseMap.put("description", "Order placed");
		responseMap.put("detail", orderDetailService.placeorder(pod));
		responseMap.put("status", HttpStatus.OK.value());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@GetMapping(value = "getOrderDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOrderDetail(@RequestParam String userId) {
		logger.info("inside getOrderDetail() begine for user ");
		return new ResponseEntity<>(orderDetailService.findOrderDetailByUserId(userId), HttpStatus.OK);
	}
	
	@PutMapping(value = "cancelOrder", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cancelOrder(@RequestParam String userId,@RequestParam String orderId) {
		logger.info("inside cancelOrder() begine for user ");
		OrderDetail oderdetl=orderDetailService.cancelOrder(orderId,userId);
		Map responseMap = new HashMap<>();
		responseMap.put("description", "Order cancelled");
		responseMap.put("detail", oderdetl.getId());
		responseMap.put("status", HttpStatus.OK.value());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllOrderDetailForAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllOrderDetailForAdmin(@RequestHeader("Authorization") String authHeader) {
		logger.info("inside getOrderDetail() begine for user ");
		 final String jwt = authHeader.substring(7);
         final String userEmail = jwtService.extractUsername(jwt);
         System.out.println("userEmail "+userEmail);
         if(userEmail.contains("admin"))
		return new ResponseEntity<>(orderDetailService.getAllOrderDetailForAdmin(), HttpStatus.OK);
         else 
        	 return new ResponseEntity<>("Acess Denied", HttpStatus.OK);
	}
	
	@GetMapping(value = "updateOrderStatusByAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateOrderStatusByAdmin(@RequestHeader ("Authorization") String authHeader,@RequestParam String OrderId,@RequestParam String status) {
		logger.info("inside updateOrderStatusByAdmin() begine for user ");
		
		 final String jwt = authHeader.substring(7);
         final String userEmail = jwtService.extractUsername(jwt);
         System.out.println("userEmail "+userEmail);
         if(userEmail.contains("admin"))
     		return new ResponseEntity<>(orderDetailService.updateOrderStatusByAdmin(OrderId, status), HttpStatus.OK);
         else 
        	 return new ResponseEntity<>("Acess Denied", HttpStatus.OK);
	}
	
	
}
