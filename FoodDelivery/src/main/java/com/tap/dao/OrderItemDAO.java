package com.tap.dao;

import java.util.List;
import com.tap.model.OrderItem;

public interface OrderItemDAO {

	int addOrderItem(OrderItem orderItem);

	List<OrderItem> getOrderItemsByOrder(int orderId);

	int deleteOrderItemsByOrder(int orderId);
}
