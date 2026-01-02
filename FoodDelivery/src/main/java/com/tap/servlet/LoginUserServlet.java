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

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.getUserByEmail(email);

        // ❌ User not found → go to register page
        if (user == null) {
            resp.sendRedirect("register.jsp");
            return;
        }

        // ❌ Invalid password → back to login with error
        if (!user.getPassword().equals(password)) {
            resp.sendRedirect("login.jsp?error=invalid");
            return;
        }

        // ✅ Successful login
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        userDAO.updateLastLogin(user.getUserId());

        resp.sendRedirect("checkout.jsp");
    }
}
