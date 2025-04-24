package com.wu.shopping.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Category {

	 private String id;
	    private String name;
	    private String image;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		
		@Override
		public String toString() {
			return "Category [id=" + id + ", name=" + name + ", image=" + image + "]";
		}
		public Category(String id, String name, String image) {
			super();
			this.id = id;
			this.name = name;
			this.image = image;
		}
		public Category() {
			// TODO Auto-generated constructor stub
		}

	    // Getters and Setters
	    
	}