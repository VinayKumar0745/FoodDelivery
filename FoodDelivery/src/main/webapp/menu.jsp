<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List, com.tap.model.Menu,com.tap.model.Restaurant,com.tap.model.Cart,com.tap.model.CartItem" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>Menu - FoodDelivery</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">

            <!-- Font Awesome -->
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
                        <a href="restaurant">Restaurants</a>
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
                <a href="restaurant" class="back-link">
                    <i class="fas fa-arrow-left"></i> Back to Restaurants
                </a>

                <!-- Restaurant Details Card (Static mockup for now, usually dynamic) -->
               <%
               Restaurant restaurant=(Restaurant)request.getAttribute("restaurant");
               %>
                <div class="restaurant-header-card">
                    <div class="rh-content">
                        <h1 class="rh-title"><%= restaurant.getName() %></h1>
                        <p class="rh-cuisine"><%= restaurant.getCuisineType() %></p>
                        <p class="rh-location"><i class="fas fa-map-marker-alt"></i> <%= restaurant.getAddress() %></p>
                    </div>
                    <div class="rh-meta">
                        <div class="rh-rating">
                            <i class="fas fa-star"></i> <%= restaurant.getRating() %>
                        </div>
                        <div class="rh-time">
                            <%= restaurant.getDeliveryTime() %> mins
                        </div>
                    </div>
                </div>

                <!-- ================= MENU GRID ================= -->
                <div class="menu-grid">

                    <% List<Menu> menuByRestaurant =
                        (List<Menu>) request.getAttribute("menuByRestaurant");

                            for (Menu menu : menuByRestaurant) {
                            %>

                            <!-- ===== MENU CARD ===== -->
                            <div class="menu-card">

                                <div class="menu-img-container">
                                    <img src="<%= menu.getImageURL() %>" alt="<%= menu.getItemName() %>"
                                        class="menu-img" loading="lazy">
                                </div>

                                <div class="menu-content">
                                    <h3 class="menu-title">
                                        <%= menu.getItemName() %>
                                    </h3>

                                    <p class="menu-desc">
                                        <%= menu.getDescription() %>
                                    </p>

                                    <!-- PRICE + RATING -->
                                    <div class="menu-footer">
                                        <span class="price">₹<%= menu.getPrice() %></span>

                                        <% if (menu.getRating()> 0) { %>
                                            <span class="rating-badge">
                                                <i class="fas fa-star"></i>
                                                <%= menu.getRating() %>
                                            </span>
                                            <% } %>
                                    </div>

                                    <!-- ADD TO CART -->
                                    <form action="cart" class="mt-1" method="post">
                                        <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                                        <input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId() %>">
                                        <input type="hidden" name="quantity" value="1">
                                        <input type="hidden" name="action" value="add">

                                        <input type="submit" class="btn btn-primary add-to-cart-btn" value="Add to Cart">
                                          
                                    </form>
                                </div>
                            </div>

                            <% } %>

                </div>
            </div>
        </body>

        </html>