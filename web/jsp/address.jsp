<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>Personal information</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygrxx.css"/>
</head>
<body>
<div class="head ding">
    <%@include file="header.jsp" %>
</div>
<div class="address mt" id="add">
    <div class="wrapper clearfix"><a href="IndexServlet?action=base" class="fl">Home</a><span>/</span><a
            href="IndexServlet?action=address" class="on">Shipping address</a>
    </div>
</div>
<div class="Bott">
    <div class="wrapper clearfix">
        <%@include file="dataAside.jsp" %>
        <div class="you fl"><h2>Personal information</h2>
            <div class="gxin">
                <div class="tx"><a href="#"><img src="${pageContext.request.contextPath}/img/tx.png"/>
                </a></div>
                <div class="xx"><h3 class="clearfix"><strong class="fl">Basic information</strong><a href="#" class="fr" id="edit1">Edit</a>
                </h3>
                    <div>Recipient：${address.name}</div>
                    <div>Recipient‘s telephone：${address.phone}</div>
                    <div>Shipping address：${address.address}</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="mask"></div>
<div class="bj">
    <div class="clearfix"><a href="#" class="fr gb"><img src="${pageContext.request.contextPath}/img/icon4.png"/></a>
    </div>
    <h3>EditAddress</h3>
    <form action="${pageContext.request.contextPath}/jsp/IndexServlet?action=addresschange" method="post">
        <input type="hidden" name="id" value="${address.id}">
        <p><label>Recipient：</label><input type="text" name="name" value="${address.name}"/></p>
        <p><label>Recipient‘s telephone：</label><input style="width: 200px" type="text" name="phone" value="${address.phone}"/></p>
        <p><label>Shipping address：</label><input type="text" name="address" value="${address.address}"/></p>

        <div class="bc"><input type="submit" value="Save"/><input type="button" value="Cancel"/></div>
    </form>
</div>
<div class="footer">
    <div class="top">
        <div class="wrapper">
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot1.png"/></a><span
                    class="fl">7-day no-questions-asked return policy</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot2.png"/></a><span
                    class="fl">15-day free exchange</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot3.png"/></a><span
                    class="fl">Free shipping on orders over 599</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot4.png"/></a><span
                    class="fl">Mobile special services</span>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
