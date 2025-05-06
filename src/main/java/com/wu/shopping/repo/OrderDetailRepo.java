package com.wu.shopping.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wu.shopping.model.OrderDetail;

@Repository
public interface OrderDetailRepo extends MongoRepository<OrderDetail, String>{

	Optional<OrderDetail> findByIdAndUserid(String id,String userid);
	
	Optional<OrderDetail> findByUserid(String userid);
	
}
