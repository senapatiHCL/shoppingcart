/**
 * 
 */
package com.wu.shopping.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Document //(collation = "tablename")--> to customize table/collection name, other than POJO calss name
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	@Id
	private String productId;
	@NotBlank(message = "title is mandatory")
    private String title;
	@NotBlank(message = "price is mandatory")
    private float price;
	@NotBlank(message = "description is mandatory")
    private String description;
    private String images;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private int qunatity;
    @Valid
    private Category category;
    
     
}
