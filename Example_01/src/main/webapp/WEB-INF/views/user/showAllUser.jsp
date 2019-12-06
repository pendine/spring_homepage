<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
String[] users = (String[])request.getAttribute("userList");
try{
	out.println("<h2>now exist in database user list </h2> </br>");
for(int i=0; i< users.length; i++){
	out.println((i+1) + ".  " + users[i]);
	out.println("</br>");
}
}catch(NullPointerException e){
	out.println(e);
}catch(Exception e){
	out.println(e);
}
%>
<form action = "../" method ="post">
	<input type="submit" value=" go to home" name = "home">
</form>
</body>
</html>