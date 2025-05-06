package com.wu.shopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.dto.CartProductDto;
import com.wu.shopping.model.CartProduct;
import com.wu.shopping.model.CartResponse;
import com.wu.shopping.model.Product;
import com.wu.shopping.service.CartProductService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartProductController {
	Logger logger = LoggerFactory.getLogger(CartProductController.class);
	
	@Autowired
	private CartProductService cartProductService;

	@PostMapping(value = "addToCart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addToCart(@Valid @RequestBody CartProductDto cartProductDto, BindingResult br) {
		logger.info("inside addToCart() begine");
		cartProductService.addToCart(cartProductDto);
		Map responseMap = new HashMap<>();
		responseMap.put("description", "Product Added To CART Successfully");
		responseMap.put("productcount", cartProductService.getAllCartProductByUserId(cartProductDto.getUserId()).size());
		responseMap.put("status", HttpStatus.OK.value());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping(value = "removeFromCart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeFromCart(@RequestBody CartProductDto cartProductDto, BindingResult br) {
		logger.info("inside removeFromCart() begine");
		cartProductService.removeCartItemByUseridAndProductId(cartProductDto);
		Map responseMap = new HashMap<>();
		responseMap.put("description", "Product removed Successfully from Cart");
		responseMap.put("productcount", cartProductService.getAllCartProductByUserId(cartProductDto.getUserId()).size());
		responseMap.put("status", HttpStatus.OK.value());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping(value = "viewCartDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewCartDetail(@RequestBody CartProductDto cartProductDto, BindingResult br) {
		logger.info("inside viewCartDetail() begine");
		List<CartProduct> cartList=cartProductService.getAllCartProductByUserId(cartProductDto.getUserId());
		double productAmount=cartList.stream().mapToDouble(productlist->productlist.getQuantity()*productlist.getProduct().getPrice()).sum();
		List<Map<String,Object>> mapList=new ArrayList<>();
		
		for (CartProduct cartProduct : cartList) {
			Map prodMap=new HashMap<>();
			prodMap.put("title", cartProduct.getProduct().getTitle());
			prodMap.put("price", cartProduct.getProduct().getPrice());
			prodMap.put("quantity", cartProduct.getQuantity());
			prodMap.put("productId", cartProduct.getProduct().getProductId());
			prodMap.put("image", cartProduct.getProduct().getImages());
			prodMap.put("amount", cartProduct.getProduct().getPrice()*cartProduct.getQuantity());
			mapList.add(prodMap);
		}
		CartResponse cartResponse=new CartResponse();
		
		cartResponse.setCartProductList(mapList);
		cartResponse.setDeliveryCharge(50);
		cartResponse.setProductAmount(productAmount);
		cartResponse.setProductCount(cartList.size());
	//	double tax=12/100;
		cartResponse.setTax((productAmount *12)/100);
	//	double tax=productAmount+cartResponse.getDeliveryCharge()+cartResponse.getTax();
		cartResponse.setTotalAmount(productAmount+cartResponse.getDeliveryCharge()+cartResponse.getTax());
	//	int cartTotal=cartList.stream().forEach(productlist->productlist.getQuantity()*productlist.getProduct().getPrice());
		
		return new ResponseEntity<>(cartResponse, HttpStatus.OK);
	}
}
