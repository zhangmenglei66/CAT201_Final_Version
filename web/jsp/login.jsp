<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../css/public.css"/>
    <link rel="stylesheet" type="text/css" href="../css/login.css"/>
</head>
<body>
<div class="login">
    <form action="${pageContext.request.contextPath}/UserServlet?action=login" method="post">
        <h3>
            <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=base">
                <img src="../img/logo.png" style="width: 80px;height: 80px;">
            </a>
        </h3>
        <p><input type="text" name="username" placeholder="username"></p>
        <p><input type="text" name="user_Password" placeholder="password"></p>
        <p>
            <input type="text" name="verifyCodes" placeholder="Captcha" style="width: 100px;">
            <img src="${pageContext.request.contextPath}/jsp/IndexServlet?action=verifyCode" alt="" width="100"
                 height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="changeCode()">
        </p>
        <p><input type="submit" value="Login"></p>
        <c:if test="${not empty errMsg}">
            <div style="color: red;font-size: 15px;">
                    ${errMsg}
            </div>
        </c:if>
        <p class="txt"><a class="" href="/jsp/regesite.jsp">Register</a></p>
    </form>
</div>
</body>
<script type="text/javascript">
    function changeCode() {
        var img = document.getElementsByTagName("img")[1];
        img.src = "/jsp/IndexServlet?action=verifyCode&time=" + new Date().getTime();

    }
</script>
</html>
