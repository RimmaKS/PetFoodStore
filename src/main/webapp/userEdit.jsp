<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.edituser"/></title>
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
		
<form action="userdataedit" method="post">

<div class="titles">
			<p><fmt:message key="title.edituser"/></p>
</div>

  <div class="container">
    <label for="firstName"><b><fmt:message key="label.firstname"/></b></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.firstname"/>" 
    	name="firstName"
    	value="${sessionScope.userData.firstName}"
    	required pattern="^[a-zA-Zа-яА-Я\-]+$"
    	title="<fmt:message key="label.placeholdermsg.firstname"/>" 
    	required="required">


    <label for="lastName"><b><fmt:message key="label.lastname"/></b></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.lastname"/>" 
    	name="lastName"
    	value="${sessionScope.userData.lastName}"
    	required pattern="^[a-zA-Zа-яА-Я\-]+$"
    	title="<fmt:message key="label.placeholdermsg.lastname"/>" 
    	required="required">
    	
    <label for="email"><b><fmt:message key="label.email"/></b></label>
    <input type="email" 
    	placeholder="<fmt:message key="label.placeholder.email"/>" 
    	name="email"
    	value="${sessionScope.userData.email}"
    	required="required">	
    	
    <label for="mobile"><b><fmt:message key="label.mobile"/></b></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.placeholder.mobile"/>"  
    	name="mobile"
    	value="${sessionScope.userData.mobile}"
		title="<fmt:message key="label.placeholdermsg.mobile"/>" 
		required pattern="[+][7][0-9]{10}"
    	required="required">	  	

    	
	<label for="password"><b><fmt:message key="label.password"/></b></label>
	<input id="password" 
		name="password" 
		type="password" 
		pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" 
		onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="label.placeholdermsg.password"/>' : ''); if(this.checkValidity()) form.passwordconfirmation.pattern = this.value;" 
		placeholder="<fmt:message key="label.placeholder.password"/>" required>
    
    <label for="passwordconfirmation"><b><fmt:message key="label.passwordconfirm"/></b></label>
	<input id="passwordconfirmation" 
		name="passwordconfirmation" 
		type="password" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" 
		onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="label.placeholdermsg.passwordconfirm"/>' : '');" 
		placeholder="<fmt:message key="label.placeholder.passwordconfirm"/>" required>
    <button type="submit"><fmt:message key="button.update"/></button> 
  </div>

</form>

</div>
</body>
</html>