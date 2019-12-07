package com.spring.example.javaPack.userPack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.example.javaPack.QueryGadjet;

import com.spring.example.javaPack.userPack.UserDAO;

/**
 * Servlet implementation class UserCreate
 */
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * User ID identifier = userId User PASSWORD identifier = userPass
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
	private UserDAO userInstance = new UserDAO();
	private String SQL = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDelete() {
		super();
		// TODO Auto-generated constructor stub
		
		
		
	}

	/**
	 * @see HttpServlet#doPush(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dbConnect();
		// 나중에 세션과 쿠키를 사용해서 로그인 유저에게 정보를 받을것.
//		Cookie[] userInfoCookies = request.getCookies();
//
//		String requestSessionId = String.valueOf(request.getAttribute("userId"));
//		String requestCookieId = String.valueOf(userInfoCookies[0].getValue());
//		
		String result = "";

		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");

		
		
		if (userInstance.userCheck(userId, userPass)) {
			SQL = "DELETE FROM auth_user WHERE User_Id = ? AND User_Password = ?;";

			
			//어떻게서든 특수문자가 삽입되는것을 막기위한 정적메소드 사용과
			//입,출력돠는 인수를 확인해서 특수문자가 삽입됬는지, 걸러졌는지 확인.
			System.out.println("before User id : " + userId);
			System.out.println("before User password : " + userPass);

			userId = QueryGadjet.injectionDetect( userId);
			userPass = QueryGadjet.injectionDetect( userPass);

			System.out.println("after & input User id : " + userId);
			System.out.println("after & input User password : " + userPass);

			try {
				pst = conn.prepareStatement(SQL);
				System.out.println("DB connected");

				pst.setString(1, userId);
				pst.setString(2, userPass);
				System.out.println("SQL setting complete");

				int resultRow = pst.executeUpdate();
				System.out.println("Query Execute");


				//delete, update, insert 여부 관계없이 
				//영향받은 레코드의 수는 양수로 나옴
				//따라서, delete 일 경우 
				//성공적으로 수행됬다는 결과로 
				//-1 을 반환한다는것은
				//틀렸음
				if (resultRow == 1) {
					System.out.println(resultRow);
					System.out.println("user delete success");
					result = "user delete success";
				} else {
					System.out.println("user delete fail <br/> check id/password");
					
					result = "user delete fail <br/> check id/password";
				}
				
				queryClose();
				
			} catch (SQLException e) {
				queryClose();
				System.out.println(e);
			} catch (Exception e) {
				queryClose();
				System.out.println(e);
			}
		} else {
			System.out.println("not exist user id / password combination");
			userId = userId;
			result = "not exist user id / password combination <br> user delete fail <br/> check id/password";
		}

		request.setAttribute("userId", userId);
		request.setAttribute("result", result);

		RequestDispatcher dispatcher = request.getRequestDispatcher("deleteUserResult");
		dispatcher.forward(request, response);

	}

	private void queryClose() {
		try {
		pst.close();
		conn.close(); 
		SQL = "";
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}catch( Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
