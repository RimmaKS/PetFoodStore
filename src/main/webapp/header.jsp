<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<div class="topnav">
  <a class="active" href="index.jsp"><fmt:message key="label.homepage"/></a>
  <a href="<c:url value="productshow"><c:param name="productshow" value="post"/></c:url>"><fmt:message key="label.products"/></a>   
  <a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a>
  <a href="?sessionLocale=ru"><fmt:message key="label.lang.ru" /></a>
</div>