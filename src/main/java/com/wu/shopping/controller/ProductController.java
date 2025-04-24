package com.wu.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.model.Product;
import com.wu.shopping.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/product/")
public class ProductController {

	Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value="saveProduct",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveProduct(@RequestBody Product product,BindingResult br) {
		logger.info("inside saveProduct() begine");
			return ResponseEntity.ok(productService.saveProduct(product));
	}

	@GetMapping(value="getAllProductCatalog",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllProductCatalog() throws NoDataFoundException {
		logger.info("inside getAllProductCatalog begine");
		List<Product> productList=productService.getAllProduct();
		if(productList!=null && !productList.isEmpty()) {
			return new ResponseEntity<>(productList,HttpStatus.OK);}
		else {
			throw new NoDataFoundException("No Product Data Found");
		}
	}
}