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
    <script type="text/javascript" src="js/admin/userManage/userAdd.js"></script>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: #eee;
        }
    </style>
</head>
<body>
<c:if test="${!empty userMessage}">
    <h3 class="text-center">${userMessage}</h3>
</c:if>
<div class="container">
    <h2 class="text-center">Add User</h2>
    <form id="myForm" action="jsp/admin/UserManageServlet?action=add" method="post" class="form-horizontal"
    ">
    <div class="form-group">
        <label for="userName" class="col-md-2 col-md-offset-2 control-label">username：</label>
        <div class="col-md-4">
            <input name="username" id="username" type="text" class="form-control">
        </div>
        <div class="col-md-4">
            <span class="Validform_checktip">*Required</span>
        </div>
    </div>
    <div class="form-group">
        <label for="passWord" class="col-md-2 col-md-offset-2 control-label">password：</label>
        <div class="col-md-4">
            <input type="password" name="password" id="password" class="form-control">
        </div>
        <div class="col-md-4">
            <span class="Validform_checktip">*Required</span>
        </div>
    </div>
    <div class="form-group">
        <label for="c_passWord" class="col-md-2 col-md-offset-2 control-label">Confirm password：</label>
        <div class="col-md-4">
            <input type="password" name="c_password" id="c_password" class="form-control">
        </div>
        <div class="col-md-4">
            <span class="Validform_checktip"></span>
        </div>
    </div>
    <div class="form-group">
        <label for="nickname" class="col-md-2 col-md-offset-2 control-label">Name：</label>
        <div class="col-md-4">
            <input type="text" id="nickname" name="nickname" class="form-control">
        </div>
        <div class="col-md-4">
            <span class="Validform_checktip">*Required</span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 col-md-offset-2 control-label">Gender：</label>
        <div class="col-md-4 ">
            <label class="radio-inline">
                <input type="radio" name="sex" id="sex" class="pr1" value="Man">Man
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" class="pr1" value="Woman">Woman
            </label>
        </div>

    </div>

    <div class="form-group">
        <label for="phone" class="col-md-2 col-md-offset-2 control-label">Telephone：</label>
        <div class="col-md-4">
            <input type="text" id="phone" name="phone" class="form-control">
        </div>
        <div class="col-md-4">
            <span class="Validform_checktip">*Required</span>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-md-2 col-md-offset-2 control-label">Email：</label>
        <div class="col-md-4">
            <input type="text" id="email" name="email" class="form-control">
        </div>
        <div class="col-md-4">
            <span class="Validform_checktip">*Required</span>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2  control-label col-md-offset-2">
            <input class="btn btn-success btn-block" type="submit" value="Add">
        </label>
        <label class="col-md-2 control-label">
            <input class="btn btn-warning btn-block" type="reset" value="Reset">
        </label>
    </div>
    </form>
</div>
</body>
</html>
