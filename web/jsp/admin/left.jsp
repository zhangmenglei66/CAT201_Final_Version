<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert Title Here</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/admin/left.css">
</head>
<body>
<div class="nowTime"></div>
<div class="left">
    <ul>
        <li class="list">
            <h3>User</h3>
            <ul>
                <li><a href="jsp/admin/AdminManageServlet?action=list" target="rFrame">Admin Management</a></li>
                <li><a href="jsp/admin/UserManageServlet?action=list" target="rFrame">User Management</a></li>
            </ul>
        </li>
        <li class="list">
            <h3>Product</h3>
            <ul>
                <li><a href="jsp/admin/CategoryManageServlet?action=list" target="rFrame">Product Categories</a></li>
                <li><a href="jsp/admin/ProductManageServlet?action=list" target="rFrame">Product List</a></li>
            </ul>
        </li>
        <li class="list">
            <h3>Order</h3>
            <ul>
                <li><a href="jsp/admin/OrdersManageServlet?action=list" target="rFrame">Order List</a></li>
                <%--					<li><a href="jsp/admin/CartManageServlet?action=list" target="rFrame">购物车管理</a></li>--%>
            </ul>
        </li>
        <li class="list">
            <h3>Address</h3>
            <ul>
                <li><a href="jsp/admin/AddressManageServlet?action=list" target="rFrame">Shipping Address</a></li>
            </ul>
        </li>

    </ul>
</div>
<script type="text/javascript">
    /* 菜单切换展开 */
    $(".list h3").next().slideUp(300);
    $(".list h3").click(function () {
        $(".list h3").css("color", "#fff");
        $(".list h3").next().slideUp(300);
        if ($(this).next().css("display") == "none") {
            // $(this).css("color","#bc0a6b");
            $(this).next().slideDown(300);
        } else {
            $(this).next().slideUp(300);
        }
    })

    $(".list ul a").click(function () {
        $(".list ul a").css("color", "#000");
        // $(this).css("color","#bc0a6b");
    })

    /* 计时器 */
    function showTime() {
        var now = new Date();
        var time = now.toLocaleDateString() + " " + now.toLocaleTimeString();
        document.getElementsByClassName('nowTime')[0].innerHTML = time;
    }

    setInterval(showTime, 1000);

</script>
</body>
</html>
