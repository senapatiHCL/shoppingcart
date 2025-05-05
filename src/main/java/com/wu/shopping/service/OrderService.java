package com.wu.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

import com.wu.shopping.model.OrderDetail;
import com.wu.shopping.repo.OrderDetailRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderDetailRepo OrderDetailRepo;
	
	@Autowired
	private ProductService productService;
	
	
}
