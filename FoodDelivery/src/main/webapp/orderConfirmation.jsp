<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User" %>

<%
    User user = (User) session.getAttribute("user");

    // If user directly opens confirmation page without login
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Confirmed - FoodDelivery</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <!-- Main CSS -->
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
            <a href="restaurant" class="action-btn">
                <i class="fas fa-home"></i>
            </a>
        </div>
    </div>
</nav>

<!-- ================= CONFIRMATION ================= -->
<div class="container" style="margin-top:3rem; margin-bottom:4rem;">
    <div class="checkout-section" style="max-width:600px; margin:auto; text-align:center;">

        <!-- Success Icon -->
        <div style="font-size:4rem; color:#16a34a; margin-bottom:1rem;">
            <i class="fas fa-check-circle"></i>
        </div>

        <!-- Title -->
        <h1 style="color:#0f172a; margin-bottom:0.5rem;">
            Order Placed Successfully!
        </h1>

        <!-- Message -->
        <p style="color:#64748b; font-size:1rem; margin-bottom:2rem;">
            Thank you <strong><%= user.getUsername() %></strong> 😊  
            Your delicious food is being prepared and will be delivered soon.
        </p>

        <!-- Info Box -->
        <div style="background:#f8fafc;
                    border:1px solid #e2e8f0;
                    border-radius:12px;
                    padding:1.5rem;
                    margin-bottom:2rem;
                    text-align:left;">
            <p style="margin-bottom:0.75rem;">
                <i class="fas fa-map-marker-alt" style="color:#16a34a;"></i>
                <strong> Delivery Address:</strong><br>
                <%= user.getAddress() %>
            </p>

            <p style="margin-bottom:0;">
                <i class="fas fa-clock" style="color:#16a34a;"></i>
                Estimated Delivery Time: <strong>30 – 45 minutes</strong>
            </p>
        </div>

        <!-- Buttons -->
        <div style="display:flex; gap:1rem; justify-content:center; flex-wrap:wrap;">
            <a href="restaurant" class="btn btn-primary">
                <i class="fas fa-utensils"></i> Order More Food
            </a>

            <a href="profile.jsp" class="btn btn-outline">
                <i class="fas fa-user"></i> My Profile
            </a>
        </div>

    </div>
</div>

</body>
</html>
