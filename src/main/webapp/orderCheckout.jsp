<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.checkout"/></title>
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
      <strong><c:out value="Your shopping cart is empty. Check out impossible."/></strong>
</c:if>

<c:if test="${sessionScope.shoppingCart ne null && not empty shoppingCart.items}">
<div class="address-container">
	<p><strong><fmt:message key="title.ordercontent"/></strong></p>
<table class ="userdata">
   <tr>
		<td><p class ="bold"><fmt:message key="label.product.id"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.title"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.weight"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.price"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.quantity"/></p></td>	
	
   </tr>

<c:forEach var="product" items="${shoppingCart.items}">
	<tr>
		<td>${product.id}</td>
		<td>${product.title}</td>
		<td>${product.weight}</td>
		<td>${product.price}</td>
		<td>${product.quantity}</td>	
	</tr>
	</c:forEach>	
</table>	
	
<strong><c:out value="Total cost: ${shoppingCart.totalCost}"/></strong>
   </div>
   
   
<div class="address-container">   
<jsp:useBean id = "address" scope = "session"  class = "com.epam.tcfp.zooproject.entity.UserAddress" />

<c:if test="${sessionScope.addressStatus eq 'exists'}">

<table class ="userdata">
  <tr><fmt:message key="title.exist.address"/></tr>
  <tr><td><strong><fmt:message key="label.order.address.zip"/></strong></td><td>${address.zipCode}</td></tr>
  <tr><td><strong><fmt:message key="label.order.address.country"/></strong></td><td>${address.country}</td></tr>
  <tr><td><strong><fmt:message key="label.order.address.region"/></strong></td><td>${address.region}</td></tr>
  <tr><td><strong><fmt:message key="label.order.address.city"/></strong></td><td>${address.city}</td></tr>  
  <tr><td><strong><fmt:message key="label.order.address.street"/></strong></td><td>${address.street}</td></tr>
  <tr><td><strong><fmt:message key="label.order.address.building"/></strong></td><td>${address.building}</td></tr>  
  <tr><td><strong><fmt:message key="label.order.address.apartment"/></strong></td><td>${address.apartment}</td></tr>  
</table>
   
    <form action="createOrder" method="post">
      <button type="submit"><fmt:message key="button.checkout.useexisting"/></button> 
      <input type="hidden" id="addressNoChange" name="addressNoChange" value="true">
    </form>  
</c:if>
</div>


<div class="address-container">
	<p><strong><fmt:message key="title.order.address.details"/></strong></p>    

    <form action="createOrder" method="post">    	
    <label for="zipCode"><strong><fmt:message key="label.order.address.zip"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.zip"/>" 
    	name="zipCode"
    	required pattern="^[a-zA-Z0-9\-]{1,8}$"
    	title="<fmt:message key="label.order.address.zip"/>" 
    	required="required">   
		  	
    <label for="country"><strong><fmt:message key="label.order.address.country"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.country"/>" 
    	name="country"
    	required pattern="^[a-zA-Z0-9\-]{1,10}$"
    	title="<fmt:message key="label.order.address.country"/>" 
    	required="required">

    <label for="region"><strong><fmt:message key="label.order.address.region"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.region"/>" 
    	name="region"
    	required pattern="^[a-zA-Z0-9\-]{1,50}$"
    	title="<fmt:message key="label.order.address.region"/>" 
    	required="required">

    <label for="city"><strong><fmt:message key="label.order.address.city"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.city"/>" 
    	name="city"
    	required pattern="^[a-zA-Z0-9\-\s]{1,85}$"
    	title="<fmt:message key="label.order.address.city"/>" 
    	required="required">
    	
    <label for="street"><strong><fmt:message key="label.order.address.street"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.street"/>" 
    	name="street"
    	required pattern="^[a-zA-Z0-9\-\s]{1,50}$"
    	title="<fmt:message key="label.order.address.street"/>" 
    	required="required">
    
    <label for="building"><strong><fmt:message key="label.order.address.building"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.building"/>" 
    	name="building"
    	required pattern="^[a-zA-Z0-9\-]{1,10}$"
    	title="<fmt:message key="label.order.address.building"/>" 
    	required="required">	
    
    <label for="apartment"><strong><fmt:message key="label.order.address.apartment"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.apartment"/>" 
    	name="apartment"
    	required pattern="^[a-zA-Z0-9\-]{1,10}$"
    	title="<fmt:message key="label.order.address.apartment"/>" 
    	required="required">
    		
<c:if test="${sessionScope.addressStatus eq 'exists'}"> 	
    <button type="submit"><fmt:message key="button.checkout.change"/></button>
    <input type="hidden" id="addressChange" name="addressChange" value="true">
</c:if>  

<c:if test="${sessionScope.addressStatus eq 'notExists'}">    		
    <button type="submit"><fmt:message key="button.checkout.change"/></button>
    <input type="hidden" id="newAddress" name="newAddress" value="true">
</c:if>   
    </form>
</div>

</c:if>

<br>

</div>
</body>
</html>
