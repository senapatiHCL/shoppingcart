package com.wu.shopping.model;

import java.time.Instant;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PaymentDetail {
	
	@Id
private String id;
private String orderId;
private String userId;
private String paymentMode;
private Instant paymentDate;
private String paymentStatus;
private double amount;
private String crediCardNumber;
private String expiry;

}
