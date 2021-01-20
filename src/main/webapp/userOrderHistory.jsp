<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.order.history"/></title>
</head>


<style>
   <%@include file='css/style.css' %>
</style>   
</head>

<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="footer.jsp" />
<br>
<div class="page-container"> 
<c:choose>
		<c:when test="${sessionScope.user eq null}">
			<c:redirect url="userLogin.jsp" />
		</c:when>
		<c:when test="${sessionScope.userData eq null}">
			<!-- add action on when null-->
		</c:when>
		<c:otherwise>
			<p><c:out value="Welcome Back ${sessionScope.user}" /></p>
		</c:otherwise>
</c:choose> 

<table class ="userdata">
		<tr>
			<td><p class ="bold"><fmt:message key="label.order.number"/></p></td>
			<td><p class ="bold"><fmt:message key="label.product.summary"/></p></td>
			<td><p class ="bold"><fmt:message key="label.product.price"/></p></td>
			<td><p class ="bold"><fmt:message key="label.product.quantity"/></p></td>
			<td><p class ="bold"><fmt:message key="label.order.status"/></p></td>
		</tr>
			<c:forEach var="orderList" items="${sessionScope.userOrders}" varStatus="iterationCount">	
		<tr>
			<td>${orderList.orderid}</td>
			<td>${orderList.productTitle}</td>
			<td>${orderList.productPrice}</td>
			<td>${orderList.productQuantity}</td>
			<td>${orderList.orderStatus}</td>
		</tr>
	</c:forEach>		
</table>		
</div>
</body>
</html>