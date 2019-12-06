package com.spring.example.javaPack.postPack;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostDAO
 */
public class PostDAO extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	String SQL="";
       
    /*
     * 
     * 
     * 
     * 
     * table name => posts
     * +--------------+-----------------+------+-----+-------------------+----------------+
     * | Field        | Type            | Null | Key | Default           | Extra          |
     * +--------------+-----------------+------+-----+-------------------+----------------+
     * | post_num     | int(8) unsigned | NO   | PRI | NULL              | auto_increment |
     * | post_subject | varchar(128)    | NO   |     | NULL              |                |
     * | created      | timestamp       | NO   |     | CURRENT_TIMESTAMP |                |
     * | user_Id      | varchar(12)     | NO   | MUL | NULL              |                |
     * | context      | varchar(1024)   | YES  |     | NULL              |                |
     * +--------------+-----------------+------+-----+-------------------+----------------+
     * */
    public PostDAO() {  
    	try {
    		String url = "jdbc:mysql://localhost:3306/webserver?serverTimezone=UTC";
    		String dbId = "webConn";
        	String dbPass = "qweQWE123";
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(url, dbId, dbPass);
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    }
    
    public void createPost() {

    	
    }

    public void queryClose() {
    	try {
    	rs.close();
    	pst.close();
    	conn.close();
    	}catch(SQLException e) {
    		
    	}catch(Exception e) {
    		
    	}
    }
	

}
