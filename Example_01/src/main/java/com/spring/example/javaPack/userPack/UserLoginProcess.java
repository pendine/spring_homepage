package com.spring.example.javaPack.userPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.*;

/**
 * Servlet implementation class UserLogin
 */

//@WebServlet("/UserLoginProcess")
public class UserLoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//일단 인코딩 문제인가 싶어 마구잡이로 인코딩세팅
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("login processing");
		//세션사용 & Attribute사용 = 오류
//		HttpSession session = request.getSession(false);
//		String userId=(String)session.getAttribute("userId");
//		String userPass = (String)session.getAttribute("userPass");
		
		//Attribute만 사용 = 오류
//		String userId= (String) request.getAttribute("userId");
//		String userPass = (String)request.getAttribute("userPass");
		
		//Parameter 사용 = 성공.
		String userId= (String) request.getParameter("userId");
		String userPass = (String)request.getParameter("userPass");
		
		System.out.println(userId);
		System.out.println(userPass);
		int loginResult = new UserDAO().login(userId,userPass); 

		//해쉬맵으로 데이터 묶어서 전달. = 오류
//		HashMap info = new HashMap();
//		info.put("userId", userId);
//		info.put("userPass", userPass);
//		info.put("loginResult",new Integer(loginResult));
//		request.setAttribute("userInfo", info);
		
		
		request.setAttribute("userId", userId);
		request.setAttribute("userPass", userPass);
		request.setAttribute("loginResult", loginResult);
		
		//세션및 context를 사용해서 전달하기. = 전달 자체가 오류
//		ServletContext context = session.getServletContext();
//		RequestDispatcher dispatcher = context.getRequestDispatcher("loginResultView.jsp"); <<여기서 오류 jsp확장자를 붙이지 않아야함 
																							//참조했던 코드들에서 전부 붙이기에 
		//따라서 붙였고 jsp경로를 집어넣기에 view폴더에서 참조하기 위해 경로를 설정하는줄 알았지만 아니었고
		//컨트롤러를 이용하기위해 디스패쳐를 사용하는것이므로 jsp확장자를 붙이지 않아야함
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginResultView"); 
		try {
			dispatcher.forward(request, response);
		}catch(ServletException e) {
			response.getWriter().println(e);
		}
	}

}
