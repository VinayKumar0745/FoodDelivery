package com.tap.dao;

import java.util.List;

import src.main.java.com.tap.model.Menu;

public interface MenuDAO {
	
	int addMenu(Menu menu);

	Menu getMenu(int menuId);

	List<Menu> getMenuByRestaurant(int restaurantId);

	List<Menu> getAllMenus();

	int updateMenu(Menu menu);

	int deleteMenu(int menuId);

	List<Menu> getAvailableMenus(int restaurantId);
}
