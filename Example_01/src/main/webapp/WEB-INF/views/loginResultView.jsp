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
		out.println("<p>�α��� Ȯ��</p>");
		out.println("<p>������� " +userId +"</p>");
		request.setAttribute("userId",userId);
	}else if(loginResult ==0 || loginResult ==-1){
		out.println("<p>�α��� ����</p>");
		out.println("<p>���̵� �Ǵ� ��й�ȣ �����Դϴ�.</p>");
		out.println("ID : "+userId );
		out.println("Password : "+userPass);
		//���̵�� ���������� ��й�ȣ ����
	}else {
		out.println("<p>�α��� ����</p>");
		out.println("<p>���̵� �Ǵ� ��й�ȣ �����Դϴ�.</p>");
		out.println("ID : "+userId );
		out.println("Password : "+userPass);
	} //���̵� ����
}catch(Exception e){
	e.printStackTrace();
}
 %>


</body>
</html>