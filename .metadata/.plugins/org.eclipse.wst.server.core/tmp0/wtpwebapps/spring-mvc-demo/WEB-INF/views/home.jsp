<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h2>welcome to the home page of spring-demo Mr.${user }</h2>
 
 <h3><a href="open-view?view=sum">Click here to find the sum of two numbers</a></h3>
 <h3><a href="open-view?view=diff">Click here to find the diff of two numbers</a></h3>
 <h3><a href="open-view?view=product">Click here to find the product of two numbers</a></h3>
 <h3><a href="open-view?view=getQuotient">Click here to find the Quotient of two numbers</a></h3>
 <h3><a href="open-view?view=getRemainder">Click here to find the remainder between two numbers</a></h3>
 <h2><a href="open-register">Click here to register</a></h2> 
</body>
</html>