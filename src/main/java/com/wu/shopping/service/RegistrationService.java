package com.wu.shopping.service;


import com.wu.shopping.dto.UpdatePasswordDto;
import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.dto.UserDTOUS;
import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.exception.PasswordUpdateNotAllowedException;
import com.wu.shopping.exception.SomeThingWentWrongException;
import com.wu.shopping.model.Address;
import com.wu.shopping.model.User;
import com.wu.shopping.model.UserWallet;
import com.wu.shopping.repo.RegistrationRepo;
import com.wu.shopping.repo.WalletRepo;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepo registrationRepo;
    
    @Autowired
    WalletRepo walletRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Value("${default.walletBalance}")
	private double walletBalance;
    Logger logger = LoggerFactory.getLogger(OrderDetailService.class);
    public User registerUser(UserDTO user) {
    	User registeredUser=mapUserDtoTOUser(user);
    	registeredUser.setPassword(hashPassword(user.getPassword()));
    	registeredUser= registrationRepo.save(registeredUser);
    	createUserWallet(registeredUser);
    	    	return registeredUser;
    }
    
    public User registerUsUser(UserDTOUS user) {
    	User registeredUser=mapUserUSDtoTOUser(user);
    	registeredUser.setPassword(hashPassword(user.getPassword()));
    	registeredUser= registrationRepo.save(registeredUser);
        createUserWallet(registeredUser);
        return registeredUser;
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
    
    public void setNewPassword(UpdatePasswordDto updatePasswordDto) {
    	User dbuser=findUserById(updatePasswordDto.getId());
    	if(! updatePasswordDto.getNewpassword().equals(updatePasswordDto.getConfirmPassword())) {
    		throw new SomeThingWentWrongException("Error.PasswordMismatch");
    	}if(updatePasswordDto.getNewpassword().equals(updatePasswordDto.getOldpassword())) {
    		throw new SomeThingWentWrongException("Error.OldNewPasswordSame");
    	}if(! passwordEncoder.matches(updatePasswordDto.getOldpassword(), dbuser.getPassword())) {
    		throw new SomeThingWentWrongException("Error.WrongPassword");
    	}
    	dbuser.setPassword(hashPassword(updatePasswordDto.getNewpassword()));
    	 registrationRepo.save(dbuser);
    	
    }
    
    private String hashPassword(String password) {
    	return passwordEncoder.encode(password.trim());
    }
    
void createUserWallet(User user) {
	logger.info("--- | createing user wallet with default balance | --");
	Instant now = Instant.now();
	Random rnd = new Random();
    int number = rnd.nextInt(999999);
	UserWallet wallet=new UserWallet();
	wallet.setId("ZA"+number);
	wallet.setIssueDate(now);
	wallet.setUserid(user.getId());
	wallet.setWalletAmount(walletBalance);
	walletRepo.save(wallet);
	logger.info("--- | wallet with default balance created| --");
}

   }
