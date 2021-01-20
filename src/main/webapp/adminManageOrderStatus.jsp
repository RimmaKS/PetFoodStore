<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.order.status.manage"/></title>
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
		<c:otherwise>
			<fmt:message key="label.loggedinuser"/>
			<c:out value="${sessionScope.user}" />			
		</c:otherwise>
</c:choose> 

<c:if test="${sessionScope.ordersNotExist eq 'true'}">
   <div class="warning">
       <strong><fmt:message key="error.noorders"/></strong>
   </div>
</c:if>

<c:if test="${sessionScope.ordersNotExist eq 'false'}">
<table class ="userdata">
	<tr>
		<td><p class ="bold"><fmt:message key="label.order.number"/></p></td>
		<td><p class ="bold"><fmt:message key="label.order.totalcost"/></p></td>
		<td><p class ="bold"><fmt:message key="label.order.status"/></p></td>
		<td></td>
		
	</tr>
		<c:forEach var="order" items="${sessionScope.allOrders}" varStatus="iterationCount">
	<tr>
		<td>${order.id}</td>
		<td>${order.totalCost}</td>
		<td>${order.statusId}</td>			  
		<td>
		<form action="updateOrderStatus" method="post">
   		<br><label for="orderStatus"><b><fmt:message key="label.order.status"/></b></label>
	    <select name="orderStatus" id="orderStatus">
	    <option value="1">New</option>
	    <option value="2">Checkout</option>
	    <option value="3">Paid</option>
	    <option value="4">Failed</option>
	    <option value="5">Shipped</option>
	    <option value="6">Delivered</option>
	    <option value="7">Returned</option>	    
	    </select>	
	    <input type="submit" value="<fmt:message key="label.order.status.update"/>">
  		<input type="hidden" id="orderToChange" name="orderToChange" value="${order.id}">
	    </form>
		</td>		
	</tr>
	</c:forEach>	
</table>	
</c:if>
	
</div>
</body>
</html>