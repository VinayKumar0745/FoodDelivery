package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer, CartItem> cart;

	public Cart() {
		cart = new HashMap<>();
	}

	public void addItem(CartItem item) {
		int itemId = item.getItemId();
		if (cart.containsKey(itemId)) {
			CartItem existingItem = cart.get(itemId);
			int newQuantity = existingItem.getQuantity();
			int oldQuantity = item.getQuantity();
			existingItem.setQuantity(newQuantity + oldQuantity);
		} else {
			cart.put(itemId, item);
		}
	}

	public void updateItem(int itemId, int quantity) {
		CartItem existingItem = cart.get(itemId);
		if (existingItem != null) {
            if (quantity <= 0) {
                cart.remove(itemId);
            } else {
                existingItem.setQuantity(quantity);
            }
        }
	}

	public void removeItem(int itemId) {
		cart.remove(itemId);
	}

	public Map<Integer, CartItem> getItems() {
		return cart;
	}

	public double totalPrice() {
		double totalPrice = 0.0;
		for (CartItem item : cart.values()) {
			totalPrice += item.getPrice() * item.getQuantity();
		}
		return totalPrice;
	}
	
	public void clearCart() {
		cart.clear();
	}

}
