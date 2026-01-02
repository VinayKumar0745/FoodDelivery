package com.tap.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.daoimpl.OrderDAOImpl;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		// If not logged in → login first
		if (user == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		// Show checkout page
		req.getRequestDispatcher("checkout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");
		if (user == null) {
			resp.sendRedirect("login.jsp");
			return;
		}
		if (cart != null && user != null && !cart.getItems().isEmpty()) {
			Order order = new Order();
			order.setUserId(user.getUserId());
			order.setRestaurantId(restaurantId);
			order.setOrderDate(new Timestamp(System.currentTimeMillis()));
			double totalAmount = 0.0;
			for (CartItem item : cart.getItems().values()) {
				totalAmount += item.getPrice() * item.getQuantity();
			}
			order.setTotalAmount(totalAmount);
			String address = req.getParameter("address");
			if (address == null || address.trim().isEmpty()) {
			    address = user.getAddress(); // take saved address
			}
			order.setAddress(address);

			order.setStatus("Pending");
			String paymentMode = req.getParameter("paymentMode");
			order.setPaymentMode(paymentMode);

			OrderDAOImpl orderDAOImpl = new OrderDAOImpl();

			int orderId = orderDAOImpl.addOrder(order);
			OrderItemDAOImpl orderItemDAOImpl = new OrderItemDAOImpl();
			for (CartItem item : cart.getItems().values()) {
				int itemId = item.getItemId();
				int quantity = item.getQuantity();
				double totalPrice = item.getTotalPrice();
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderId(orderId);
				orderItem.setMenuId(itemId);
				orderItem.setQuantity(quantity);
				orderItem.setTotalAmount(totalPrice);

				orderItemDAOImpl.addOrderItem(orderItem);

			}

			session.removeAttribute("cart");
			session.removeAttribute("oldRestaurantId");
			resp.sendRedirect("orderConfirmation.jsp");
		} else {
			resp.sendRedirect("cart.jsp");
		}
	}
}
