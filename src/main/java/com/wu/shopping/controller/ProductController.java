package com.wu.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.model.Product;
import com.wu.shopping.repo.ProductRepo;
import com.wu.shopping.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/public/product")
public class ProductController {

	Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepo productRepo;
	
	@PostMapping(value="saveProduct",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveProduct(@RequestBody Product product,BindingResult br) {
		logger.info("inside saveProduct() begine");
			return ResponseEntity.ok(productService.saveProduct(product));
	}

	
	@GetMapping(value="getProductById",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProductById(@RequestParam String productid) {
		logger.info("inside saveProduct() begine");
			return ResponseEntity.ok(productService.getProductById(productid));
	}
	
	
	@GetMapping(value="getAllProductCatalog" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchProduct(@RequestParam String searchKeyword) {
		logger.info("inside searchProduct() begine");
		List<Product> productList=productService.searchProduct(searchKeyword);
		Map responseMap = new HashMap<>();
        responseMap.put("description", productList);
        responseMap.put("total", productList.size());
        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap,HttpStatus.OK);
	}
}