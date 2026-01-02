package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_USER = "INSERT INTO `user` (`username`, `password`, `email`, `phoneNumber`, `address`) VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE_LAST_LOGIN = "UPDATE `user` SET `lastLoginDate` = CURRENT_TIMESTAMP WHERE `userId` = ?";

	private static final String GET_USER_BY_ID = "SELECT * FROM `user` WHERE `userId` = ?";

	private static final String GET_USER_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";

	private static final String DELETE_USER = "DELETE FROM `user` WHERE `userId` = ?";

	private static final String UPDATE_USER = "UPDATE `user` SET `username` = ?, `address` = ? WHERE `userId` = ?";

	private static final String UPDATE_PASSWORD = "UPDATE user SET password = ? WHERE userId = ?";

	private static final String GET_ALL_USERS = "SELECT * FROM user";

	private static final String UPDATE_ADDRESS = "UPDATE user SET address=? WHERE userId=?";

	// ADD USER
	@Override
	public int addUser(User u) {
		Connection connection = DBConnection.getConnection();
		int result = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);

			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getEmail());
			preparedStatement.setString(4, u.getPhoneNumber());
			preparedStatement.setString(5, u.getAddress());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// UPDATE LAST LOGIN
	@Override
	public void updateLastLogin(int userId) {
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LAST_LOGIN);

			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GET USER BY ID
	@Override
	public User getUser(int userId) {
		Connection connection = DBConnection.getConnection();
		User user = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);

			preparedStatement.setInt(1, userId);
			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				int id = res.getInt("userId");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String phoneNumber = res.getString("phoneNumber");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createdDate = res.getTimestamp("createdDate");
				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				user = new User(id, username, password, email, phoneNumber, address, role, createdDate, lastLoginDate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// GET USER BY EMAIL
	@Override
	public User getUserByEmail(String email) {
		Connection connection = DBConnection.getConnection();
		User user = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL);

			preparedStatement.setString(1, email);
			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				int id = res.getInt("userId");
				String username = res.getString("username");
				String password = res.getString("password");
				String mail = res.getString("email");
				String phoneNumber = res.getString("phoneNumber");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createdDate = res.getTimestamp("createdDate");
				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				user = new User(id, username, password, mail, phoneNumber, address, role, createdDate, lastLoginDate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// DELETE USER
	@Override
	public void deleteUser(int userId) {
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);

			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// UPDATE USER
	@Override
	public int updateUser(User user) {
		Connection connection = DBConnection.getConnection();
		int result = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getAddress());
			preparedStatement.setInt(3, user.getUserId());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// UPDATE PASSWORD
	@Override
	public int updatePassword(int userId, String newPassword) {
		Connection connection = DBConnection.getConnection();
		int result = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);

			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, userId);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// ---------------- GET ALL USERS ----------------
	@Override
	public List<User> getAllUsers() {
		Connection connection = DBConnection.getConnection();
		List<User> users = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("userId");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				String role = rs.getString("role");
				Timestamp createdDate = rs.getTimestamp("createdDate");
				Timestamp lastLoginDate = rs.getTimestamp("lastLoginDate");

				User user = new User(id, username, password, email, phoneNumber, address, role, createdDate,
						lastLoginDate);

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public int updateAddress(int userId, String address) {
		int res = 0;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE_ADDRESS);
			ps.setString(1, address);
			ps.setInt(2, userId);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
