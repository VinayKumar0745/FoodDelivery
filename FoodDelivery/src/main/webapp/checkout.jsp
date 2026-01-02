<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.tap.model.Cart,com.tap.model.CartItem,com.tap.model.User" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>Checkout - FoodDelivery</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="Complete your food delivery order">

            <!-- Font Awesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
            <link rel="stylesheet" href="styles.css">
        </head>

        <body>

            <% Cart cart=(Cart) session.getAttribute("cart");
            if (cart==null || cart.getItems().isEmpty()) {
                response.sendRedirect("cart"); return;
            }
            double itemTotal=0.0; 
            for (CartItem item : cart.getItems().values()) { 
            	itemTotal +=item.getPrice() * item.getQuantity(); 
            }
            double deliveryFee=40.0;
            double grandTotal=itemTotal;
            User user=(User) session.getAttribute("user"); 
            String address=(user !=null && user.getAddress() !=null) ? user.getAddress() : "No address saved" ; %>

                <!-- Navbar -->
                <nav class="navbar">
                    <div class="container nav-container">
                        <a href="restaurant" class="logo">FoodDelivery</a>

                        <div class="nav-links">
                            <a href="restaurant">Restaurants</a>
                            <a href="#">Offers</a>
                            <a href="#">Help</a>
                        </div>

                        <% int cartCount=0; if (cart !=null) { for (CartItem item : cart.getItems().values()) {
                            cartCount +=item.getQuantity(); } } %>
                            <div class="nav-icons">
                                <a href="cart" class="cart-icon" aria-label="Cart">
                                    <i class="fas fa-shopping-cart"></i>
                                    <span class="cart-count">
                                        <%= cartCount %>
                                    </span>
                                </a>

                                <a href="#" class="action-btn" aria-label="Profile">
                                    <i class="fas fa-user"></i>
                                </a>

                                <a href="index.html" class="action-btn" aria-label="Logout">
                                    <i class="fas fa-sign-out-alt"></i>
                                </a>
                            </div>
                    </div>
                </nav>

                <!-- Checkout Container -->
                <div class="container checkout-container">
                    <h1 class="page-title">Checkout</h1>

                    <div class="checkout-layout">

                        <!-- Left Column - Address & Payment -->
                        <div>
                            <!-- Delivery Address Section -->
                            <div class="checkout-section">
                                <div class="section-header">
                                    <div class="section-icon">
                                        <i class="fas fa-map-marker-alt"></i>
                                    </div>
                                    <h2 class="section-title">Delivery Address</h2>
                                </div>

                                <div class="address-display">
                                    <div class="address-label">Saved Address</div>
                                    <div class="address-text">
                                        <%= address %>
                                    </div>
                                </div>

                                <!-- Update Address Section -->
                                <details class="address-update-toggle">
                                    <summary class="update-toggle-btn">
                                        <i class="fas fa-edit"></i> Update Address
                                    </summary>

                                    <form method="post" action="updateAddress" class="address-update-form">
                                        <label for="address-input" class="form-label">
                                            <i class="fas fa-home"></i>
                                            New Address
                                        </label>
                                        <textarea id="address-input" name="address" rows="4" class="address-textarea"
                                            required
                                            placeholder="Enter your complete delivery address"><%= address %></textarea>

                                        <button type="submit" class="btn btn-secondary">
                                            <i class="fas fa-save"></i> Save Address
                                        </button>
                                    </form>
                                </details>
                            </div>

                            <!-- Payment Method Section -->
                            <div class="checkout-section">
                                <div class="section-header">
                                    <div class="section-icon">
                                        <i class="fas fa-credit-card"></i>
                                    </div>
                                    <h2 class="section-title">Payment Method</h2>
                                </div>

                                <!-- Checkout Form Starts -->
                                <form method="post" action="checkout" id="checkout-form">
                                    <div class="payment-options">
                                        <label class="payment-option">
                                            <input type="radio" name="paymentMode" value="COD" required hidden>
                                            <div class="payment-radio"></div>
                                            <div class="payment-icon">
                                                <i class="fas fa-money-bill-wave"></i>
                                            </div>
                                            <div class="payment-details">
                                                <h4>Cash on Delivery</h4>
                                                <p>Pay when you receive your order</p>
                                            </div>
                                        </label>

                                        <label class="payment-option">
                                            <input type="radio" name="paymentMode" value="CARD" hidden>
                                            <div class="payment-radio"></div>
                                            <div class="payment-icon">
                                                <i class="fas fa-credit-card"></i>
                                            </div>
                                            <div class="payment-details">
                                                <h4>Credit / Debit Card</h4>
                                                <p>Visa, Mastercard, Rupay accepted</p>
                                            </div>
                                        </label>

                                        <label class="payment-option">
                                            <input type="radio" name="paymentMode" value="UPI" hidden>
                                            <div class="payment-radio"></div>
                                            <div class="payment-icon">
                                                <i class="fab fa-google-pay"></i>
                                            </div>
                                            <div class="payment-details">
                                                <h4>UPI Payment</h4>
                                                <p>Google Pay, PhonePe, Paytm</p>
                                            </div>
                                        </label>

                                        <label class="payment-option">
                                            <input type="radio" name="paymentMode" value="WALLET" hidden>
                                            <div class="payment-radio"></div>
                                            <div class="payment-icon">
                                                <i class="fas fa-wallet"></i>
                                            </div>
                                            <div class="payment-details">
                                                <h4>Digital Wallet</h4>
                                                <p>Amazon Pay, Mobikwik, Freecharge</p>
                                            </div>
                                        </label>
                                    </div>
                            </div>
                        </div>

                        <!-- Right Column - Order Summary -->
                        <div class="order-summary">
                            <div class="section-header">
                                <div class="section-icon">
                                    <i class="fas fa-receipt"></i>
                                </div>
                                <h2 class="section-title">Order Summary</h2>
                            </div>

                            <!-- Order Items -->
                            <div class="order-items-list">
                                <% for (CartItem item : cart.getItems().values()) { %>
                                    <div class="order-item">
                                        <div class="item-info">
                                            <div class="item-name">
                                                <%= item.getItemName() %>
                                            </div>
                                            <div class="item-quantity">Qty: <%= item.getQuantity() %>
                                            </div>
                                        </div>
                                        <div class="item-price">₹<%= item.getPrice() * item.getQuantity() %>
                                        </div>
                                    </div>
                                    <% } %>
                            </div>

                            <div class="summary-divider"></div>

                            <!-- Bill Details -->
                            <div class="bill-details">
                                <div class="summary-row">
                                    <span>Item Total</span>
                                    <span>₹<%= String.format("%.2f", itemTotal) %></span>
                                </div>
                            </div>

                            <div class="summary-divider"></div>

                            <div class="summary-row total">
                                <span>Grand Total</span>
                                <span>₹<%= String.format("%.2f", grandTotal) %></span>
                            </div>

                            <!-- Place Order Button -->
                            <button type="submit" class="place-order-btn">
                                <i class="fas fa-check-circle"></i> Place Order
                            </button>

                            <div class="secure-checkout">
                                <i class="fas fa-lock"></i>
                                <span>Secure Checkout</span>
                            </div>
                        </div>
                        </form>
                        <!-- Checkout Form Ends -->

                    </div>
                </div>

        </body>

        </html>