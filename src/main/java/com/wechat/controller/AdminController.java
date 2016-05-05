package com.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wechat.pojo.Admin;
import com.wechat.pojo.User;
import com.wechat.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminServices;
	
	@RequestMapping(value = "/loginOfAdminOfWeChat.htm", method = RequestMethod.GET)
	public String adminLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null){
			User u = (User) session.getAttribute("user");
			System.out.println(u);
			session.invalidate();
			request.setAttribute("errorCode", "You can not access admin Page");
			return "error";
		}
//		Admin admin = new Admin();
//		admin.setFirstName("John");
//		admin.setLastName("Appleseed");
//		admin.setUsername("john");
//		admin.setPassword("Admin@123");
//		admin.setBranch("MainAdmin");
//		admin.setRole("Admin");
//		adminServices.createNewAdmin(admin);
		return "adminLoginPage";
	}
	
	@RequestMapping(value = "/adminLoginAction.htm", method = RequestMethod.POST)
	public String authenticateAdmin(HttpServletRequest request, HttpServletResponse response){
		return "adminLoginPage";
	}
}
