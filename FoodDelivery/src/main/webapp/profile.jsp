<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User, com.tap.model.Cart, com.tap.model.CartItem" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Cart cart = (Cart) session.getAttribute("cart");
    int cartCount = 0;
    if (cart != null) {
        for (CartItem item : cart.getItems().values()) {
            cartCount += item.getQuantity();
        }
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile - FoodDelivery</title>

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <!-- CSS -->
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<!-- ================= NAVBAR ================= -->
<nav class="navbar">
    <div class="container nav-container">
        <a href="restaurant" class="logo">FoodDelivery</a>

        <div class="nav-links">
            <a href="restaurant">Restaurants</a>
            <a href="#">Offers</a>
            <a href="#">Help</a>
        </div>

        <div class="nav-icons">
            <a href="cart.jsp" class="cart-icon">
                <i class="fas fa-shopping-cart"></i>
                <span class="cart-count"><%= cartCount %></span>
            </a>

            <a href="profile.jsp" class="action-btn active">
                <i class="fas fa-user"></i>
            </a>

            <a href="logout" class="action-btn">
                <i class="fas fa-sign-out-alt"></i>
            </a>
        </div>
    </div>
</nav>

<!-- ================= PROFILE ================= -->
<div class="container">
    <div class="profile-layout">

        <!-- ===== SIDEBAR ===== -->
        <aside class="profile-sidebar">
            <div class="profile-user-info">
                <div class="profile-avatar">
                    <i class="fas fa-user"></i>
                </div>
                <div class="profile-name"><%= user.getUsername() %></div>
                <div class="profile-email"><%= user.getEmail() %></div>
            </div>

            <nav class="profile-nav">
                <a href="profile.jsp" class="profile-nav-item active">
                    <i class="fas fa-user-circle"></i> Personal Info
                </a>
                <a href="orders.jsp" class="profile-nav-item">
                    <i class="fas fa-shopping-bag"></i> Orders
                </a>
                <a href="profile.jsp" class="profile-nav-item">
                    <i class="fas fa-map-marker-alt"></i> Address
                </a>
                <a href="profile.jsp" class="profile-nav-item" style="color:#ef4444;">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </nav>
        </aside>

        <!-- ===== MAIN CONTENT ===== -->
        <main class="profile-content">
            <div class="section-header">
                <h2 class="section-title">Personal Information</h2>
            </div>

            <div class="info-grid">
                <div class="info-card">
                    <div class="info-label">Full Name</div>
                    <div class="info-value"><%= user.getUsername() %></div>
                </div>

                <div class="info-card">
                    <div class="info-label">Email Address</div>
                    <div class="info-value"><%= user.getEmail() %></div>
                </div>

                <div class="info-card">
                    <div class="info-label">Phone Number</div>
                    <div class="info-value"><%= user.getPhoneNumber() %></div>
                </div>

                <div class="info-card">
                    <div class="info-label">Address</div>
                    <div class="info-value">
                        <%= user.getAddress() != null ? user.getAddress() : "No address added" %>
                    </div>
                </div>
            </div>
        </main>

    </div>
</div>

</body>
</html>
