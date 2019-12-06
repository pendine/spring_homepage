<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
  
    <%@ page import="java.util.HashMap"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% 
try{
	
//	HashMap userInfo = (HashMap)request.getAttribute("userInfo");
	
	//String userId = (String)userInfo.get("userId");
//	String userPass  = (String)userInfo.get("userPass");
//	int loginResult = (Integer)userInfo.get("loginResult");
	
	String userId = (String)request.getAttribute("userId");
	String userPass  = (String)request.getAttribute("userPass");
	int loginResult = (Integer)request.getAttribute("loginResult");
	
	if(loginResult ==1){
		out.println("<p>로그인 확인</p>");
		out.println("<p>어서오세요 " +userId +"</p>");
		request.setAttribute("userId",userId);
	}else if(loginResult ==0 || loginResult ==-1){
		out.println("<p>로그인 실패</p>");
		out.println("<p>아이디 또는 비밀번호 오류입니다.</p>");
		out.println("ID : "+userId );
		out.println("Password : "+userPass);
		//아이디는 존재하지만 비밀번호 오류
	}else {
		out.println("<p>로그인 실패</p>");
		out.println("<p>아이디 또는 비밀번호 오류입니다.</p>");
		out.println("ID : "+userId );
		out.println("Password : "+userPass);
	} //아이디가 없음
}catch(Exception e){
	e.printStackTrace();
}
 %>


</body>
</html>