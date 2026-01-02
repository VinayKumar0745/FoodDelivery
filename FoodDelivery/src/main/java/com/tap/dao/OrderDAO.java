package com.tap.dao;

import java.util.List;
import com.tap.model.Order;

public interface OrderDAO {

	int addOrder(Order order);

	Order getOrder(int orderId);

	List<Order> getOrdersByUser(int userId);

	List<Order> getOrdersByRestaurant(int restaurantId);

	int updateOrderStatus(int orderId, String status);
}