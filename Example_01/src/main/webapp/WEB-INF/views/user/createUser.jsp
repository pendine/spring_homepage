<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR" session = "false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="UserCreate" method="post">
		<div>
			id : <input name="userId" type="text" placeholder="input your id">
		</div>

		<div>
			password : <input name="userPass" type = "password" placeholder="input your password">
		</div>
		
		<div>
			text : <input name="userText" placeholder="input your text for free">
		</div>
		
		<button type="submit">create</button>
	</form>
	
	<!-- �������丮�� �������� ������̰� �̰�� ��Ʈ�ѷ��� �ּҸ� ����� �ʿ� ���� -->
<form action = "../" method = "post" >
	<input type="submit" name = "home" value = "Home"  >
</form>

</body>
</html>