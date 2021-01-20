<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.userslist"/></title>
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

<c:if test="${sessionScope.deactivationResult eq 'true'}">
   <div class="warning">
       <strong><fmt:message key="label.user.deactivated"/></strong>
   </div>
   <c:set var="deactivationResult" value="" scope="session"/>
   
   
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
		<td><p class ="bold"><fmt:message key="label.id"/></p></td>
		<td><p class ="bold"><fmt:message key="label.firstname"/></p></td>
		<td><p class ="bold"><fmt:message key="label.lastname"/></p></td>
		<td><p class ="bold"><fmt:message key="label.mobile"/></p></td>
		<td><p class ="bold"><fmt:message key="label.email"/></p></td>
		<td><p class ="bold"><fmt:message key="label.isadmin"/></p></td>
		<td><p class ="bold"><fmt:message key="label.isactive"/></p></td>
		<td></td>
		
	</tr>
		<c:forEach var="user" items="${sessionScope.usersList}" varStatus="iterationCount">
	<tr>
		<td>${user.id}</td>
		<td>${user.firstName}</td>
		<td>${user.lastName}</td>
		<td>${user.mobile}</td>
		<td>${user.email}</td>
		<td><c:choose>
			  <c:when test="${user.admin eq true}">
			    <fmt:message key="label.isadminstatus"/>
			  </c:when>
			  <c:otherwise>
			    <fmt:message key="label.isnotadminstatus"/>
			  </c:otherwise>
			</c:choose></td> 
		<td><c:choose>
			  <c:when test="${user.active eq true}">
			    <fmt:message key="label.isactivestatus"/>
			  </c:when>
			  <c:otherwise>
			    <fmt:message key="label.isnotactivestatus"/>
			  </c:otherwise>
			</c:choose></td>	  
		<td>
			<a href="<c:url value="deactivateuser">
			<c:param name="deactivateUser" value="post"/>
			<c:param name="userToDeactivate" value="${user.email}"/>
			</c:url>"><fmt:message key="label.deactivate"/></a>
		</td>		
	</tr>
	</c:forEach>	
</table>		
</div>
</body>
</html>