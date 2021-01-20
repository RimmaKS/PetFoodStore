<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
   

<body>
	<jsp:include page="header.jsp" />	
	<jsp:include page="footer.jsp" />
	
<br>

<div class="page-container"> 	

<a href="<c:url value="index.jsp"> 
</c:url>"><img border="0" alt="mainpic" class="center-pic" src= '<c:url value="${initParam['imageLocation']}main_logo.png"/>'></a>
</div>
</body>
</html>