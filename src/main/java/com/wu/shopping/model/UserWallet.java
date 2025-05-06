package com.wu.shopping.model;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserWallet {

	@Id
	private String id;
	private String userid;
	private Instant issueDate;
	private Instant lastUsed;
	private double walletAmount;
	private String orderId;
}
