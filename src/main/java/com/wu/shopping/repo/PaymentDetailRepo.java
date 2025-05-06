package com.wu.shopping.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wu.shopping.model.PaymentDetail;

@Repository
public interface PaymentDetailRepo  extends MongoRepository <PaymentDetail, String>{
}
