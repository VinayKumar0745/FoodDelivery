package com.tap.servlet;

import java.io.IOException;

import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateAddress")
public class UpdateAddressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String newAddress = req.getParameter("address");

        if (newAddress == null || newAddress.trim().isEmpty()) {
            resp.sendRedirect("checkout");
            return;
        }

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.updateAddress(user.getUserId(), newAddress);

        user.setAddress(newAddress);
        session.setAttribute("user", user);

        resp.sendRedirect("checkout");
    }
}
