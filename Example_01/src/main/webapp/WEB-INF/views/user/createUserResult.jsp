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
String TEXT = (String)request.getAttribute("TEXT");
// out.println(result + "</br>");
// out.println(userId + "</br>");
%>
</br>
${result}
</br>
${userId}
</br>
${TEXT}

	<!-- 상위디렉토리로 가기위한 상대경로이고 이경우 컨트롤러에 주소를 등록이 필요 없음 -->
<form action = "../" method = "post" >
	<input type="submit" name = "home" value = "Home"  >
</form>

</body>
</html>