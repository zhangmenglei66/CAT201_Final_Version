<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Product details page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
</head>
<body>
<div class="head">
    <%@include file="header.jsp"%>
</div>
<div class="address">
    <div class="wrapper clearfix"><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=base">Home</a><span>/</span>
        <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=selectproduct&fid=${product.productFid}">${product.productFidName}</a><span>/</span>
        <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=selectproduct&cid=${product.productCid}">${product.productCidName}</a><span>/</span>
        <a  class="on">${product.productName}</a>
    </div>
</div>
<div class="detCon">
    <div class="proDet wrapper">
        <div class="proCon clearfix">
            <div class="proImg fl"><img class="det" src="/photo/${product.productImage}"/>

            </div>
            <div class="fr intro">
                <div class="title"><h4>${product.productName}</h4>
                    <p style="margin-top: 50px;">${product.productDescription}</p><span>￥${product.productPrice}0</span></div>
                <div class="proIntro">

                    <p>Quantity:&nbsp;&nbsp;<span>${product.productStock}</span> in stock</p>
                    <div class="num clearfix">
                        <img class="fl sub" src="${pageContext.request.contextPath}/img/sub.jpg">
                        <span id="count" class="fl" contentEditable="true">1</span>
                        <img class="fl add" src="${pageContext.request.contextPath}/img/add.jpg">
                    </div>
                </div>
                <div class="btns clearfix">

                    <a href="javascript:shopadd('${product.id}')">
                        <p class="cart fr">Add to cart</p></a></div>
            </div>
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
    </a><a href="#" class="toptop" style="display: none;">
    <dl>
        <dt><img src="${pageContext.request.contextPath}/img/gt4.png"/></dt>
        <dd>返回<br/>顶部</dd>
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
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot3.png"/></a><span class="fl">Free shipping on orders over 599</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot4.png"/></a><span class="fl">Mobile special services</span>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/cart.js" type="text/javascript" charset="utf-8"></script>
<script>
    function shopadd(id) {
        var  count = document.getElementById("count").innerHTML;
        location.href='${pageContext.request.contextPath}/jsp/IndexServlet?action=productoperation&product_id='+id+'&cart_num='+count;
    }
</script>
</body>
</html>
