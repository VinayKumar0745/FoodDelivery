package com.tap.dao;

import java.util.List;
import com.tap.model.Restaurant;

public interface RestaurantDAO {

	int addRestaurant(Restaurant restaurant);

	Restaurant getRestaurant(int restaurantId);

	List<Restaurant> getAllRestaurants();

	List<Restaurant> getAvailableRestaurants();

	int updateRestaurant(Restaurant restaurant);

	int updateAvailability(int restaurantId, boolean isAvailable);

	int deleteRestaurant(int restaurantId);
}
