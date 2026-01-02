package com.tap.servlet;

import java.io.IOException;

import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phoneNumber");
        String address = req.getParameter("address");

        User user = new User(name, password, email, phone, address);
        UserDAOImpl userDAOImpl = new UserDAOImpl();

        int res = userDAOImpl.addUser(user);

        if (res == 1) {
            // ✅ Redirect to login with success message
            resp.sendRedirect("login.jsp?success=true");
        } else {
            // ❌ Redirect back to register with error
            resp.sendRedirect("register.jsp?error=true");
        }
    }
}
