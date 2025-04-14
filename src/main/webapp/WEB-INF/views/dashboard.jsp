<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.akhila.paymentapp.entities.BankAccountsEntity" %>
<%@ page import="com.akhila.paymentapp.dtos.UserDetailsDto" %>
<%@ page import="com.akhila.paymentapp.entities.TransactionEntity" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>

    <!-- Bootstrap & FontAwesome -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        /* Attractive Gradient Background */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-image: linear-gradient(45deg, #8b5aed 0%, #78ebfc 100%);
            color: white;
        }

        /* Navbar */
        .navbar {
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(10px);
        }

        .navbar-brand {
            font-weight: bold;
            font-size: 1.5rem;
        }

        /* Dashboard Container */
        .container {
            max-width: 900px;
            margin-top: 20px;
        }

        /* Section Box */
        .section {
            background: rgba(255, 255, 255, 0.2);
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(255, 255, 255, 0.2);
            text-align: center;
        }

        /* Cards */
        .bank-section {
            display: flex;
            justify-content: space-between;
            gap: 15px;
        }

        .bank-card {
            flex: 1;
            border: 2px solid white;
            padding: 20px;
            border-radius: 10px;
            background: rgba(255, 255, 255, 0.3);
            color: white;
            text-align: center;
            transition: transform 0.3s ease-in-out;
        }

        .bank-card:hover {
            transform: scale(1.05);
            background: rgba(255, 255, 255, 0.5);
        }

        /* Buttons */
        .btn-custom {
            padding: 10px 20px;
            font-size: 1rem;
            border-radius: 5px;
            text-decoration: none;
            transition: 0.3s;
            display: inline-block;
        }

        .btn-send {
            background: #ffeb3b;
            color: #222;
            border: none;
        }

        .btn-send:hover {
            background: #ffc107;
        }

        .btn-edit {
            background: #4caf50;
            color: white;
            border: none;
        }

        .btn-edit:hover {
            background: #388e3c;
        }

        .btn-logout {
            background: red;
            color: white;
        }

        .btn-logout:hover {
            background: darkred;
        }

        /* Transaction List Style */
        .transaction-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .transaction-table th, .transaction-table td {
            padding: 10px;
            text-align: center;
            border: 1px solid white;
        }

        .transaction-table th {
            background-color: #444;
        }

        .transaction-table td {
            background-color: rgba(255, 255, 255, 0.3);
        }

        .transaction-table tr:nth-child(even) {
            background-color: rgba(255, 255, 255, 0.2);
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
            <a class="navbar-brand text-white" href="#"><i class="fas fa-wallet"></i> My Payments</a>
            <a href="logout.jsp" class="btn btn-logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
        </div>
    </nav>

    <div class="container">
        <%
        UserDetailsDto user = (UserDetailsDto) request.getAttribute("userDto");
        %>

        <!-- Welcome Message -->
        <div class="section">
            <h2>Welcome, <%= user.getFullname() %></h2>
            <p>Username: <%= user.getUsername() %></p>
            <p>Email: <%= user.getEmail() %></p>
            <p>Phone: <%= user.getPhoneNumber() %></p>
        </div>

        <%
    BankAccountsEntity primaryAccount = (BankAccountsEntity) request.getAttribute("primaryAccount");
    Double walletBalance = (Double) request.getAttribute("walletBalance");
%>

	<!-- Account Details -->
	<div class="section">
	    <h4><i class="fas fa-university"></i> Primary Bank Account: 
	        <%= primaryAccount != null ? primaryAccount.getBankAccountNo() : "N/A" %>
	    </h4>
	    
	    <p><i class="fas fa-dollar-sign"></i> Account Balance: 
	        <strong>$<%= primaryAccount != null ? primaryAccount.getCurrentbalance() : "0.00" %></strong>
	    </p>
	    
		 <p><i class="fas fa-wallet"></i> Wallet Balance: 
		 <strong>$<%= walletBalance %></strong>

		   <a href="#">[+]</a> 
	   </p>

	    <a href="sendmoney" class="btn btn-custom btn-send">
	        <i class="fas fa-paper-plane"></i> Send Money
	    </a>
	</div>


        <%
        List<BankAccountsEntity> accounts = (List<BankAccountsEntity>) request.getAttribute("accounts");
        %>

        <div class="section bank-section">
            <%
            if (accounts != null) {
                for (BankAccountsEntity account : accounts) {
            %>
            <div class="bank-card">
                <p><strong><i class="fas fa-building"></i> <%= account.getBankName() %></strong></p>
                <p>Account No: <%= account.getBankAccountNo() %></p>
                <p>Balance: <%= account.getCurrentbalance() %></p>
                <p>IFSC Code: <%= account.getIfscCode() %></p>
                <p>Branch: <%= account.getBranchName() %></p>
                <a href="editBankAcct.jsp?accountNumber=<%= account.getBankAccountNo() %>" class="btn btn-custom btn-edit"><i class="fas fa-edit"></i> Edit</a>
            </div>
            <%
                }
            }
            %>

            <!-- Add Bank Card -->
            <div class="bank-card">
                <a href="<%= request.getContextPath() %>/bankaccount/addbankaccount" class="btn btn-custom btn-edit"><i class="fas fa-plus-circle"></i> Add Bank </a>
            </div>
        </div>

        <!-- Recent Transactions -->
        <div class="section">
            <h4><i class="fas fa-list"></i> Recent Transactions</h4>

            <!-- Transaction Table -->
            <table class="transaction-table">
                <tr>
                    <th>Sender</th>
                    <th>Receiver</th>
                    <th>Amount</th>
                    <th>Transaction Type</th>
                    <th>Nature</th>
                    <th>Date&Time</th>
                </tr>

                <%
                List<TransactionEntity> transactions = (List<TransactionEntity>) request.getAttribute("transactions");
                if (transactions != null) {
                    int count = 0;
                    for (TransactionEntity transaction : transactions) {
                        if (count == 3) break; // Only show the last 3 transactions
                %>
                <tr>
                    <td><%= transaction.getSender().getUsername() %></td>
                    <td><%= transaction.getReceiver().getUsername() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= transaction.getTransactionNature() %>
                    <td><%= transaction.getTimestamp() %></td>
                </tr>
                <%
                        count++;
                    }
                }
                %>
            </table>

            <!-- View More Button -->
		         <div class="text-center mt-3">
		    <a href="transactions" class="btn btn-lg btn-warning text-dark fw-bold">
		        <i class="fas fa-file-alt me-2"></i> View Full Transaction History
		    </a>
		</div>

        </div>

    </div>

</body>
</html>
