<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image:  linear-gradient(45deg, #8b5aed 0%, #78ebfc 100%);
            font-family: Arial, sans-serif;
        }
        .login-container {
            background: white;
            padding: 50px;
            text-align: center;
            width: 300px;
            border-radius: 35px;
            box-shadow: 0 45px 63px rgba(0, 0, 0, 0.26);
        }
        .login-container h2 {
            margin-bottom: 15px;
        }
        .input-group {
            margin-bottom: 15px;
        }
         .input-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s, box-shadow 0.3s;
        }
        .input-group input:hover, .input-group input:focus {
            border-color: #8b5aed;
            box-shadow: 0px 0px 8px rgba(139, 90, 237, 0.5);
            outline: none;
        }
        .login-btn {
            width: 100%;
            padding: 10px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .login-btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
	       <%-- This goes before or inside the form --%>
				<% String error = (String) request.getAttribute("error"); %>
				<% if (error != null) { %>
				    <div style="color: red;"><%= error %></div>
				<% } %>

        <form action="login" method="POST">
            <div class="input-group">
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div class="input-group">
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <button type="submit" class="login-btn">Login</button>
            <br>
            <br>
          <center> <a href="register" > New user? </a> </center>
        </form>
    </div>
</body>
</html>
