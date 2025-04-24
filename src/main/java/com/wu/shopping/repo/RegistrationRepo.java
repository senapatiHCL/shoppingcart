package com.wu.shopping.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wu.shopping.model.User;

import java.util.Optional;

public interface RegistrationRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
