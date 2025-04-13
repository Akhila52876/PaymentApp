<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.akhila.paymentapp.entities.BankAccountsEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Send Money</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
        }
        .form-container {
            max-width: 400px;
            margin: auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 16px 0;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Send Money</h2>

 <form action="sendmoney" method="post">
     <input type="hidden" name="senderUsername" value="${sessionScope.username}" />
     <input type="hidden" name="receiverUsername" value="${sessionScope.username}" />
     
		   <!-- Sender Account Selection -->
		<label for="senderAccount">Sender Account:</label>
		<select name="senderAccountNumber" required>
		    <c:forEach var="acc" items="${accounts}">
		        <option value="${acc.bankAccountNo}">
		            ${acc.bankAccountNo} - ${acc.bankName}
		        </option>
		    </c:forEach>
		</select>
		
		<br><br>
		
		<!-- Receiver Account Selection -->
		<label for="receiverAccount">Receiver Account:</label>
		<select name="receiverAccountNumber" required>
		    <c:forEach var="acc" items="${allAccounts}">
		        <option value="${acc.bankAccountNo}">
		            ${acc.bankAccountNo} - ${acc.bankName}
		        </option>
		    </c:forEach>
		</select>

    <br><br>

    <!-- Destination Type -->
    
    <label>Transfer Type:</label>
    <select name="destinationType" required>
        <option value="BANK">Bank to Bank</option>
        <option value="BANK_TO_WALLET">Bank to Wallet</option>
        <option value="WALLET_TO_BANK">Wallet to Bank</option>
    </select>

    <br><br>

    <!-- Amount -->
    <label for="amount">Amount:</label>
    <input type="number" step="0.01" name="amount" required/>
    <br><br>

    <input type="submit" value="Send Money"/>
</form>

</div>

    <!-- Back to Dashboard Button -->
    <div style="text-align: center; margin-top: 20px;">
    <a href="/dashboard" style="text-decoration: none; padding: 10px 20px; background-color: #007bff; color: white; border-radius: 5px;">Back to Dashboard</a>
    
</body>
</html>
