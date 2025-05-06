package com.wu.shopping.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "CartProduct")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class CartProduct {

	  @Id
	  private String id;
	  private String userid;
	  private int quantity;
	  private Product product;
	  private String status;
}
