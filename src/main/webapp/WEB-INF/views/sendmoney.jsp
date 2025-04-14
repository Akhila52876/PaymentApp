<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Send Money</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 500px;
            margin: 60px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 6px;
            color: #444;
        }

        select, input[type="number"] {
            width: 100%;
            padding: 10px 12px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
        }

        select:focus, input:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0,123,255,0.3);
        }

        .hidden {
            display: none;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .btn-back {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            margin-top: 15px;
        }

        .btn-back:hover {
            background-color: #0056b3;
        }
    </style>

  <script>
    function toggleFields() {
        const type = document.getElementById("destinationType").value;

        const senderGroup = document.getElementById("senderGroup");
        const receiverGroup = document.getElementById("receiverGroup");

        const senderInput = document.getElementById("senderAccountNumber");

        const allAccountsSelect = document.getElementById("allAccountsSelect");
        const userAccountsSelect = document.getElementById("userAccountsSelect");

        // Handle sender field visibility and requirement
        if (type === "BANK" || type === "BANK_TO_WALLET") {
            senderGroup.classList.remove("hidden");
            senderInput.required = true;
        } else {
            senderGroup.classList.add("hidden");
            senderInput.required = false;
        }

        // Handle receiver field visibility
        if (type === "BANK") {
            receiverGroup.classList.remove("hidden");
            allAccountsSelect.classList.remove("hidden");
            userAccountsSelect.classList.add("hidden");

            allAccountsSelect.required = true;
            userAccountsSelect.required = false;

        } else if (type === "WALLET_TO_BANK") {
            receiverGroup.classList.remove("hidden");
            allAccountsSelect.classList.add("hidden");
            userAccountsSelect.classList.remove("hidden");

            allAccountsSelect.required = false;
            userAccountsSelect.required = true;

        } else if (type === "BANK_TO_WALLET") {
            receiverGroup.classList.add("hidden");

            allAccountsSelect.required = false;
            userAccountsSelect.required = false;
        }
    }

    window.onload = function () {
        toggleFields(); // Initialize on page load
    };
</script>

</head>

<body>
    <div class="container">
        <h2>Send Money</h2>

        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <form action="sendmoney" method="post">

            <!-- 👤 Sender Account (your accounts only) -->
            <div class="form-group" id="senderGroup">
                <label for="senderAccountNumber">Sender Account:</label>
                <select name="senderAccountNumber" id="senderAccountNumber">
                    <option disabled selected value="">-- Select Sender Account --</option>
                    <c:forEach var="account" items="${accounts}">
                        <option value="${account.bankAccountNo}">
                            ${account.bankAccountNo} - ${account.bankName}
                        </option>
                    </c:forEach>
                </select>
            </div>

        <!-- 🎯 Receiver Account -->
			<div class="form-group" id="receiverGroup">
			    <label for="receiverAccountNumber">Receiver Account:</label>
			    
			    <!-- All Accounts (for BANK) -->
			    <select name="receiverAccountNumber" id="allAccountsSelect" class="receiver-select">
			        <option disabled selected value="">-- Select Receiver Account --</option>
			        <c:forEach var="account" items="${allAccounts}">
			            <option value="${account.bankAccountNo}">
			                ${account.bankAccountNo} - ${account.bankName} (${account.user.username})
			            </option>
			        </c:forEach>
			    </select>
			
			    <!-- Only User's Accounts (for WALLET_TO_BANK) -->
			    <select name="receiverAccountNumber" id="userAccountsSelect" class="receiver-select hidden">
			        <option disabled selected value="">-- Select Receiver Account --</option>
			        <c:forEach var="account" items="${accounts}">
			            <option value="${account.bankAccountNo}">
			                ${account.bankAccountNo} - ${account.bankName}
			            </option>
			        </c:forEach>
			    </select>
			</div>

            <!-- 🔄 Destination Type -->
            <div class="form-group">
                <label for="destinationType">Send To:</label>
                <select name="destinationType" id="destinationType" onchange="toggleFields()" required>
                    <option value="BANK">Bank Account</option>
                    <option value="BANK_TO_WALLET">Wallet</option>
                    <option value="WALLET_TO_BANK">Wallet to Bank</option>
                </select>
            </div>

            <!-- 💰 Amount -->
            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" step="0.01" name="amount" placeholder="Enter amount" required />
            </div>

            <!-- Hidden usernames -->
            <input type="hidden" name="senderUsername" value="${sessionScope.username}" />
            <input type="hidden" name="receiverUsername" value="${sessionScope.username}" />

            <button type="submit">Send Money</button>
        </form>

        <a href="${pageContext.request.contextPath}/dashboard" class="btn-back">Back to Dashboard</a>
    </div>
</body>
</html>
