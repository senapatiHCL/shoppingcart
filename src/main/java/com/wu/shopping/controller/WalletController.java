package com.wu.shopping.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.service.WalletService;

@Controller
@RequestMapping("/wallet")
public class WalletController {

	Logger logger=LoggerFactory.getLogger(WalletController.class);
	
	@Autowired
	private WalletService walletService;
	
	@GetMapping(value="fetchWalletBalanceById")
	public ResponseEntity<?> fetchWalletAmountByUserId(@RequestParam String userId) throws NoDataFoundException {
		logger.info("inside fetchWalletAmountByUserId() begine");
		 Map responseMap = new HashMap<>();
		 responseMap.put("description", walletService.fetchWalletAmountByUserId(userId));
	     responseMap.put("status", HttpStatus.OK.value());
	     return new ResponseEntity<>(responseMap,HttpStatus.OK);
	}
	
}
