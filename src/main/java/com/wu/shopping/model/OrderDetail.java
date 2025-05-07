package com.wu.shopping.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orderdetail")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class OrderDetail {
	
	@Id
  private String id;
  private String userid;
  private List<CartProduct> cartProductList;
  private double totalAmount;
  private Instant orderDate;
  private double tax;
  private double deliveryCharge;
  private double productAmount;
  private String status;
  private String paymentMode;
  private String paymentStatus;
  private Address shippingAddress;
}
