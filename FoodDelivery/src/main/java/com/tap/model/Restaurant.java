package com.tap.model;

public class Restaurant {
	private int restaurantId;
	private String name;
	private int adminId;
	private String address;
	private String cuisineType;
	private double rating;
	private int deliveryTime;
	private String imageURL;
	private boolean isAvailable;

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Restaurant(int restaurantId, String name, int adminId, String address, String cuisineType, double rating,
			int deliveryTime, String imageURL, boolean isAvailable) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.adminId = adminId;
		this.address = address;
		this.cuisineType = cuisineType;
		this.rating = rating;
		this.deliveryTime = deliveryTime;
		this.imageURL = imageURL;
		this.isAvailable = isAvailable;
	}

	public Restaurant() {
		// TODO Auto-generated constructor stub
	}

	public Restaurant(String name, int adminId, String address, String cuisineType, int deliveryTime, String imageURL) {
		this.name = name;
		this.adminId = adminId;
		this.address = address;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.imageURL = imageURL;
		this.rating = 0.0; // default
		this.isAvailable = true; // default
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", adminId=" + adminId + ", address="
				+ address + ", cuisineType=" + cuisineType + ", rating=" + rating + ", deliveryTime=" + deliveryTime
				+ ", imageURL=" + imageURL + ", isAvailable=" + isAvailable + "]";
	}

}
