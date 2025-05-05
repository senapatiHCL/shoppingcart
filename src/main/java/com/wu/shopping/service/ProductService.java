/**
 * 
 */
package com.wu.shopping.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.shopping.controller.ProfileController;
import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.model.Product;
import com.wu.shopping.repo.ProductRepo;

/**
 * 
 */
@Service
public class ProductService {
	Logger logger=LoggerFactory.getLogger(ProductService.class);
	@Autowired 
	private ProductRepo productRepo;
	
	public List<Product> getAllProduct(){
		logger.info("inside getAllProduct() ");
		return productRepo.findAll();
	}
	public Product getProductById(String productId){
		logger.info("inside getAllProduct() ");
		return productRepo.findById(productId).orElseThrow(()-> new NoDataFoundException("No Data Found"));
	}
	
	public Product saveProduct(Product product){
		logger.info("inside saveProduct() ");
		Instant now = Instant.now();
		long timestamp = now.toEpochMilli();
		product.setProductId("pr_"+timestamp);
		return productRepo.save(product);
	}
	
	public List<Product> searchProduct(String title){
		List<Product> productList= productRepo.findByTitleRegex(title);
		if(productList==null)
			productList= productRepo.findByCategoryNameRegex(title);
		return productList;
	}
//	findByCategoryNameRegex
}
