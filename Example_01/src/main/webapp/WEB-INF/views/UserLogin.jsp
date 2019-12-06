<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 뭔진 모르겠지만 home.jsp에서 붙어있고 문제없이 작동하니까 끼워맞추기-->

<%@ page session="true" %>
<!-- 세션을 사용하는지 여부에 대한  설정
이전 페이지에서 받은 세션이 존재 = 기존세션 사용
이전 페이지에서 받은 세션이 부재 = 신규세션 생성 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "UserLoginProcess" method = "post">
		아이디 <input name="userId" type = "text"  placeholder="User ID">
		비밀번호 <input name = "userPass" type = "password" placeholder="User PASS">
		<input type="submit" value="login">로그인</button>
	</form>
</body>
</html>