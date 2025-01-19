<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>Online Store</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
</head>
<body>
<div class="head">
    <%@include file="header.jsp"%>
</div>

<div class="block_home_slider">
    <div id="home_slider" class="flexslider">
        <ul class="slides">
            <li>
                <div class="slide"><img src="${pageContext.request.contextPath}/img/banner2.jpg"/></div>
            </li>
            <li>
                <div class="slide"><img src="${pageContext.request.contextPath}/img/banner1.jpg"/></div>
            </li>
        </ul>
    </div>
</div>
<div class="news">
    <div class="wrapper">

        <div class="flower clearfix tran">
            <c:forEach begin="0" end="2" var="fz" items="${fz}">
            <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${fz.id}" class="clearfix">
                <dl>
                    <dt><span class="abl"></span><img src="/photo/${fz.productImage}" style="width: 360px;"/><span class="abr"></span></dt>
                    <dd>${fz.productName}</dd>
                    <dd><span>¥ ${fz.productPrice}0</span></dd>
                </dl>
            </a>
            </c:forEach>
        </div>
        <div class="flower clearfix tran">
            <c:forEach begin="3" end="5" var="fz" items="${fz}">
                <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${fz.id}" class="clearfix">
                    <dl>
                        <dt><span class="abl"></span><img src="/photo/${fz.productImage}" style="width: 360px;height: 360px;"/><span class="abr"></span></dt>
                        <dd>${fz.productName}</dd>
                        <dd><span>¥ ${fz.productPrice}0</span></dd>
                    </dl>
                </a>
            </c:forEach>
        </div>
        <div class="flower clearfix tran">
            <c:forEach begin="0" end="2" var="dz" items="${dz}">
                <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${dz.id}" class="clearfix">
                    <dl>
                        <dt><span class="abl"></span><img src="/photo/${dz.productImage}" style="width: 360px;"/><span class="abr"></span></dt>
                        <dd>${dz.productName}</dd>
                        <dd><span>¥ ${dz.productPrice}0</span></dd>
                    </dl>
                </a>
            </c:forEach>
        </div>
        <div class="flower clearfix tran">
            <c:forEach begin="3" end="5" var="dz" items="${dz}">
                <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${dz.id}" class="clearfix">
                    <dl>
                        <dt><span class="abl"></span><img src="/photo/${dz.productImage}" style="width: 360px;height: 360px;"/><span class="abr"></span></dt>
                        <dd>${dz.productName}</dd>
                        <dd><span>¥ ${dz.productPrice}0</span></dd>
                    </dl>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
    <a href="#" class="ad">
        <img src="${pageContext.request.contextPath}/img/ib1.jpg"/>
    </a>
<div class="people">
    <div class="wrapper">
        <div class="pList clearfix tran">
            <c:forEach begin="0" end="3" items="${mz}" var="mz">
                <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${mz.id}">
                    <dl>
                        <dt>
                            <span class="abl"></span>
                            <img src="/photo/${mz.productImage}" style="width: 270px;height: 270px;"/>
                            <span class="abr"></span>
                        </dt>
                        <dd style="width: 270px;">${mz.productName}</dd>
                        <dd><span>￥${mz.productPrice}0</span></dd>
                    </dl>
                </a>
            </c:forEach>
        </div>
        <div class="pList clearfix tran">
            <c:forEach begin="4" end="7" items="${mz}" var="mz">
                <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${mz.id}">
                    <dl>
                        <dt>
                            <span class="abl"></span>
                            <img src="/photo/${mz.productImage}" style="width: 270px;height: 270px;"/>
                            <span class="abr"></span>
                        </dt>
                        <dd style="width: 270px;">${mz.productName}</dd>
                        <dd><span>￥${mz.productPrice}0</span></dd>
                    </dl>
                </a>
            </c:forEach>
        </div>
        <div class="pList clearfix tran">
            <c:forEach begin="8" end="11" items="${mz}" var="mz">
                <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${mz.productId}">
                    <dl>
                        <dt>
                            <span class="abl"></span>
                            <img src="/photo/${mz.productImage}" style="width: 270px;height: 270px;"/>
                            <span class="abr"></span>
                        </dt>
                        <dd style="width: 270px;">${mz.productName}</dd>
                        <dd><span>￥${mz.productPrice}0</span></dd>
                    </dl>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
<c:if test="${sessionScope.user.username!=null}">
<div class="gotop">
    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=findcart">
    <dl>
        <dt><img src="${pageContext.request.contextPath}/img/gt1.png"/></dt>
        <dd>去购<br/>物车</dd>
    </dl>
</a>
    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=mydata">
        <dl>
            <dt><img src="${pageContext.request.contextPath}/img/gt3.png"/></dt>
            <dd>个人<br/>中心</dd>
        </dl>
    </a>
    <a href="#" class="toptop" style="display: none">
        <dl>
            <dt><img src="${pageContext.request.contextPath}/img/gt4.png"/></dt>
            <dd>Back<br/>Top</dd>
        </dl>
    </a>
</div>
</c:if>
<div class="footer">
    <div class="top">
        <div class="wrapper">
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot1.png"/></a><span class="fl">7-day no-questions-asked return policy</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot2.png"/></a><span class="fl">15-day free exchange</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot3.png"/></a><span class="fl">Free shipping on orders over 599RM</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot4.png"/></a><span class="fl">Mobile special services</span>
            </div>
        </div>
    </div>
   </div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">$(function () {
    $('#home_slider').flexslider({
        animation: 'slide',
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        slideshow: true,
        slideshowSpeed: 2000,
        useCSS: false
    });
});</script>
</body>
</html>
