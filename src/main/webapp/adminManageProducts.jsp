<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.product.manage"/></title>
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
<c:if test="${sessionScope.productupdatestatus eq 'updated'}">
   <div class="warning">
       <strong><fmt:message key="label.product.updated"/></strong>
   </div>
   <c:set var="productupdatestatus" value="" scope="session"/>
</c:if>

<c:if test="${sessionScope.productaddstatus eq 'added'}">
   <div class="warning">
       <strong><fmt:message key="label.product.added"/></strong>
   </div>
   <c:set var="productaddstatus" value="" scope="session"/>
</c:if>

<c:choose>
		<c:when test="${sessionScope.user eq null}">
			<c:redirect url="userLogin.jsp" />
		</c:when>
		<c:otherwise>
			<fmt:message key="label.loggedinuser"/>
			<c:out value="${sessionScope.user}" />				
		</c:otherwise>
</c:choose>




<table class ="userdata">
	<tr>
		<td><p class ="bold"><fmt:message key="label.product.id"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.title"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.brand"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.form"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.breed"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.agerate"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.weight"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.animaltype"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.summary"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.price"/></p></td>
		<td><p class ="bold"><fmt:message key="label.product.quantity"/></p></td>
		<td></td>
	</tr>
		<c:forEach var="product" items="${sessionScope.products}" varStatus="iterationCount">
	<tr>
		<td>${product.id}</td>
		<td>${product.title}</td>
		<td>${product.brand}</td>
		<td>${product.form}</td>
		<td>${product.breed}</td>
		<td>${product.ageRate}</td>
		<td>${product.weight}</td>
		<td>${product.animalType}</td>
		<td>${product.summary}</td>
		<td>${product.price}</td>	
		<td>${product.quantity}</td>	 
		<td>
			<a href="<c:url value="editProduct"> 
			<c:param name="editProduct" value="post"/>
			<c:param name="producttoedit" value="${product.id}"/>
			<c:set var="fillForm" value="fillForm" scope="session"/>		
			</c:url>"><img border="0" alt="edituser" src= "${pageContext.request.contextPath}/ImageServlet?imageName=editlogo.jpg" width="30" height="30"></a>
		</td>
	
	</tr>
	</c:forEach>	
</table>	

<form action="addProduct" method="post">
	<button type="submit"><fmt:message key="label.product.addnew"/></button>
	<c:set var="action" value="addProduct" scope="session"/>
</form>	
</div>
</body>
</html>