<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="title.login"/></title>
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
     <div class="titles">
			<p><fmt:message key="title.login"/></p>
   </div>
  	
<form action="login" method="post">
  <div class="container">
    <label for="email"><b><fmt:message key="label.email"/></b></label>
    <input type="email" 
    	placeholder="<fmt:message key="label.placeholder.email"/>" 
    	name="email" required>

    <label for="password"><b><fmt:message key="label.password"/></b></label>
    <input type="password" 
    	placeholder="<fmt:message key="label.placeholder.password"/>" 
    	name="password" required>

<button type="submit"><fmt:message key="button.login"/></button>
 
 <c:if test="${sessionScope.loginStatus eq 'userNotActive'}">
   <div class="warning">
       <strong><fmt:message key="error.login.notactive"/></strong>       
   </div>   
   <c:set var="loginStatus" value="" scope="session"/>
</c:if>

 <c:if test="${sessionScope.loginStatus eq 'incorrectCredentials'}">
   <div class="warning">
       <strong><fmt:message key="error.login.incorrect"/></strong>       
   </div>   
   <c:set var="loginStatus" value="" scope="session"/>
</c:if>


 <c:if test="${sessionScope.loginStatus eq 'loginError'}">
   <div class="warning">
       <strong><fmt:message key="error.login.error"/></strong>       
   </div>   
   <c:set var="loginStatus" value="" scope="session"/>
</c:if>

 <c:if test="${sessionScope.registerStatus eq 'success'}">
   <div class="warning">
       <strong><fmt:message key="notify.registration.success"/></strong>       
   </div>   
   <c:set var="registerStatus" value="" scope="session"/>
</c:if>

 <c:if test="${sessionScope.registerStatus eq 'success'}">
   <div class="warning">
       <strong><fmt:message key="notify.registration.success"/></strong>       
   </div>   
   <c:set var="registerStatus" value="" scope="session"/>
</c:if>


<c:if test="${sessionScope.cartNotAllowed eq 'true'}">
   <div class="warning">
       <strong><fmt:message key="error.addtocart"/></strong>       
   </div>   
   <c:set var="cartNotAllowed" value="" scope="session"/>

</c:if>

  </div>

</form>

</div>
</body>
</html>