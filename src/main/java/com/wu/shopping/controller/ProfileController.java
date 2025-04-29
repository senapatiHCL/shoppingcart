package com.wu.shopping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.model.User;
import com.wu.shopping.service.RegistrationService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*",allowCredentials= "true")
@RestController
@RequestMapping("/profile/")
public class ProfileController {
	
	Logger logger=LoggerFactory.getLogger(ProfileController.class);
	
	
	 @Autowired
	 private  RegistrationService registrationService;
	 
@GetMapping(value="viewProfileByEmail")
public ResponseEntity<?> findUserByEmail(@RequestParam String email) throws NoDataFoundException {
	logger.info("inside getProfileById() begine");
	 Map responseMap = new HashMap<>();
	 responseMap.put("description", registrationService.findUserByEmail(email));
     responseMap.put("status", HttpStatus.OK.value());
     return new ResponseEntity<>(responseMap,HttpStatus.OK);
}
 
@PutMapping(value="/updateProfile")
public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO user) {
  registrationService.updateUser(user);
  Map responseMap = new HashMap<>();
	 responseMap.put("description", "User Detail Updated Successfully");
responseMap.put("status", HttpStatus.OK.value());
  return new ResponseEntity<>(responseMap,HttpStatus.OK);
//	return ResponseEntity.ok(registrationService.updateUser(user));
}
}