package com.sportyshoes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "user")
	private String user;
	@Column(name = "product")
	private String product;
	@Column(name = "category")
	private String category;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "amount")
	private double amount;
	@Column(name = "created_date")
	private Date createdDate;

	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Purchase(int id, String user, String product, String category, int quantity, double amount,
			Date createdDate) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.category = category;
		this.quantity = quantity;
		this.amount = amount;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", user=" + user + ", product=" + product + ", category=" + category
				+ ", quantity=" + quantity + ", amount=" + amount + ", createdDate=" + createdDate + "]";
	}

}
