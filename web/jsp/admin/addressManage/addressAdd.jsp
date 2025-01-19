<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%= basePath %>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Shipping Address</title>
    <link rel="stylesheet" href="bs/css/bootstrap.css">
    <script src="bs/js/jquery.min.js"></script>
    <link rel="stylesheet" href="bs/validform/style.css">
    <script src="bs/validform/Validform_v5.3.2_min.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: #f7f7f7;
        }
        .container {
            margin-top: 20px;
        }
        .form-group label {
            text-align: left;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:if test="${!empty addressMessage}">
            <div class="alert alert-info text-center">${addressMessage}</div>
        </c:if>
        <h2 class="text-center">Add Shipping Address</h2>
        <form id="addressAddForm" class="form-horizontal" action="jsp/admin/AddressManageServlet?action=add" method="post">
            <div class="form-group">
                <label for="phone" class="col-sm-2 col-sm-offset-2 control-label">Recipient's Telephone</label>
                <div class="col-sm-4">
                    <input type="text" name="phone" id="phone" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-2 col-sm-offset-2 control-label">Recipient's Address</label>
                <div class="col-sm-4">
                    <input type="text" name="address" id="address" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 col-sm-offset-2 control-label">Recipient's Name</label>
                <div class="col-sm-4">
                    <input type="text" name="name" id="name" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="userId" class="col-sm-2 col-sm-offset-2 control-label">User ID</label>
                <div class="col-sm-4">
                    <input type="text" name="userId" id="userId" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4 col-sm-offset-4">
                    <button type="submit" class="btn btn-success btn-block">Submit</button>
                    <button type="reset" class="btn btn-warning btn-block">Reset</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
