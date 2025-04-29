package com.wu.shopping.service;


import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.dto.UserDTOUS;
import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.exception.PasswordUpdateNotAllowedException;
import com.wu.shopping.model.Address;
import com.wu.shopping.model.User;
import com.wu.shopping.repo.RegistrationRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepo registrationRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerUser(UserDTO user) {
    	User registeredUser=mapUserDtoTOUser(user);
    	registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return registrationRepo.save(registeredUser);
    }
    
    public User registerUsUser(UserDTOUS user) {
    	User registeredUser=mapUserUSDtoTOUser(user);
    	registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return registrationRepo.save(registeredUser);
    }
    
    public User mapUserDtoTOUser(UserDTO user) {
    	User registeringUser=new User();
    	registeringUser.setEmail(user.getEmail());
    	registeringUser.setFirstName(user.getFirstName());
    	registeringUser.setLastName(user.getLastName());
    	registeringUser.setMiddleName(user.getMiddleName());
    	registeringUser.setPassword(user.getPassword());
    	registeringUser.setPhoneNumber(user.getPhoneNumber());
    	Address add= new Address();
    	add.setCity(user.getAddress().getCity());
    	add.setCountry("INDIA");
    	add.setHouseNumber(user.getAddress().getHouseNumber());
    	add.setState(user.getAddress().getState());
    	add.setStreet(user.getAddress().getStreet());
    	add.setZipCode(user.getAddress().getZipCode());
    	registeringUser.setAddress(add);
    	return registeringUser;
    }
    
    public User mapUserUSDtoTOUser(UserDTOUS user) {
    	User registeringUser=new User();
    	registeringUser.setEmail(user.getEmail());
    	registeringUser.setFirstName(user.getFirstName());
    	registeringUser.setLastName(user.getLastName());
    	registeringUser.setMiddleName(user.getMiddleName());
    	registeringUser.setPassword(user.getPassword());
    	registeringUser.setPhoneNumber(user.getPhoneNumber());
    	Address add= new Address();
    	add.setCity(user.getAddress().getCity());
    	add.setCountry("US");
    	add.setHouseNumber(user.getAddress().getHouseNumber());
    	add.setState(user.getAddress().getState());
    	add.setStreet(user.getAddress().getStreet());
    	add.setZipCode(user.getAddress().getZipCode());
    	registeringUser.setAddress(add);
    	return registeringUser;
    }
    
    public User findUserById(String userId) throws NoDataFoundException {
        return registrationRepo.findById(userId).orElseThrow(()-> new NoDataFoundException("No Data Found"));
    }
    
    public User findUserByEmail(String email) throws NoDataFoundException {
        return registrationRepo.findByEmail(email).orElseThrow(()-> new NoDataFoundException("No Data Found"));
     //   user.setPassword(passwordEncoder.matches(email, email));
      //  System.out.println("check--"+passwordEncoder.matches("mukmuk1234", user.getPassword()));
    }
    
    public boolean existsByEmail(String email) throws NoDataFoundException {
        return registrationRepo.existsByEmail(email);
    }
     
    public User updateUser(UserDTO user) {
    	User dbuser=findUserById(user.getId());
    //	User dbuser=findUserByEmail(user.getEmail()); // if not present will throw exception in service layer
      if(! dbuser.getPassword().equalsIgnoreCase(user.getPassword())) {
    	  throw new PasswordUpdateNotAllowedException("Right Now Password Updation Is Not Allowed");
      } if(! dbuser.getEmail().equalsIgnoreCase(user.getEmail())) {
    	  throw new PasswordUpdateNotAllowedException("Email Updation Is Not Allowed");
      }
    	  User updatingUser=mapUserDtoTOUser(user);
    	  updatingUser.setId(dbuser.getId());
    	  updatingUser.setPassword(dbuser.getPassword());
        return registrationRepo.save(updatingUser);
    }
    
    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password) ;
    }


   }
