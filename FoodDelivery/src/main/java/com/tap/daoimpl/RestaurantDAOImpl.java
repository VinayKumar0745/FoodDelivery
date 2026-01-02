package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static final String INSERT_RESTAURANT = "INSERT INTO restaurant(name, adminId, address, cuisineType, rating, deliveryTime, imageURL, isAvailable) VALUES (?,?,?,?,?,?,?,?)";

	private static final String GET_RESTAURANT = "SELECT * FROM restaurant WHERE restaurantId=?";

	private static final String GET_ALL = "SELECT * FROM restaurant";

	private static final String UPDATE_RESTAURANT = "UPDATE restaurant SET name=?, address=?, cuisineType=?, deliveryTime=?, imageURL=?, isAvailable=? WHERE restaurantId=?";

	private static final String DELETE_RESTAURANT = "DELETE FROM restaurant WHERE restaurantId=?";

	private static final String GET_AVAILABLE_RESTAURANTS = "SELECT * FROM restaurant WHERE isAvailable = ?";

	private static final String UPDATE_AVAILABILITY = "UPDATE restaurant SET isAvailable = ? WHERE restaurantId = ?";

	@Override
	public int addRestaurant(Restaurant r) {
		Connection con = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_RESTAURANT);
			preparedStatement.setString(1, r.getName());
			preparedStatement.setInt(2, r.getAdminId());
			preparedStatement.setString(3, r.getAddress());
			preparedStatement.setString(4, r.getCuisineType());
			preparedStatement.setDouble(5, r.getRating());
			preparedStatement.setInt(6, r.getDeliveryTime());
			preparedStatement.setString(7, r.getImageURL());
			preparedStatement.setBoolean(8, r.isAvailable());

			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Connection con = DBConnection.getConnection();
		Restaurant r = null;

		try {
			PreparedStatement preparedStatement = con.prepareStatement(GET_RESTAURANT);
			preparedStatement.setInt(1, restaurantId);
			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				int id = res.getInt("restaurantId");
				String name = res.getString("name");
				int adminId = res.getInt("adminId");
				String address = res.getString("address");
				String cuisineType = res.getString("cuisineType");
				double rating = res.getDouble("rating");
				int deliveryTime = res.getInt("deliveryTime");
				String imageURL = res.getString("imageURL");
				boolean isAvailable = res.getBoolean("isAvailable");

				r = new Restaurant(id, name, adminId, address, cuisineType, rating, deliveryTime, imageURL,
						isAvailable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		Connection con = DBConnection.getConnection();
		List<Restaurant> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = con.prepareStatement(GET_ALL);
			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				int id = res.getInt("restaurantId");
				String name = res.getString("name");
				int adminId = res.getInt("adminId");
				String address = res.getString("address");
				String cuisineType = res.getString("cuisineType");
				double rating = res.getDouble("rating");
				int deliveryTime = res.getInt("deliveryTime");
				String imageURL = res.getString("imageURL");
				boolean isAvailable = res.getBoolean("isAvailable");

				Restaurant r = new Restaurant(id, name, adminId, address, cuisineType, rating, deliveryTime, imageURL,
						isAvailable);
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateRestaurant(Restaurant r) {
		Connection con = DBConnection.getConnection();
		int res = 0;	

		try {
			PreparedStatement preparedStatement = con.prepareStatement(UPDATE_RESTAURANT);
			preparedStatement.setString(1, r.getName());
			preparedStatement.setString(2, r.getAddress());
			preparedStatement.setString(3, r.getCuisineType());
			preparedStatement.setInt(4, r.getDeliveryTime());
			preparedStatement.setString(5, r.getImageURL());
			preparedStatement.setBoolean(6, r.isAvailable());
			preparedStatement.setInt(7, r.getRestaurantId());

			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteRestaurant(int restaurantId) {
		Connection con = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = con.prepareStatement(DELETE_RESTAURANT);
			preparedStatement.setInt(1, restaurantId);
			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Restaurant> getAvailableRestaurants() {
		Connection connection = DBConnection.getConnection();
		ArrayList<Restaurant> restaurantList = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_AVAILABLE_RESTAURANTS);

			preparedStatement.setBoolean(1, true);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("restaurantId");
				String name = rs.getString("name");
				int adminId = rs.getInt("adminId");
				String address = rs.getString("address");
				String cuisineType = rs.getString("cuisineType");
				double rating = rs.getDouble("rating");
				int deliveryTime = rs.getInt("deliveryTime");
				String imageURL = rs.getString("imageURL");
				boolean isAvailable = rs.getBoolean("isAvailable");

				Restaurant restaurant = new Restaurant(id, name, adminId, address, cuisineType, rating, deliveryTime, imageURL,
						isAvailable);

				restaurantList.add(restaurant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantList;
	}

	@Override
	public int updateAvailability(int restaurantId, boolean isAvailable) {
		Connection connection = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AVAILABILITY);

			preparedStatement.setBoolean(1, isAvailable);
			preparedStatement.setInt(2, restaurantId);

			res = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
}
