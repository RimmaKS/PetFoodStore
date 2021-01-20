<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login Page</title>

<style>
   <%@include file='css/style.css' %>
</style>
   
</head>

<body>
	<jsp:include page="header.jsp" />
<br>
		
<form action="addproduct" method="post">
	<h2>
	Add New Product
	</h2>

  <div class="container">
  <label for="brand">Choose brand:</label>
  <select name="brand" id="brand">
    <option value="Monge">Monge</option>
    <option value="saab">Saab</option>
    <option value="opel">Opel</option>
    <option value="audi">Audi</option>
  </select>




    <label for="lastName"><b>Last Name</b></label>
    <input type="text" 
    	placeholder="Enter Last Name" 
    	name="lastName"
    	required pattern="^[a-zA-Z\-]+$"
    	title="Please enter only letters." 
    	required="required">
    	
    <label for="email"><b>Email</b></label>
    <input type="email" 
    	placeholder="Enter Email" 
    	name="email"
    	required="required">	
    	
    <label for="mobile"><b>Mobile</b></label>
    <input type="text" 
    	placeholder="Enter Last Name" 
    	name="mobile"
		title="Please enter number in +77001234567 format."
		required pattern="[+][7][0-9]{10}"
    	required="required">	
    	
    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <button type="submit">Register</button> 
  </div>

  <div class="container">
    <button type="button" class="cancelbtn">Cancel</button>

  </div>
</form>


</body>
</html>