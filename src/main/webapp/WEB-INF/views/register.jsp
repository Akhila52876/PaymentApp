<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>




<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: linear-gradient(45deg, #8b5aed 0%, #78ebfc 100%);
            font-family: Arial, sans-serif;
        }
        .register-container {
            background: white;
            padding: 50px;
            border-radius: 30px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 350px;
        }
        .register-container h2 {
            margin-bottom: 30px;
        }
        .input-group {
            margin-bottom: 25px;
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
        .register-btn {
            width: 100%;
            padding: 10px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .register-btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Register</h2>
     <% 
    String error = (String) request.getAttribute("error"); 
    String message = (String) request.getAttribute("message");
	%>
	<% if (error != null) { %>
	    <div style="color: red;"><%= error %></div>
	<% } else if (message != null) { %>
	    <div style="color: green;"><%= message %></div>
	<% } %>

        
        
        <form action="register" method="post">  <!-- Fixed form action -->
            <div class="input-group">
                <input type="text" name="fullname" placeholder="Full Name" required>
            </div>
            <div class="input-group">
                <input type="email" name="email" placeholder="Email" required>
            </div>
            <div class="input-group">
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div class="input-group">
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <div class="input-group">
               <input type="tel" name="phoneNumber" placeholder="phone number" pattern="[0-9]{10}" maxlength="10" required>
            </div>
            <button type="submit" class="register-btn"><b>SUBMIT</b></button>
            <br>
            <br>
            <center><a href="login">Already have an account?</a></center>
        </form>
    </div>
</body>
</html>
