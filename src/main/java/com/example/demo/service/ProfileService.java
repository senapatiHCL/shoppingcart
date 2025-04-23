/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.ProfileRepo;

/**
 * 
 */
@Service
public class ProfileService {

	@Autowired ProfileRepo profileRepo;
	
	public User getUserById(String userId) throws NoDataFoundException {
		return profileRepo.findById(userId).orElseThrow(()->new NoDataFoundException("No data found"));
	}
	
	public List<User> getAllUser() {
		return profileRepo.findAll();
	}
}
