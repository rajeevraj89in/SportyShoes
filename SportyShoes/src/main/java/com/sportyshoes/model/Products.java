package com.sportyshoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private ProductCategory category;
	private String productName;
	private String brand;
	private double price;
	private String colour;
	private int size;
	private String image;
	@Column(length = 300)
	private String shortDescription;
	@Column(length = 1000)
	private String longDescription;
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Products(int id, ProductCategory category, String productName, String brand, double price, String colour,
			int size, String image, String shortDescription, String longDescription) {
		super();
		this.id = id;
		this.category = category;
		this.productName = productName;
		this.brand = brand;
		this.price = price;
		this.colour = colour;
		this.size = size;
		this.image = image;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductCategory getCategoryId() {
		return category;
	}
	public void setCategoryId(ProductCategory categoryId) {
		this.category = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", categoryId=" + category + ", productName=" + productName + ", brand=" + brand
				+ ", price=" + price + ", colour=" + colour + ", size=" + size + ", image=" + image
				+ ", shortDescription=" + shortDescription + ", longDescription=" + longDescription + "]";
	}
	
	

}
