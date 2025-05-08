package com.wu.shopping.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wu.shopping.model.CartProduct;
import com.wu.shopping.model.OrderDetail;

@Repository
public interface OrderDetailRepo extends MongoRepository<OrderDetail, String>{

	Optional<OrderDetail> findByIdAndUserid(String id,String userid);
	
	List<OrderDetail> findByUserid(String userid);
	
	CartProduct findByCartProductListId(String id);
	
}
