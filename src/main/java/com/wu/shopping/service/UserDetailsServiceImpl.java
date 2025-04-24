package com.wu.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.model.User;
import com.wu.shopping.repo.RegistrationRepo;

@Service
public class UserDetailsServiceImpl{

//	@Autowired
//    private RegistrationRepo userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
//        return user;
//    }
}
