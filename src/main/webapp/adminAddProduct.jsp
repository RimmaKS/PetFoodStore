<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<title><fmt:message key="label.product.addnew"/></title>
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
		
<form action=addproduct method="post">
<div class="titles">
	<p><fmt:message key="label.product.addnew"/></p>
</div>

  <div class="container">
    <label for="producttitle"><b><fmt:message key="label.product.title"/></b></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.product.title"/>" 
    	name="producttitle"
    	required pattern="^[a-zA-Z0-9 \-]+$"
    	required="required">
    	
    	
    <label for="productsummary"><b><fmt:message key="label.product.summary"/></b></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.product.summary"/>"  
    	name="productsummary"
		required pattern="^[a-zA-Z0-9 \-]+$"
    	required="required">
    	
    <label for="productquantity"><b><fmt:message key="label.product.quantity"/></b></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.product.quantity"/>"  
    	name="productquantity"
		required pattern="^[0-9]+$"
    	required="required">
    	
    <label for="productprice"><b><fmt:message key="label.product.price"/></b></label>
    <input type="text" 
    	placeholder="<fmt:message key="label.product.price"/>"  
    	name="productprice"
		required pattern="^[0-9\.]+$"
    	required="required">	
    	
    <br><label for="productbrand"><b><fmt:message key="label.product.brand"/></b></label>
	    <select class="container" name="productbrand" id="productbrand">
	    <option value="No brand">No brand</option>
	    <option value="Royal Canin">Royal Canin</option>
	    <option value="Pro Plan">Pro Plan</option>
	    <option value="Monge">Monge</option>
	    </select>	
	    
	 <br><label for="productform"><b><fmt:message key="label.product.form"/></b></label>   
	    <select class="container" name="productform" id="productform">
	    <option value="dry">Dry</option>
	    <option value="wet">Wet</option>
	    </select>
	    
	  <br><label for="productbreed"><b><fmt:message key="label.product.breed"/></b></label>   
	    <select class="container" name="productbreed" id="productbreed">	    
		<option value="All">All</option>
		<option value="Abyssinian">Abyssinian</option>
		<option value="Bengal">Bengal</option>
		<option value="Burmese">Burmese</option>
		<option value="Burmilla">Burmilla</option>
		<option value="Egyptian Mau">Egyptian Mau</option>
		<option value="German Rex">German Rex</option>
		<option value="Havana">Havana</option>
		<option value="Oriental cat">Oriental cat</option>
		<option value="Persian">Persian</option>
		<option value="Siamese">Siamese</option>
		<option value="Maine Coon">Maine Coon</option>
		<option value="Norwegian Forest Cat">Norwegian Forest Cat</option>
		<option value="Somali">Somali</option>
		<option value="Turkish Angora">Turkish Angora</option>
		<option value="Turkish Van">Turkish Van</option>
		<option value="Siberian">Siberian</option>
		<option value="Golden Retriever">Golden Retriever</option>
		<option value="Chihuahua">Chihuahua</option>
		<option value="Dachshund">Dachshund</option>
		<option value="Poodle">Poodle</option>
		<option value="Yorkshire Terrier">Yorkshire Terrier</option>
		</select>    
	    
	   <br><label for="productage"><b><fmt:message key="label.product.agerate"/></b></label>
	   <select class="container" name="productage" id="productage">
	   <option value="mature">Mature</option>
	   <option value="senior">Senior</option>
	   <option value="kitten">Kitten</option>
	   <option value="adult">Adult</option>
	   </select>   
	   
	   <br><label for="productanimaltype"><b><fmt:message key="label.product.animaltype"/></b></label>
	   <select class="container" name="productanimaltype" id="productanimaltype">
	   <option value="any">Any</option>
	   <option value="cat">Cat</option>
	   <option value="dog">Dog</option>   
	   </select>
	   
	   <br><label for="productweight"><b><fmt:message key="label.product.weight"/></b></label>
	   <select class="container" name="productweight" id="productweight">
	   <option value="2.5">2.5 lb</option>
		<option value="3">3 lb</option>
		<option value="5.5">5.5 lb</option>
		<option value="6">6 lb</option>
		<option value="7">7 lb</option>
		<option value="14">14 lb</option>
		<option value="15">15 lb</option>
		<option value="3">3 oz</option>
		<option value="5.8">5.8 oz</option>
		<option value="12">12 oz</option>
		<option value="0.4">0.4 kg</option>
		<option value="1.5">1.5 kg</option>
		<option value="10">10 lb</option>
		<option value="17">17 lb</option>
		<option value="30">30 lb</option>
	   </select>
    <button type="submit"><fmt:message key="label.product.addnew"/></button>
    
  </div>

</form>

</div>
</body>
</html>