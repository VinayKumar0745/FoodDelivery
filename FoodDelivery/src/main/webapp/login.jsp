<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <% if (session.getAttribute("user") !=null) { response.sendRedirect("restaurant"); return; } String
        error=request.getParameter("error"); %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="Sign in to your FoodDelivery account">
            <title>Login - FoodDelivery</title>
            <link rel="stylesheet" href="styles.css">
            <!-- Font Awesome for Icons -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        </head>

        <body>
            <!-- Navbar -->
            <nav class="navbar">
                <div class="container nav-container">
                    <a href="index.html" class="logo">FoodDelivery</a>

                    <div class="nav-links">
                        <a href="restaurant">Home</a>
                        <a href="#">About</a>
                        <a href="#">Contact</a>
                    </div>
                </div>
            </nav>

            <!-- Login Container -->
            <div class="container" style="margin-top: 3rem; margin-bottom: 3rem;">
                <div class="login-wrapper">
                    <div class="login-card">
                        <!-- Logo/Brand Section -->
                        <div class="login-header">
                            <div class="brand-icon">
                                <i class="fas fa-utensils"></i>
                            </div>
                            <h1>Welcome Back!</h1>
                            <p>Sign in to continue your culinary journey</p>
                        </div>

                        <!-- Error Message -->
                        <% if ("invalid".equals(error)) { %>
                            <div class="error-message">
                                <i class="fas fa-exclamation-circle"></i>
                                <span>Invalid email or password. Please try again.</span>
                            </div>
                            <% } %>

                                <!-- Login Form -->
                                <form action="login" method="post" class="login-form">
                                    <div class="form-group">
                                        <label for="email">
                                            <i class="fas fa-envelope"></i>
                                            Email Address
                                        </label>
                                        <input type="email" id="email" name="email" class="form-control"
                                            placeholder="Enter your email" required autocomplete="email">
                                    </div>

                                    <div class="form-group">
                                        <label for="password">
                                            <i class="fas fa-lock"></i>
                                            Password
                                        </label>
                                        <input type="password" id="password" name="password" class="form-control"
                                            placeholder="Enter your password" required autocomplete="current-password">
                                    </div>

                                    <div class="form-options">
                                        <label class="remember-me">
                                            <input type="checkbox" name="remember">
                                            <span>Remember me</span>
                                        </label>
                                        <a href="#" class="forgot-password">Forgot Password?</a>
                                    </div>

                                    <button type="submit" class="btn btn-primary btn-block">
                                        <i class="fas fa-sign-in-alt"></i>
                                        Sign In
                                    </button>
                                </form>

                                <!-- Divider -->
                                <div class="login-divider">
                                    <span>or</span>
                                </div>

                                <!-- Social Login -->
                                <div class="social-login">
                                    <button type="button" class="btn-social btn-google">
                                        <i class="fab fa-google"></i>
                                        Continue with Google
                                    </button>
                                    <button type="button" class="btn-social btn-facebook">
                                        <i class="fab fa-facebook-f"></i>
                                        Continue with Facebook
                                    </button>
                                </div>

                                <!-- Footer -->
                                <div class="login-footer">
                                    <p>New to FoodDelivery? <a href="register.jsp">Create an account</a></p>
                                </div>
                    </div>
                </div>
            </div>
        </body>

        </html>