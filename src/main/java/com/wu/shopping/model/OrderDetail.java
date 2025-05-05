package com.wu.shopping.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "order")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class OrderDetail {
	
	@Id
  private String id;
  private String userid;
  private String status;
  private int quantity;
  private List<Product> product;
  private double totalAmount;
  private Instant orderDate;
  private double tax;
  private double deliveryCharge;

}
