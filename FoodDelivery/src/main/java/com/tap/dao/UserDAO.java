package com.tap.dao;
import java.util.List;

import com.tap.model.User;
public interface UserDAO {
	int addUser(User u);
	void updateLastLogin(int userId);
	User getUser(int userId);
	User getUserByEmail(String email);
	void deleteUser(int userId);
	int updateUser(User user);
	int updatePassword(int userId,String newPassword);
	List<User> getAllUsers();
}
