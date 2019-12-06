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

		//�ϴ� ���ڵ� �����ΰ� �;� �������̷� ���ڵ�����
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("login processing");
		//���ǻ�� & Attribute��� = ����
//		HttpSession session = request.getSession(false);
//		String userId=(String)session.getAttribute("userId");
//		String userPass = (String)session.getAttribute("userPass");
		
		//Attribute�� ��� = ����
//		String userId= (String) request.getAttribute("userId");
//		String userPass = (String)request.getAttribute("userPass");
		
		//Parameter ��� = ����.
		String userId= (String) request.getParameter("userId");
		String userPass = (String)request.getParameter("userPass");
		
		System.out.println(userId);
		System.out.println(userPass);
		int loginResult = new UserDAO().login(userId,userPass); 

		//�ؽ������� ������ ��� ����. = ����
//		HashMap info = new HashMap();
//		info.put("userId", userId);
//		info.put("userPass", userPass);
//		info.put("loginResult",new Integer(loginResult));
//		request.setAttribute("userInfo", info);
		
		
		request.setAttribute("userId", userId);
		request.setAttribute("userPass", userPass);
		request.setAttribute("loginResult", loginResult);
		
		//���ǹ� context�� ����ؼ� �����ϱ�. = ���� ��ü�� ����
//		ServletContext context = session.getServletContext();
//		RequestDispatcher dispatcher = context.getRequestDispatcher("loginResultView.jsp"); <<���⼭ ���� jspȮ���ڸ� ������ �ʾƾ��� 
																							//�����ߴ� �ڵ�鿡�� ���� ���̱⿡ 
		//���� �ٿ��� jsp��θ� ����ֱ⿡ view�������� �����ϱ� ���� ��θ� �����ϴ��� �˾����� �ƴϾ���
		//��Ʈ�ѷ��� �̿��ϱ����� �����ĸ� ����ϴ°��̹Ƿ� jspȮ���ڸ� ������ �ʾƾ���
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginResultView"); 
		try {
			dispatcher.forward(request, response);
		}catch(ServletException e) {
			response.getWriter().println(e);
		}
	}

}
