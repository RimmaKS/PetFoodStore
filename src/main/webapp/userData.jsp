<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.accountinfo"/></title>
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
			<td><p class ="bold"><fmt:message key="label.firstname"/></p></td>
			<td><p class ="bold"><fmt:message key="label.lastname"/></p></td>
			<td><p class ="bold"><fmt:message key="label.mobile"/></p></td>
			<td><p class ="bold"><fmt:message key="label.email"/></p></td>
			<td><p class ="bold"><fmt:message key="label.edit"/></p></td>
		</tr>

		<tr>
			<td>${sessionScope.userData.firstName}</td>
			<td>${sessionScope.userData.lastName}</td>
			<td>${sessionScope.userData.mobile}</td>
			<td>${sessionScope.userData.email}</td>
		<td>
			<a href="userEdit.jsp"> <img border="0" alt="edituser" src= '<c:url value="${initParam['imageLocation']}editlogo.jpg"/>' width="30" height="30"></a>
		</td>		
		</tr>
</table>		
</div>
</body>
</html>