package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	private static final String INSERT_ORDER_ITEM = "INSERT INTO order_item (orderId, menuId, quantity, totalAmount) VALUES (?,?,?,?)";

	private static final String GET_ORDER_ITEMS_BY_ORDER = "SELECT * FROM order_item WHERE orderId=?";

	private static final String DELETE_ORDER_ITEMS_BY_ORDER = "DELETE FROM order_item WHERE orderId=?";

	@Override
	public int addOrderItem(OrderItem orderItem) {
		Connection connection = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM);
			preparedStatement.setInt(1, orderItem.getOrderId());
			preparedStatement.setInt(2, orderItem.getMenuId());
			preparedStatement.setInt(3, orderItem.getQuantity());
			preparedStatement.setDouble(4, orderItem.getTotalAmount());

			res = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrder(int orderId) {
		Connection connection = DBConnection.getConnection();
		List<OrderItem> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS_BY_ORDER);
			preparedStatement.setInt(1, orderId);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int orderItemId = rs.getInt("orderItemId");
				int oId = rs.getInt("orderId");
				int menuId = rs.getInt("menuId");
				int quantity = rs.getInt("quantity");
				double totalAmount = rs.getDouble("totalAmount");

				OrderItem orderItem = new OrderItem(orderItemId, oId, menuId, quantity, totalAmount);

				list.add(orderItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int deleteOrderItemsByOrder(int orderId) {
		Connection connection = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEMS_BY_ORDER);

			preparedStatement.setInt(1, orderId);
			res = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
}
