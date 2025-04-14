<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transaction History</title>
    <style>
        body {
            font-family: Arial;
        }
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #ddd;
        }
        .debit {
            color: red;
        }
        .credit {
            color: green;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Transaction History</h2>

    <c:if test="${not empty transactions}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Sender</th>
                    <th>Receiver</th>
                    <th>Amount</th>
                    <th>Type</th>
                    <th>Transaction Type</th>
                    <th>Nature</th>
                    <th>Timestamp</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="txn" items="${transactions}">
                    <tr>
                        <td>${txn.id}</td>
                        <td>${txn.sender.username}</td>
                        <td>${txn.receiver.username}</td>
                        <td>
                            <span class="${txn.transactionType == 'DEBIT' ? 'debit' : 'credit'}">
                                ₹${txn.amount}
                            </span>
                        </td>
                        <td>${txn.type}</td>
                        <td>${txn.transactionType}</td>
                        <td>${txn.transactionNature}</td>
                        <td>${txn.timestamp}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty transactions}">
        <p style="text-align:center;">No transactions found.</p>
    </c:if>

    <!-- Back to Dashboard Button -->
    <div style="text-align: center; margin-top: 20px;">
        <a href="/dashboard" style="text-decoration: none; padding: 10px 20px; background-color: #007bff; color: white; border-radius: 5px;">Back to Dashboard</a>
    </div>

</body>
</html>
