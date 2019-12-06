<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%

String result = (String)request.getAttribute("result");
String userId = (String)request.getAttribute("userId");
%>
{$result}
{$userId}
<form action ="../" method = "post">
	<input type = "submit"  value = "home">
</form>

</body>

</html>