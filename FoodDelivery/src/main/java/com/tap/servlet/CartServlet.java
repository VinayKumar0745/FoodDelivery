package com.tap.servlet;

import java.io.IOException;
import java.util.HashMap;

import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Just forward to cart.jsp
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Integer oldRestaurantId = (Integer) session.getAttribute("oldRestaurantId");
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		
		if (cart == null || oldRestaurantId != restaurantId) {
			cart = new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("oldRestaurantId", restaurantId);
		}
		String action = req.getParameter("action");
		if (action.equals("add")) {
			addItemToCart(req, cart);
		} else if (action.equals("update")) {
			updateItemToCart(req, cart);
		} else if (action.equals("delete")) {
			deleteItemFromCart(req, cart);
		}
		
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		HashMap<Integer,String> itemImages= new HashMap<>();
		for (CartItem item : cart.getItems().values()) {
			Menu menu = menuDAOImpl.getMenu(item.getItemId());
			itemImages.put(item.getItemId(), menu.getImageURL());
		}
		session.setAttribute("itemImages", itemImages);
		
		
		resp.sendRedirect("cart");
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		Menu menu = menuDAOImpl.getMenu(itemId);
		String itemName = menu.getItemName();
		double price = menu.getPrice();
		CartItem cartItem = new CartItem(itemId, itemName, quantity, price);

		cart.addItem(cartItem);

	}

	private void updateItemToCart(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(itemId, quantity);
	}

	private void deleteItemFromCart(HttpServletRequest req, Cart cart) {
		// TODO Auto-generated method stub\
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId);
	}

}
