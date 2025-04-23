/**
 * 
 */
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ProfileController;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;

/**
 * 
 */
@Service
public class ProductService {
	Logger logger=LoggerFactory.getLogger(ProductService.class);
	@Autowired 
	private ProductRepo productRepo;
	
	public List<Product> getAllProduct(){
		logger.debug("inside getAllProduct() ");
		return productRepo.findAll();
	}
	
	public Product saveProduct(Product product){
		logger.debug("inside saveProduct() ");
		return productRepo.save(product);
	}
}
