package com.wu.shopping.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wu.shopping.model.CartProduct;

public interface CartProductRepo  extends MongoRepository<CartProduct, String>{

	 List<CartProduct> findAllByUserid(String userid);
	 
	 CartProduct findByUseridAndProductProductId(String userid,String productId);
	 
	 void deleteByProductProductId(String productId);
	 
	 void deleteByUseridAndProductProductId(String userid,String productId);
	 
}
