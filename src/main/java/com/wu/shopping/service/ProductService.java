/**
 * 
 */
package com.wu.shopping.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.shopping.controller.ProfileController;
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
	
	public Product saveProduct(Product product){
		logger.info("inside saveProduct() ");
		return productRepo.save(product);
	}
}
