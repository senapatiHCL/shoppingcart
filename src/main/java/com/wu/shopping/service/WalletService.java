package com.wu.shopping.service;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.shopping.exception.SomeThingWentWrongException;
import com.wu.shopping.model.UserWallet;
import com.wu.shopping.repo.WalletRepo;

@Service
public class WalletService {
	   Logger logger = LoggerFactory.getLogger(WalletService.class);
	   
	@Autowired
	private WalletRepo walletRepo;
	
	public double fetchWalletAmountByUserId(String userId) {
		return walletRepo.findByUserid(userId).getWalletAmount();
	}
	
	public void deductWalletAmountByUserId(String userId,double amount,String orderId) {
		logger.info("Deducting amount from wallet for user "+userId);
		UserWallet wallet= walletRepo.findByUserid(userId);
		if(wallet.getWalletAmount()<amount) {
			throw new SomeThingWentWrongException("Error.lowWalletBalance");
		}
		Instant now = Instant.now();
		wallet.setWalletAmount(wallet.getWalletAmount()-amount);
		wallet.setLastUsed(now);
		wallet.setOrderId(orderId);
		walletRepo.save(wallet);
		logger.info("Deducted amount from wallet for user "+userId+"| of Amount  "+amount);
	}
	
	public void refundWalletAmountByUserId(String userId,double amount,String orderId) {
		logger.info("refunded amount to  wallet for user "+userId+"| started of Amount  "+amount);
		UserWallet wallet= walletRepo.findByUserid(userId);
		
		Instant now = Instant.now();
		wallet.setWalletAmount(wallet.getWalletAmount()+amount);
		wallet.setLastUsed(now);
		wallet.setOrderId(orderId);
		walletRepo.save(wallet);
		logger.info("refunded amount to  wallet for user "+userId+"| of Amount  "+amount);
	}
}
