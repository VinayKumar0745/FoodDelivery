package com.tap.model;

public class CartItem {
	private int itemId;
	private String itemName;
	private int quantity;
	private double price;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public double getTotalPrice() {
		return price * quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CartItem(int itemId, String itemName, int quantity, double price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

}
