<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.tap.model.Cart,com.tap.model.CartItem,java.util.Map" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Your Cart - FoodDelivery</title>
            <!-- Font Awesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

            <link rel="stylesheet" href="styles.css">
        </head>

        <body>

            <nav class="navbar">
                <div class="container nav-container">
                    <a href="restaurant" class="logo">FoodDelivery</a>

                    <div class="nav-links">
                        <a href="restaurant">Restaurants</a>
                        <a href="#">Offers</a>
                        <a href="#">Help</a>
                    </div>
                    <%
                    int cartCount=0; 
                    Cart cart=(Cart)session.getAttribute("cart"); 
                    if(cart!=null){
                    	for(CartItem item : cart.getItems().values() ){
                    		cartCount+=item.getQuantity(); 
                    	}
                    } 
                    %>
                        <div class="nav-icons">
                            <a href="cart" class="cart-icon" aria-label="Cart">
                                <i class="fas fa-shopping-cart"></i>
                                <span class="cart-count">
                                    <%= cartCount %>
                                </span>
                            </a>

                            <a href="profile.jsp" class="action-btn" aria-label="Profile">
                                <i class="fas fa-user"></i>
                            </a>

                            <a href="logout" class="action-btn" aria-label="Logout">
                                <i class="fas fa-sign-out-alt"></i>
                            </a>
                        </div>
                </div>
            </nav>

            <div class="container" style="margin-top: 2rem; padding-bottom: 4rem;">
                <h2 class="page-title">Your Cart</h2>

                <% Integer restaurantId=(Integer)session.getAttribute("oldRestaurantId");
                Map<Integer, String> itemImages = (Map<Integer, String>) session.getAttribute("itemImages");

                        if(cart!=null && !cart.getItems().isEmpty()){
                        %>

                        <!-- Filled Cart State -->
                        <div class="cart-filled-state">
                            <div class="cart-layout">
                                <!-- Items Container -->
                                <div class="cart-items-container">
                                    <% for(CartItem item:cart.getItems().values()){ %>

                                        <div class="cart-item">
                                            <div class="image-wrapper">
                                                <img src="<%= itemImages != null ? itemImages.get(item.getItemId()) : ""%>"
                                                    alt="<%= item.getItemName()%>">
                                            </div>
                                            <div class="item-details">
                                                <div class="item-left">
                                                    <h3>
                                                        <%= item.getItemName() %>
                                                    </h3>
                                                    <div class="price">₹<%= item.getPrice() %>
                                                    </div>
                                                </div>

                                                <div class="item-actions">
                                                    <div class="quantity-control">
                                                        <form action="cart" method="post" style="display: inline;">
                                                            <input type="hidden" name="itemId"
                                                                value="<%= item.getItemId() %>">
                                                            <input type="hidden" name="restaurantId"
                                                                value="<%= restaurantId %>">
                                                            <% int newQty=(item.getQuantity() <=1) ? 0 :
                                                                item.getQuantity() - 1; %>
                                                                <input type="hidden" name="quantity"
                                                                    value="<%= newQty %>">
                                                                <input type="hidden" name="action" value="update">
                                                                <button type="submit" class="qty-btn minus">
                                                                    <i class="fas fa-minus"></i>
                                                                </button>
                                                        </form>

                                                        <span class="qty-display">
                                                            <%= item.getQuantity() %>
                                                        </span>

                                                        <form action="cart" method="post" style="display: inline;">
                                                            <input type="hidden" name="itemId"
                                                                value="<%= item.getItemId() %>">
                                                            <input type="hidden" name="restaurantId"
                                                                value="<%= restaurantId %>">
                                                            <input type="hidden" name="quantity"
                                                                value="<%= item.getQuantity()+1 %>">
                                                            <input type="hidden" name="action" value="update">
                                                            <button type="submit" class="qty-btn plus">
                                                                <i class="fas fa-plus"></i>
                                                            </button>
                                                        </form>
                                                    </div>

                                                    <div class="item-total">
                                                        ₹<%= item.getPrice() * item.getQuantity() %>
                                                    </div>

                                                    <form action="cart" method="post" style="display: inline;">
                                                        <input type="hidden" name="itemId"
                                                            value="<%= item.getItemId() %>">
                                                        <input type="hidden" name="restaurantId"
                                                            value="<%= restaurantId %>">
                                                        <input type="hidden" name="action" value="delete">
                                                        <button class="delete-btn" aria-label="Remove item">
                                                            <i class="fas fa-trash"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <% } %>
                                </div>

                                <!-- Cart Summary -->
                                <div class="cart-summary">
                                    <div class="summary-header">Bill Details</div>
                                    <div class="summary-row">
                                        <span>Item Total</span>
                                        <span>₹<%= cart.totalPrice() %></span>
                                    </div>
                                
                                    <div class="summary-row total">
                                        <span>Total</span>
                                        <span class="summary-total">₹<%= String.format("%.2f", cart.totalPrice()) %></span>
                                    </div>
                                    <form action="checkout" method="get">
                                        <button type="submit" class="btn btn-primary checkout-btn">
                                            <i class="fas fa-lock"></i>
                                            Proceed to Checkout
                                        </button>
                                    </form>
                                    <div style="text-align: center; margin-top: 16px;">
                                        <a href="menu?restaurantId=<%= restaurantId %>"
                                            style="color: var(--secondary); text-decoration: none; font-weight: 600;">
                                            <i class="fas fa-plus-circle"></i> Add more items
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <% } else { %>

                            <!-- Empty Cart State -->
                            <div class="cart-empty-state">
                                <div class="cart-empty-icon">
                                    <i class="fas fa-shopping-basket"></i>
                                </div>
                                <div class="cart-empty-title">Your cart is empty</div>
                                <div class="cart-empty-subtitle">You can go to the main page to view more restaurants
                                    and add delicious items to your cart</div>
                                <a href="restaurant" class="btn btn-primary">
                                    <i class="fas fa-utensils"></i>
                                    Browse Restaurants
                                </a>
                            </div>

                            <% } %>
            </div>
        </body>

        </html>