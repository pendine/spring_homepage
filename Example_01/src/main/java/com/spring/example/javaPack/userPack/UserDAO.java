package com.spring.example.javaPack.userPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.example.javaPack.QueryGadjet;


/**
 * Servlet implementation class UserDAO
 */
@SessionAttributes("id")
public class UserDAO  extends HttpServlet{
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
	private ResultSet rs;
	String SQL = "";
	
    public UserDAO() {
        try {
        	String dbURL = "jdbc:mysql://localhost:3306/webserver?serverTimezone=UTC";

        	//�����ð� �̼����� ���ӿ��� �߻�
        	//String dbURL = "jdbc:mysql://localhost:3306/webserver";
        	
        	//db������ ��ȯ id ������
        	//DB�� ����ID���� �з�
        	String dbId = "webConn";
        	String dbPass = "qweQWE123";
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection(dbURL, dbId, dbPass);
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    
    
    public String[] showUserList() {
    	SQL = "SELECT User_Id FROM auth_User ";
    	ArrayList<String> users = new ArrayList<String>();
    	      	
    	try {
    		pst = conn.prepareStatement(SQL);
    		System.out.println("DB connected");
 	
    		System.out.println("SQL setting complete");   		
    		rs = pst.executeQuery();
    		System.out.println("Query Execute");
    		
    		while(rs.next()) {
    			String userID = rs.getString("User_Id");
				if (userID != null) {
//					System.out.println(userID);
					users.add(userID);
				}
    		}
    		queryClose();
    		
    	}catch(Exception e) {
    		System.out.println(e);
    		SQL = "";
    	}
    	
    	String[] userIdList = new String[users.size()];
    	for(int i=0; i<users.size(); i++) {
    		//�ܼ� ��� �յڰ� �ٲ���� ���� �ϳ��� ���� null�� ��µǰ��־��� 
    		userIdList[i] = users.get(i);
//    		System.out.println(userIdList[i]);

    	}
    	
    	return userIdList;
    }
    
    
    /* user exist = return true
     * user not exist = return false
    */
    public boolean userCheck(String user) {
    	SQL = "SELECT * FROM auth_User WHERE User_Id = ?";
    	
    	user =QueryGadjet.injectionDetect(user);
    	
    	System.out.println("input id : "+user);
      	
    	try {
    		pst = conn.prepareStatement(SQL);
    		System.out.println("DB connected");
    		
    		pst.setString(1, user);
    		System.out.println("SQL setting complete");
    		
    		rs = pst.executeQuery();
    		System.out.println("Query Execute");
    		
    		while(rs.next()) {
    			String userId = rs.getString("User_Id");
    			if(userId.equals(user)) {
    				System.out.println("id exists");
    				return true;
    			}
    		}
    		
    		queryClose();
    		
    	}catch(Exception e) {
    		System.out.println(e);
    		SQL = "";
    	}
    	return false;
    }
    
    /* user id & user pass correct = return true
     * user id or user pass not correct = return false
    */
    public boolean userCheck(String user, String userPass) {
    	SQL = "SELECT * FROM auth_User WHERE User_Id = ? AND User_Password = ?";
    	
    	user =QueryGadjet.injectionDetect(user);
    	userPass =QueryGadjet.injectionDetect(userPass);
    	
    	System.out.println("input id : "+user);
    	System.out.println("input id : "+userPass);
      	
    	try {
    		pst = conn.prepareStatement(SQL);
    		System.out.println("DB connected");
    		
    		pst.setString(1, user);
    		pst.setString(2, userPass);
    		System.out.println("SQL setting complete");
    		
    		rs = pst.executeQuery();
    		System.out.println("Query Execute");
    		
    		while(rs.next()) {
    			String userId = rs.getString("User_Id");
    			String UserPass = rs.getString("User_Password");
    			if(userId.equals(user)&&UserPass.equals(userPass)) {
    				System.out.println("id & pass correct");
    				return true;
    			}else {
    				return false;
    			}
    		}
    		
    		queryClose();
    		
    	}catch(Exception e) {
    		System.out.println(e);
    		SQL = "";
    	}
    	return false;
    }
    
    
    /* User Login method
     * login success => return 1
     * login fail => id fail => -1
     * 				 password fail => 0
     * error => -2 
     * */
    public int login(String userId, String userPass) {
    	SQL = "SELECT * FROM auth_User WHERE User_Id = ?";
    	
    	System.out.println("before id : "+userId);
    	System.out.println("before pass : "+userPass);
      	
    	userId =QueryGadjet.injectionDetect(userId);
    	userPass =QueryGadjet.injectionDetect(userPass);
	
    	System.out.println("after User id : " + userId);
    	System.out.println("after User pass : "  + userPass);
    	
    	System.out.println("input user id : "+ userId);
    	System.out.println("input user pass : "+ userPass);
    	
    	try {
    		pst = conn.prepareStatement(SQL);
    		System.out.println("DB connected");
    		
    		pst.setString(1, userId);
    		System.out.println("SQL setting complete");
    		
    		rs = pst.executeQuery();
    		System.out.println("Query Execute");
    		
    		while(rs.next()) {
//    			Timestamp created = rs.getTimestamp("User_Created");
    			String id = rs.getString("User_Id");
    			String pass = rs.getString("User_Password");
    			String text = rs.getString("TEXT");
    		
    		
    			if(id.equals(userId)) {
    				System.out.println("���̵� ��ġ");
    				if(pass.equals(userPass)) {
    					System.out.println("��й�ȣ ��ġ");
    					System.out.println("�α���");
    					System.out.println("correct");
    					System.out.println("ID : " + id +" PASS : " +pass);
    					System.out.format("user id : %s \n user pass : %s \n users text : %s",id,pass,text);
    					System.out.println(1);
    					return 1; //�α��� ����
    				} else {
    					System.out.println("��й�ȣ ����ġ");
    					System.out.println("not correct");
    					System.out.println(0);
    					return 0; //��й�ȣ ����ġ
    				}
    			}else {
    				//���̵� Ʋ�ȴٸ� ���̺� �������� �����Ƿ� �ٷ� empty set���� ��ȯ�Ǳ� ������
    				//�� ������ �۵����� ����
    				System.out.println(-1);
    				System.out.println("���̵� ��Ȯ�� �ʿ�");
    				return -1; //���̵� ��ȿ
    			}
    		}
    		
    		queryClose();
    		
    	}catch(SQLException e) {
    		System.out.println(e);
    		System.out.println("SQL ���ܹ߻�");
    		SQL = "";
    	}catch(Exception e) {
    		e.printStackTrace();
    		SQL = "";
    	}
    	System.out.println(-2);
    	//DB����
    	//DB�������� �����ϰ� �ۼ��� �������� 
    	//�������� �ʴ� ���̵� �Է¹޾����� ���� �۵���
    	return -2; 
    }
    
    private void queryClose() throws Exception{
    	rs.close();
		pst.close();
		conn.close();
		SQL = "";
    }
    
    
    
}
