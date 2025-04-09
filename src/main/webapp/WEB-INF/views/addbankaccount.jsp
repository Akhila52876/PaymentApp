<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Bank Account</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: linear-gradient(45deg, #8b5aed 0%, #78ebfc 100%);
            font-family: Arial, sans-serif;
        }

        .title {
            position: absolute;
            top: 20px;
            font-size: 20px;
            font-weight: bold;
            color: red;
            border: 2px solid red;
            padding: 10px;
            text-align: center;
            background: white;
            width: 450px;
        }

        .form-container {
            background: white;
            padding: 50px;
            border-radius: 30px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 450px;
        }

        label {
            font-weight: bold;
            color: blue;
            display: inline-block;
            margin-bottom: 5px;
        }

        .input-group {
            margin-bottom: 15px;
        }

        .input-group form:input,
        .input-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        .input-group input:hover,
        .input-group input:focus {
            border-color: #8b5aed;
            box-shadow: 0px 0px 8px rgba(139, 90, 237, 0.5);
            outline: none;
        }

        .submit-btn {
            background-color: blue;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            transition: 0.3s;
            display: inline-block;          
            cursor: pointer;
            font-size: 16px;
        }

        .submit-btn:hover {
            background-color: darkblue;
        }

        .back-link {
            margin-top: 20px;
            display: block;
            text-align: center;
            color: white;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="title">Add Bank Account</div>

<div class="form-container">
   <form:form method="POST" action="addbankaccount" modelAttribute="addbankAccountDto">

        <div class="input-group">
         <input type="hidden" name="userId" value="${userId}" />
            <label>Account Number:</label>
            <form:input path="bankAccountNo" cssClass="input" />
        </div>

        <div class="input-group">
            <label>IFSC Code:</label>
            <form:input path="ifscCode" cssClass="input" />
        </div>
        

        <div class="input-group">
            <label>Bank Name:</label>
            <form:input path="bankName" cssClass="input" />
        </div>

        <div class="input-group">
            <label>Branch Name:</label>
            <form:input path="branchName" cssClass="input" />
        </div>

        <div class="input-group">
            <label>Is Active (Y/N):</label>
            <form:input path="isActive" cssClass="input" />
        </div>
        
         <div class="input-group">
            <label>Current Balance:</label>
            <form:input path="currentbalance" cssClass="input" />
        </div>

      <center>
	    <button type="submit" name="action" value="save" class="submit-btn">Save</button>
	    <button type="submit" name="action" value="saveAndClose" class="submit-btn">Save & Close</button>
     </center>

    </form:form>

    <a href="/bankaccount/list" class="back-link">Back to Account List</a>
</div>

</body>
</html>
