package com.shop.dto;

public class CartDTO {
	private int cart_id;
	private String user_id;
	private int product_id;
	private String product_name;
	private String product_image;
	private int price;
	private int point;
	
	
	
	public CartDTO(int cart_id, String user_id, int product_id, int amount, String product_name, String product_image, int price,
			int point) {
		this.cart_id = cart_id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.amount = amount;
		this.product_name = product_name;
		this.product_image = product_image;
		this.price = price;
		this.point = point;
		
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	private int  amount;
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
}
