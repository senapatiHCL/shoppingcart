package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.DataBaseAccessException;
import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.ResponseBean;
import com.example.demo.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/product/")
public class ProductController {

	Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product product,BindingResult br) {
		logger.debug("inside saveProduct() begine");
			return ResponseEntity.ok(productService.saveProduct(product));
	}

	@GetMapping("getAllProductCatalog")
	public ResponseEntity<?> getAllProductCatalog() throws NoDataFoundException {
		logger.debug("inside getAllProductCatalog begine");
		List<Product> productList=productService.getAllProduct();
		if(productList!=null && !productList.isEmpty()) {
			return new ResponseEntity<>(productList,HttpStatus.OK);}
		else {
			throw new NoDataFoundException("No Product Data Found");
		}
	}
}