package com.wu.shopping.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

	private List<Map<String,Object>> cartProductList;
	private int productCount;
	private double productAmount;
	private double deliveryCharge;
	private double tax;
	private double totalAmount;
	
}
