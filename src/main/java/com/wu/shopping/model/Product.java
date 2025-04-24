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
import lombok.Data;

/**
 * 
 */
@Document //(collation = "tablename")--> to customize table/collection name, other than POJO calss name
public class Product {
	
	@Id
    private ObjectId id;
	
	@NotBlank(message = "title is mandatory")
	@Valid
    private String title;
    private float price;
    private String description;
    private String images;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private int qunatity;
    @Embedded
    @OneToOne
    private Category category;
    
   
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
				+ ", images=" + images + "]";
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Product(ObjectId id, String title, float price, String description, String images) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.images = images;
	}



	public Product() {
		// TODO Auto-generated constructor stub
	}



	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getImages() {
		return images;
	}



	public void setImages(String images) {
		this.images = images;
	}
	   
}
