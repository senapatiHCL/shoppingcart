package com.wu.shopping.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wu.shopping.model.UserWallet;

@Repository
public interface WalletRepo extends MongoRepository<UserWallet, String>{

	UserWallet findByUserid(String userid);
}
