package com.spring.example.javaPack.userPack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.example.javaPack.QueryGadjet;

import com.spring.example.javaPack.userPack.UserDAO;

/**
 * Servlet implementation class UserCreate
 */
public class UserCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * User ID identifier = userId
	 * User PASSWORD identifier = userPass
	 *  
	 * 
	 * 
	 * table name => auth_user 
	 * +---------------+--------------+------+-----+---------+-------+
	 * | Field         | Type         | Null | Key | Default | Extra |
	 * +---------------+--------------+------+-----+---------+-------+
	 * | User_Create   | date         | NO   |     | NULL    |       |
	 * | User_Id       | varchar(12)  | NO   | PRI | NULL    |       |
	 * | User_Password | varchar(24)  | NO   |     | NULL    |       |
	 * | TEXT          | varchar(128) | YES  |     | NULL    |       |
	 * +---------------+--------------+------+-----+---------+-------+
	 * */
	
	private Connection conn;
	private PreparedStatement pst;
//	private ResultSet rs; 여기에서는 insert문을 사용하니 update를 사용하니 에초에 사용할일 없었음. 오류원인
	private String createdDay;
	private UserDAO userCheckInstance = new UserDAO();
	String SQL;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserCreate() {
		super();
		// TODO Auto-generated constructor stub
		
		
	}
	
	private void dbConnect() {
		try {
        	String dbURL = "jdbc:mysql://localhost:3306/webserver?serverTimezone=UTC";
        	//DB별 관리ID별도 분류
        	String dbId = "webConn";
        	String dbPass = "qweQWE123";
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection(dbURL, dbId, dbPass);
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPush(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		dbConnect();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");
		String userText = request.getParameter("userText");

		String result = "";
		
		setCreatedDay();
		SQL = "INSERT IGNORE INTO auth_user ( User_Id , User_Password , TEXT ) VALUES ( ?,?,?);";

		System.out.println("Today time = " +  getCreatedDay());
		
		System.out.println("before User id : " + userId);
		System.out.println("before User pass : " + userPass);
		System.out.println("before User Text : " + userText);

		userId = QueryGadjet.injectionDetect(userId);
		userPass = QueryGadjet.injectionDetect(userPass);
		userText = QueryGadjet.textInjectionDetect(userText);

		System.out.println("after & input user id : " + userId);
		System.out.println("after & input user pass : " + userPass);
		System.out.println("after & input User Text : " + userText);

		try {
			pst = conn.prepareStatement(SQL);
			System.out.println("DB connected");
 
			pst.setString(1, userId);
			pst.setString(2, userPass);
			pst.setString(3, userText);
			System.out.println("SQL setting complete");
			System.out.println(pst);
			System.out.println(SQL);
			
			int effected = pst.executeUpdate();
			System.out.println("Query Execute");
			
			if (effected >= 1) {
				result = "user create success";
				System.out.println("user create success");
			}else {
				result = "user create fail. please try again or contect to admin";
				System.out.println("user create fail");
			}
		}catch (SQLIntegrityConstraintViolationException e){ 
			result = "user create fail </br> The same user id already exists on the web server";
			System.out.println(e);
			queryClose();
		}catch (SQLException e) {
			result = "user create fail. please try again or contect to admin";
			System.out.println("user create fail");
			System.out.println(e);
			queryClose();
		} catch (Exception e) {
			result = "user create fail. please try again or contect to admin";
			System.out.println("user create fail");
			System.out.println(e);
			queryClose();
		}
		queryClose();
		request.setAttribute("result", result);
		request.setAttribute("userId", userId);
		request.setAttribute("TEXT", userText);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createUserResult");
		try {
			dispatcher.forward(request, response);
		}catch(ServletException e) {
			response.getWriter().println(e);
		}
	}

	private void queryClose(){
		try {
		SQL = "";
//		rs.close(); update를 사용하니 에초에 사용할일 없었음. 오류원인
		pst.close();
		conn.close();
		}catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void setCreatedDay( ) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("YYYY-MM-DD");
		String date = format1.format(cal.getTime());
		System.out.println(cal.getTime());
		this.createdDay = date;
	}
    public String getCreatedDay() {
		return createdDay;
	}
}
