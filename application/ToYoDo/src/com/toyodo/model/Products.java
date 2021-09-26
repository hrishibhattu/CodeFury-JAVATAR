package com.toyodo.model;

public class Products {
	private String productID;
	private String name;
	private Double price;
	private String category;

	public Products() {
		super();
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Products(String productID, String name, Double price, String category) {
		super();
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public Products(String product_id) {
		this.productID = product_id;
	}

}
