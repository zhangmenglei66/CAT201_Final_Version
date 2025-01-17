<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add User</title>
    <link rel="stylesheet" href="bs/css/bootstrap.css">
    <script type="text/javascript" src="bs/js/jquery.min.js"></script>
    <script type="text/javascript" src="bs/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="bs/validform/style.css">
    <script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="js/admin/adminManage/adminAdd.js"></script>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: #eee;
        }
    </style>
</head>
<body>
<c:if test="${!empty adminMessage}">
    <h3 class="text-center">${adminMessage}</h3>
</c:if>
<div class="container">

    <h2 class="text-center">Add User</h2>
    <form id="myForm" action="jsp/admin/AdminManageServlet?action=add" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="username" class="col-sm-2 col-sm-offset-2 control-label">username：</label>
            <div class="col-sm-4">
                <input name="username" id="username" type="text" class="form-control" required>
            </div>
            <div class="col-sm-4">
                <span class="Validform_checktip">Username must start with a letter and be 4-8 characters long.</span>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 col-sm-offset-2 control-label">password：</label>
            <div class="col-sm-4">
                <input type="password" name="password" id="password" required class="form-control">
            </div>
            <div class="col-sm-4">
                <span class="Validform_checktip">password must be 4-8 characters long.</span>
            </div>
        </div>
        <div class="form-group">
            <label for="c_password" class="col-sm-2 col-sm-offset-2 control-label">Confirm password：</label>
            <div class="col-sm-4">
                <input type="password" name="c_password" id="c_password" class="form-control">
            </div>
            <div class="col-sm-4">
                <span class="Validform_checktip"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 col-sm-offset-2 control-label">Name：</label>
            <div class="col-sm-4">
                <input type="text" id="name" name="name" required class="form-control">
            </div>
            <div class="col-sm-4">
                <span class="Validform_checktip">name must be 2-8 characters long.</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 col-sm-offset-2 control-label">
                <input class="btn btn-success btn-block" type="submit" value="Submit">
            </label>
            <label class="col-sm-2 control-label">
                <input class="btn btn-warning btn-block" type="reset" value="Reset">
            </label>
        </div>
    </form>
</div>
</body>
</html>
