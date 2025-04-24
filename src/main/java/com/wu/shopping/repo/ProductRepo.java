package com.wu.shopping.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wu.shopping.model.Product;

/**
 * Repository for all product related operations
 */

@Repository
public interface ProductRepo  extends MongoRepository<Product, ObjectId>{

}
