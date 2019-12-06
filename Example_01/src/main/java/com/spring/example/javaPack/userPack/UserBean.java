package com.spring.example.javaPack.userPack;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Servlet implementation class User
 */
public class UserBean{
	private String id;
	private String password;
	private String text;
	private String createdDay;
	private String createdTime;
	
	
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
		String date = format1.format(cal.getTime());
		this.createdTime = date;
	}
	public String getCreatedDay() {
		return createdDay;
	}
	public void setCreatedDay( ) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
		String date = format1.format(cal.getTime());
		this.createdDay = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	
   
}
