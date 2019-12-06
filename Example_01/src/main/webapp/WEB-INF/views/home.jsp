<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import = "javax.servlet.http.HttpSession"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<%
HttpSession session = request.getSession();
if(session.getAttribute("userId")!= null){
	String userId = (String)session.getAttribute("userId");
	out.println("<h1> Hellow user:"+"userId </h1>"); 
}

%>

<P>  The time on the server is ${serverTime}. </P>
<P>  The server say ${serverSay}. </P>

<form action = "HelloServlet" method = "get" >
	<input type="submit" name = "get_test" value = "GET Button"  >
</form>

<form action = "HelloServlet" method = "post" >
	<input type="submit" name = "post_test" value = "Post Button" >
</form>

<form action = "user/userLogin"  method = "post" >
	<input type="submit" name = "userLogin" value = "UserLogin">
</form>

<form action = "user/createUser" method = "post">
	<input type ="submit" name = "createNewUser" value="CreateUser" >
</form>

<form action = "user/deleteUser" method = "post">
	<input type ="submit" name = "deleteUser" value="deleteUser" >
</form>

<form action = "user/userShow" method = "get">
	<input type ="submit" name = "showUser" value="showUser" >
</form>


</body>
</html>
