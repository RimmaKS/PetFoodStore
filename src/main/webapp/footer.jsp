<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<body>
 
<c:if test="${sessionScope.user ne null}">
<div class="footer">
<a href="<c:url value="logout"><c:param name="logout" value="post"/></c:url>"><fmt:message key="label.logout"/></a>
<a href="<c:url value="userdataget"><c:param name="userdata" value="post"/></c:url>"><fmt:message key="label.myaccount"/></a>
<a href="shoppingCart.jsp"><fmt:message key="title.shoppingcart"/></a>
<a href="<c:url value="orderHistory"><c:param name="orderHistory" value="post"/></c:url>"><fmt:message key="title.order.history"/></a>


</div>
</c:if>


<c:if test="${ (sessionScope.user eq null) || (sessionScope.user eq 'guest')}">
<div class="footer">
<a href="userLogin.jsp"><fmt:message key="label.login"/></a>
<a href="userRegistration.jsp"><fmt:message key="label.register"/></a>
</div>
</c:if>
</body>

<!-- if user is admin -->
<c:if test="${sessionScope.isAdmin eq 'true'}">
<div class="footer">
<a href="<c:url value="logout"><c:param name="logout" value="post"/></c:url>"><fmt:message key="label.logout"/></a>
<a href="<c:url value="userdataget"><c:param name="userdata" value="post"/></c:url>"><fmt:message key="label.myaccount"/></a>
<a href="userRegistration.jsp"><fmt:message key="label.registernewuser"/></a>
<a href="<c:url value="manageusers"><c:param name="manageusers" value="post"/></c:url>"><fmt:message key="label.manageusers"/></a>
<a href="<c:url value="productshow"><c:param name="productshow" value="post"/><c:param name="editpage" value="editpage"/></c:url>"><fmt:message key="title.product.manage"/></a>  
<a href="<c:url value="getordersstatus"><c:param name="getordersstatus" value="post"/></c:url>"><fmt:message key="title.order.status.manage"/></a>  


</div>
</c:if>