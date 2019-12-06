package com.spring.example;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.annotation.*;

/**
 * Handles requests for the application home page.
 */
@Controller
//@WebServlet("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String serverSay = "Test Text";
		
		System.out.println(locale.getCountry());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String serverString = serverSay;
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("serverSay", serverString );
		
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home2(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String serverSay = "Test Text";
		
		System.out.println(locale.getCountry());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String serverString = serverSay;
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("serverSay", serverString );
		return "home";
	}
	//�������� : ���ڰ��� ����
//	@RequestMapping(value = "/UserLogin" , method = RequestMethod.POST)
//	public String intoUserLogin() {
//		
//		return "redirect:UserLogin";
//	}
	
	
	//�α��� â ��Ī
	@RequestMapping(value = "/user/userLogin" , method = RequestMethod.POST)
	public String intoUserLogin(Locale locale, Model model) {
		
		return "UserLogin";
	}
	
	//������̼ǿ��� �޾Ƽ� ó�����ִ� GET, POST����� �޶� �޼ҵ� �̸�, ���ڰ� ���� ������ �޼ҵ��� �����ִ� �޼ҵ常 �ν���
	//���� �޴� �Ӽ����� ������ ���ٸ� �ٸ� �޼ҵ� �̸��� ����������
//	@RequestMapping(value = "/UserLogin" , method = RequestMethod.GET)
//	public String intoUserLogin(Locale locale, Model model) {
//		
//		return "UserLogin";
//	}
	
//	@RequestMapping(value = "/user/UserLoginProcess" , method = RequestMethod.POST)
//	public String intoUserLoginProcess(Locale locale, Model model) {
//		
//		return "/UserLoginProcess";
//	}

	
	
	@RequestMapping(value = "/user/createNewUser", method = RequestMethod.PUT)
	public String createUser(Locale locale, Model model) {
		return "UserCreate";
	}
	
//	@RequestMapping(value = "/user/deleteUser", method = RequestMethod.DELETE)
//	public String deleteUser(@RequestParam(name="userId") String userId, @RequestParam(name="userPass") String userPass, Model model) {
//		model.addAttribute("userId", userId);
//		model.addAttribute("userPass", userPass);
//		
//		return "user/deleteUser";
//	}
	
	//==========================jsp ����==============================
	@RequestMapping(value = "/user/loginResultView" , method = RequestMethod.POST)
	public String jspLoginResult(Locale locale, Model model) {
		return "loginResultView";
	}
	@RequestMapping(value = "/user/createUser" , method = RequestMethod.POST)
	public String jspCreateUser(Locale locale, Model model) {
		return "user/createUser";
	}
	@RequestMapping(value = "/user/createUserResult" , method = RequestMethod.POST)
	public String jspCreateResult(Locale locale, Model model) {
		return "user/createUserResult";
	}
	@RequestMapping(value = "/user/deleteUser", method = RequestMethod.POST)
	public String jspDeleteUser(Locale locale, Model model) {
		return "user/deleteUser";
	}
	@RequestMapping(value = "/user/deleteUserResult", method = RequestMethod.POST)
	public String jspDeleteResult(Locale locale, Model model) {
		return "user/deleteUserResult";
	}
	@RequestMapping(value = "/user/showAllUser", method = RequestMethod.GET)
	public String jspShowAllUser(Locale locale, Model model) {
		return "user/showAllUser";
	}
	
	
	
}
