package com.wu.shopping.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wu.shopping.model.OrderDetail;

@Repository
public interface OrderDetailRepo extends MongoRepository<OrderDetail, String>{

}
