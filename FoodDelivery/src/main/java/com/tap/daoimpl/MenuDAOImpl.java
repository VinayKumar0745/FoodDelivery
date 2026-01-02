package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	private static final String INSERT_MENU =
			"INSERT INTO menu (restaurantId, itemName, description, price, rating, imageURL, cuisineType, isAvailable) VALUES (?,?,?,?,?,?,?,?)";

	private static final String GET_MENU =
			"SELECT * FROM menu WHERE menuId=?";

	private static final String GET_MENU_BY_RESTAURANT =
			"SELECT * FROM menu WHERE restaurantId=?";

	private static final String GET_ALL_MENUS =
			"SELECT * FROM menu";

	private static final String UPDATE_MENU =
			"UPDATE menu SET itemName=?, description=?, price=?, imageURL=?, cuisineType=?, isAvailable=? WHERE menuId=?";

	private static final String DELETE_MENU =
			"DELETE FROM menu WHERE menuId=?";

	private static final String GET_AVAILABLE_MENUS =
			"SELECT * FROM menu WHERE restaurantId=? AND isAvailable=?";

	@Override
	public int addMenu(Menu menu) {
		Connection con = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_MENU);
			preparedStatement.setInt(1, menu.getRestaurantId());
			preparedStatement.setString(2, menu.getItemName());
			preparedStatement.setString(3, menu.getDescription());
			preparedStatement.setDouble(4, menu.getPrice());
			preparedStatement.setDouble(5, menu.getRating());
			preparedStatement.setString(6, menu.getImageURL());
			preparedStatement.setString(7, menu.getCuisineType());
			preparedStatement.setBoolean(8, menu.isAvailable());

			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Menu getMenu(int menuId) {
		Connection con = DBConnection.getConnection();
		Menu menu = null;

		try {
			PreparedStatement preparedStatement = con.prepareStatement(GET_MENU);
			preparedStatement.setInt(1, menuId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				menu = new Menu(
						rs.getInt("menuId"),
						rs.getInt("restaurantId"),
						rs.getString("itemName"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getDouble("rating"),
						rs.getString("imageURL"),
						rs.getString("cuisineType"),
						rs.getBoolean("isAvailable")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public List<Menu> getMenuByRestaurant(int restaurantId) {
		Connection con = DBConnection.getConnection();
		List<Menu> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = con.prepareStatement(GET_MENU_BY_RESTAURANT);
			preparedStatement.setInt(1, restaurantId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Menu menu = new Menu(
						rs.getInt("menuId"),
						rs.getInt("restaurantId"),
						rs.getString("itemName"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getDouble("rating"),
						rs.getString("imageURL"),
						rs.getString("cuisineType"),
						rs.getBoolean("isAvailable")
				);
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Menu> getAllMenus() {
		Connection con = DBConnection.getConnection();
		List<Menu> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = con.prepareStatement(GET_ALL_MENUS);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("menuId");
				int rId = rs.getInt("restaurantId");
				String itemName = rs.getString("itemName");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				double rating = rs.getDouble("rating");
				String imageURL = rs.getString("imageURL");
				String cuisineType = rs.getString("cuisineType");
				boolean isAvailable = rs.getBoolean("isAvailable");
				Menu menu = new Menu(id, rId, itemName, description, price, rating, imageURL, cuisineType, isAvailable);
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateMenu(Menu menu) {
		Connection con = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = con.prepareStatement(UPDATE_MENU);
			preparedStatement.setString(1, menu.getItemName());
			preparedStatement.setString(2, menu.getDescription());
			preparedStatement.setDouble(3, menu.getPrice());
			preparedStatement.setString(4, menu.getImageURL());
			preparedStatement.setString(5, menu.getCuisineType());
			preparedStatement.setBoolean(6, menu.isAvailable());
			preparedStatement.setInt(7, menu.getMenuId());

			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteMenu(int menuId) {
		Connection con = DBConnection.getConnection();
		int res = 0;

		try {
			PreparedStatement preparedStatement = con.prepareStatement(DELETE_MENU);
			preparedStatement.setInt(1, menuId);
			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Menu> getAvailableMenus(int restaurantId) {
		Connection con = DBConnection.getConnection();
		List<Menu> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = con.prepareStatement(GET_AVAILABLE_MENUS);
			preparedStatement.setInt(1, restaurantId);
			preparedStatement.setBoolean(2, true);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("menuId");
				int rId = rs.getInt("restaurantId");
				String itemName = rs.getString("itemName");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				double rating = rs.getDouble("rating");
				String imageURL = rs.getString("imageURL");
				String cuisineType = rs.getString("cuisineType");
				boolean isAvailable = rs.getBoolean("isAvailable");
				Menu menu = new Menu(id, rId, itemName, description, price, rating, imageURL, cuisineType, isAvailable);
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
