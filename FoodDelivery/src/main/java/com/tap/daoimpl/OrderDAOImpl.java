package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO {

	private static final String INSERT_ORDER =
			"INSERT INTO orders (userId, restaurantId, totalAmount, address, status, paymentMode) VALUES (?,?,?,?,?,?)";

	private static final String GET_ORDER =
			"SELECT * FROM orders WHERE orderId=?";

	private static final String GET_ORDERS_BY_USER =
			"SELECT * FROM orders WHERE userId=?";

	private static final String GET_ORDERS_BY_RESTAURANT =
			"SELECT * FROM orders WHERE restaurantId=?";

	private static final String UPDATE_ORDER_STATUS =
			"UPDATE orders SET status=? WHERE orderId=?";

	@Override
	public int addOrder(Order order) {
		Connection connection = DBConnection.getConnection();
		int orderId = 0;

		try {
			PreparedStatement preparedStatement =
					connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, order.getUserId());
			preparedStatement.setInt(2, order.getRestaurantId());
			preparedStatement.setDouble(3, order.getTotalAmount());
			preparedStatement.setString(4, order.getAddress());
			preparedStatement.setString(5, order.getStatus());
			preparedStatement.setString(6, order.getPaymentMode());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				orderId = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderId;
	}

	@Override
	public Order getOrder(int orderId) {
		Connection connection = DBConnection.getConnection();
		Order order = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER);
			preparedStatement.setInt(1, orderId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("orderId");
				int userId = rs.getInt("userId");
				int restaurantId = rs.getInt("restaurantId");
				Timestamp orderDate = rs.getTimestamp("orderDate");
				double totalAmount = rs.getDouble("totalAmount");
				String address = rs.getString("address");
				String status = rs.getString("status");
				String paymentMode = rs.getString("paymentMode");

				order = new Order(
						id,
						userId,
						restaurantId,
						orderDate,
						totalAmount,
						address,
						status,
						paymentMode
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> getOrdersByUser(int userId) {
		Connection connection = DBConnection.getConnection();
		List<Order> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("orderId");
				int restaurantId = rs.getInt("restaurantId");
				Timestamp orderDate = rs.getTimestamp("orderDate");
				double totalAmount = rs.getDouble("totalAmount");
				String address = rs.getString("address");
				String status = rs.getString("status");
				String paymentMode = rs.getString("paymentMode");

				Order order = new Order(
						orderId,
						userId,
						restaurantId,
						orderDate,
						totalAmount,
						address,
						status,
						paymentMode
				);
				list.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Order> getOrdersByRestaurant(int restaurantId) {
		Connection connection = DBConnection.getConnection();
		List<Order> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_RESTAURANT);
			preparedStatement.setInt(1, restaurantId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("orderId");
				int userId = rs.getInt("userId");
				Timestamp orderDate = rs.getTimestamp("orderDate");
				double totalAmount = rs.getDouble("totalAmount");
				String address = rs.getString("address");
				String status = rs.getString("status");
				String paymentMode = rs.getString("paymentMode");

				Order order = new Order(
						orderId,
						userId,
						restaurantId,
						orderDate,
						totalAmount,
						address,
						status,
						paymentMode
				);
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateOrderStatus(int orderId, String status) {
		Connection connection = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, orderId);

			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
