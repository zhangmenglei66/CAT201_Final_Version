<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl"><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=base"><img style="height: 80px; width: 110px" src="${pageContext.request.contextPath}/img/logo.png"/></a></h1>
            <div class="fr clearfix" id="top1"><p class="fl">
                <c:if test="${sessionScope.user.username==null}">
                    <a href="${pageContext.request.contextPath}/jsp/login.jsp" id="login">Login</a><a href="${pageContext.request.contextPath}/jsp/regesite.jsp" id="reg">Register</a>
                </c:if>
                <c:if test="${sessionScope.user.username!=null}">
                    Hello,<a>${sessionScope.user.nickname}</a>
                </c:if>
            </p>

                <div class="btn fl clearfix">
                    <c:if test="${sessionScope.user.username!=null}">
                    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=mydata"><img src="${pageContext.request.contextPath}/img/grzx.png"/></a>
                        <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=findcart">
                            <img src="${pageContext.request.contextPath}/img/gwc.png"/>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=base">Home</a></li>
            <c:forEach var="f" items="${flist}">
                <li>
                    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=selectproduct&fid=${f.id}">${f.cateName}</a>
                        <div class="sList2">
                            <div class="clearfix">
                                <c:forEach var="c" items="${clist}">
                                    <c:if test="${c.cateParentId==f.id}">
                                        <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=selectproduct&cid=${c.id}">${c.cateName}</a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                </li>
            </c:forEach>
        </ul>
    </div>
