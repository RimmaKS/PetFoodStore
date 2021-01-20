<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.shoppingcart"/></title>
</head>

<style>
   <%@include file='css/style.css' %>
</style>   
</head>

<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="footer.jsp" />
<br>
	
<jsp:useBean id = "shoppingCart" scope = "session"  class = "com.epam.tcfp.zooproject.entity.ShoppingCart" />

<div class="page-container"> 
<c:if test="${sessionScope.shoppingCart eq null || empty shoppingCart.items}">
      <strong><fmt:message key="error.cart.empty"/></strong>
</c:if>

<c:if test="${sessionScope.shoppingCart ne null && not empty shoppingCart.items}">
<table class ="userdata">
   <tr>
		<td><p class ="bold"><fmt:message key="label.product.id"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.title"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.weight"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.price"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.quantity"/></p></td>		
		<td><p class ="bold"></p></td>		
   </tr>

<c:forEach var="product" items="${shoppingCart.items}">
	<tr>
		<td>${product.id}</td>
		<td>${product.title}</td>
		<td>${product.weight}</td>
		<td>${product.price}</td>
		<td>	
			<form action="changeAmount" method="post">
  			<input type="number" id="quantityToChange" name="quantityToChange" value="${product.quantity}" min="1" max="20" size="2" maxlength="2">
  			<input type="submit" value="<fmt:message key="label.product.changeamount"/>">
  			<input type="hidden" id="productToChange" name="productToChange" value="${product.id}">
			</form>
		</td>
	    <td>
		<a href="<c:url value="removefromcart">
		<c:param name="removefromcart" value="post"/>
		<c:param name="productToRemove" value="${product.id}"/>
		</c:url>"><fmt:message key="label.product.remove"/></a>
	    </td>		
	</tr>
	</c:forEach>	
</table>	
	
<strong><c:out value="Total cost: ${shoppingCart.totalCost}"/></strong>

<form action="checkAddress" method="post">
<button class="buttoncheckout" type="submit"><fmt:message key="button.checkout"/></button>
</form>
</c:if>

<br>
<c:if test="${sessionScope.cartEditStatus eq 'itemDeleted'}">
<strong><fmt:message key="notify.cart.item.delete"/></strong>
<c:remove var="cartEditStatus" scope="session" />
</c:if>

<c:if test="${sessionScope.cartEditStatus eq 'nothingToDelete'}">
<strong><fmt:message key="notify.cart.item.nothing"/></strong>
<c:remove var="cartEditStatus" scope="session" />
</c:if>
</div>
</body>
</html>
