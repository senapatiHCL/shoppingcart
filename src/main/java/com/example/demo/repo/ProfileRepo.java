/**
 * 
 */
package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

/**
 * Repository for all user profile related operations for end user/customer.
 *  details will be inserted at time of registration
 */
@Repository
public interface ProfileRepo extends MongoRepository<User, String>{

}
