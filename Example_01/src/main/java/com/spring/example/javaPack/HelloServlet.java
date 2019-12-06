package com.spring.example.javaPack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

//import java.io.*;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("get ���");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		htmlStart(pw);
		pw.println("GET");
		
		htmlEnd(pw);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post ���");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		htmlStart(pw);
		pw.println("POST");
		try {
		databaseConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		htmlEnd(pw);
		
	}
	
	protected void htmlStart(PrintWriter out) {
		out.println("<html>");		
		out.println("<body>");
	}
	protected void htmlEnd(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}
	
	
	// db����� �����߻����� = exception ó�� �������	
	protected void databaseConn() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webserver?serverTimezone=UTC","neokin","dSqeWa135!#%");
		
		//������
		String query = "select * ";
		String from = "from auth_user";
		
		//java statement ����
		Statement st = conn.createStatement();
		
		//���� execute ��ü����
		ResultSet rs = st.executeQuery(query+from);
		
		//�� �÷��� ������ ���
		while(rs.next()){
			String id = rs.getString("User_Id");
			String text = rs.getString("TEXT");
			System.out.format("id : %s  text : %s \n", id, text);
			
		}
		
		//java statement �ݱ�
		st.close();
	}
}
