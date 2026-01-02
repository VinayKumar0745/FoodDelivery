<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List, com.tap.model.Restaurant,com.tap.model.Cart,com.tap.model.CartItem" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Restaurants - FoodDelivery</title>

            <!-- Icons -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

            <!-- CSS -->
            <link rel="stylesheet" href="styles.css">
        </head>

        <body>

            <!-- ================= NAVBAR ================= -->
            <nav class="navbar">
                <div class="container nav-container">
                    <a href="restaurant" class="logo">FoodDelivery</a>

                    <div class="nav-links">
                        <a href="restaurant" class="active">Restaurants</a>
                        <a href="offers.html">Offers</a>
                        <a href="help.html">Help</a>
                    </div>
                    <%
            	int cartCount=0;
            	Cart cart = (Cart)session.getAttribute("cart");
            	if(cart!=null){
            		for(CartItem item : cart.getItems().values() ){
                		cartCount+=item.getQuantity();
                	}
            	}
            
            %>
                    <div class="nav-icons">
                        <a href="cart" class="cart-icon">
                            <i class="fas fa-shopping-cart"></i>
                            <span class="cart-count"><%= cartCount %></span>
                        </a>
                        <a href="profile.jsp"><i class="fas fa-user"></i></a>
                    </div>
                </div>
            </nav>

            <!-- ================= PAGE HEADER ================= -->
            <div class="container">
                <header class="page-header">
                    <h1 class="page-title">Top Restaurants</h1>
                    <p class="page-subtitle">Order from the best restaurants in your city</p>
                </header>

                <!-- ================= RESTAURANT GRID ================= -->
                <div class="grid-layout">

                    <% List<Restaurant> allRestaurants =
                        (List<Restaurant>) request.getAttribute("allRestaurants");

                            if (allRestaurants != null) {
                            for (Restaurant restaurant : allRestaurants) {
                            %>

                            <!-- ===== RESTAURANT CARD ===== -->
                            <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="card">

                                <div class="card-img-container">
                                    <img src="<%= restaurant.getImageURL() %>" alt="<%= restaurant.getName() %>"
                                        class="card-img" loading="lazy">
                                </div>

                                <div class="card-content">
                                    <h3 class="card-title">
                                        <%= restaurant.getName() %>
                                    </h3>

                                    <p class="card-info">
                                        <%= restaurant.getCuisineType() %>
                                    </p>

                                    <div class="card-meta">
                                        <span class="rating-badge">
                                            <i class="fas fa-star"></i>
                                            <%= restaurant.getRating() %>
                                        </span>

                                        <span class="delivery-time">
                                            <%= restaurant.getDeliveryTime() %> mins
                                        </span>
                                    </div>

                                    <p class="restaurant-location">
                                        <i class="fas fa-location-dot"></i>
                                        <%= restaurant.getAddress() %>
                                    </p>
                                </div>
                            </a>

                            <% } } else { %>
                                <p>No restaurants found.</p>
                                <% } %>

                </div>
            </div>

            <script src="js/main.js"></script>
        </body>

        </html>