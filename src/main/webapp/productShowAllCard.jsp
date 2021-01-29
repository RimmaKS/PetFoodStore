<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.shop"/></title>
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

<table class="productlist">
	<c:forEach var="product" items="${sessionScope.products}" varStatus="iterationCount">
	<tr>
	<td>							
		<div class="card">						
		<div id="productimage">  			
        <img src="${pageContext.request.contextPath}/ImageServlet?imageName=${product.image}" alt="Pet Food" style="width:50%"/>
		</div>
						
  		<div id="productinfo">
			<p>${product.brand}</p>
			<div class="title"><p style="font-family:verdana">${product.title}</p></div>
			<p>${product.weight}</p>
			<p class="price">${product.price} $</p>
			</div>

<c:if test="${product.quantity < 1}">
		<form action="addtocart" method="post">
			<button type="submit" class="addtocart" disabled><fmt:message key="button.notavailable"/></button> 
			<input type="hidden" id="productToAdd" name="productToAdd" value="${product.id}">
		</form>
</c:if>

<c:if test="${product.quantity > 0}">						    							
		<form action="addtocart" method="post">
			<button type="submit" class="addtocart"><fmt:message key="button.addtocart"/></button> 
			<input type="hidden" id="productToAdd" name="productToAdd" value="${product.id}">
		</form>
</c:if>		
			</div>

			</td>				
			</c:forEach>				
			</table>

	</div>
</body>
</html>