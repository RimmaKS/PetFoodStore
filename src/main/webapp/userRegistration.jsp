<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.registernewuser"/></title>
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
<form action="register" method="post">

<div class="container">
	<div class="titles">
			<p><fmt:message key="title.registernewuser"/></p>
   </div>
  
    <label for="firstName"><strong><fmt:message key="label.firstname"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.firstname"/>" 
    	name="firstName"
    	required pattern="^[a-zA-Zа-яА-Я\-]+$"
    	title="<fmt:message key="label.placeholdermsg.firstname"/>" 
    	required="required">

    <label for="lastName"><strong><fmt:message key="label.lastname"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.lastname"/>" 
    	name="lastName"
    	required pattern="^[a-zA-Zа-яА-Я\-]+$"
    	title="<fmt:message key="label.placeholdermsg.lastname"/>" 
    	required="required">
    	
    <label for="email"><strong><fmt:message key="label.email"/></strong></label>
    <input type="email" 
    	placeholder="<fmt:message key="label.placeholder.email"/>"  
    	name="email"
    	required="required">	
    	
    <label for="mobile"><strong><fmt:message key="label.mobile"/></strong></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.mobile"/>"  
    	name="mobile"
		title="<fmt:message key="label.placeholdermsg.mobile"/>" 
		required pattern="[+][7][0-9]{10}"
    	required="required">	

	<label for="password"><strong><fmt:message key="label.password"/></strong></label>
	<input id="password" 
		name="password" 
		type="password" 
		pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" 
		onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="label.placeholdermsg.password"/>' : ''); if(this.checkValidity()) form.passwordConfirmation.pattern = this.value;" 
		placeholder="<fmt:message key="label.placeholder.password"/>" required>
    
    <label for="passwordConfirmation"><strong><fmt:message key="label.passwordconfirm"/></strong></label>
	<input id="passwordConfirmation" 
		name="passwordConfirmation" 
		type="password" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" 
		onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="label.placeholdermsg.passwordconfirm"/>' : '');" 
		placeholder="<fmt:message key="label.placeholder.passwordconfirm"/>" required>

<c:if test="${sessionScope.isAdmin eq 'true'}">
    <input type="checkbox" id="admin" name="admin" value="isAdmin">
    <label for="admin"><fmt:message key="checkbox.asadmin"/></label><br>
</c:if>
    <button type="submit"><fmt:message key="button.register"/></button> 
</div>

<c:if test="${sessionScope.registerStatus eq 'passwordReject'}">
   <div class="warning">
       <strong><fmt:message key="error.register.password"/></strong>
   </div>
   <c:set var="registerStatus" value="" scope="session"/>
</c:if>

<c:if test="${sessionScope.registerStatus eq 'duplicate'}">
   <div class="warning">
       <strong><fmt:message key="error.register.duplicate"/></strong>
   </div>
   <c:set var="registerStatus" value="" scope="session"/>
</c:if>

<c:if test="${sessionScope.registerStatus eq 'error'}">
   <div class="warning">
       <strong><fmt:message key="error.login.error"/></strong>
   </div>
   <c:set var="registerStatus" value="" scope="session"/>
</c:if>
</form>
</div>
</body>
</html>