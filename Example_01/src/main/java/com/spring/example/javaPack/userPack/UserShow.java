package com.spring.example.javaPack.userPack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserShow
 */
public class UserShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO userList = new UserDAO();
		String[] showUsers = userList.showUserList();
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("userList", showUsers);
		
		//jsp�� ȣ���Ҷ��� ��Ʈ�ѷ��� ȣȯ�ؼ� ����Ұ�.
		//��Ʈ�ѷ����� ���� ���丮�� ��θ� ������ jsp ��ΰ� �������ִٸ� dispatcher������ ȣ���� jsp�� ������. <= �ƴ� ȣ���� jsp�̸��� ������ 
		RequestDispatcher dispatcher = request.getRequestDispatcher("showAllUser");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
