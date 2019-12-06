<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" session = "true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="UserDelete" method="post">
		<input type="hidden" name="_method" value="delete">
		
		<div>
			id : <input type ="text" name="userId" placeholder="input your id">
		</div>

		<div>
			password : <input type="password" name="userPass" placeholder="input your password">
		</div>
		
		<button type="submit">delete</button>
	</form>
	
	<form action = "../" method = "post" >
		<input type="submit" name = "home" value = "Home"  >
	</form>
</body>
</html>