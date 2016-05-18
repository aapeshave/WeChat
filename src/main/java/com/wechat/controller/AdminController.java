package com.wechat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.pojo.Admin;
import com.wechat.pojo.User;
import com.wechat.service.AdminService;
import com.wechat.service.AuthenticationService;
import com.wechat.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminServices;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userServices;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/loginOfAdminOfWeChat.htm", method = RequestMethod.GET)
	public String adminLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null){
			//User u = (User) session.getAttribute("user");
			//System.out.println(u);
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admin admin = authenticationService.authenticateAdmin(username, password);
		if(admin!=null){
			HttpSession session =request.getSession();
			session.setAttribute("admin", admin);
			return "adminHomepage";
		}
		return "adminLoginPage";
	}
	
	@RequestMapping(value = "/getOnlineUserInfo.htm", method = RequestMethod.GET)
	public void  onlineUsers(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Reached admin controller");
		ArrayList<String> onlineUsers = (ArrayList<String>) servletContext.getAttribute("loggedInUsersList");
//		User u = userServices.getUserByUsername("aapeshave");
//		if(u!=null){
//			User newUser = new User();
//			newUser.setBirthDate(u.getBirthDate());
//			newUser.setIsCreatedOn(u.getIsCreatedOn());
//			newUser.setEmail(u.getEmail());
//			newUser.setIsActive(u.getIsActive());
//			newUser.setFirstName(u.getFirstName());
//			newUser.setLastName(u.getLastName());
//			newUser.setUsername(u.getUsername());
//			Gson  gson = new GsonBuilder().serializeNulls().create();
//			ArrayList<User> userList = new ArrayList<User>();
//			userList.add(newUser);
//			String userStirng = gson.toJson(userList, userList.getClass());
//			try {
//				response.getWriter().write(userStirng);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
//		Gson  gson = new GsonBuilder().serializeNulls().create();
//		String output = gson.toJson(onlineUsers, onlineUsers.getClass());
//		try {
//			response.getWriter().write(output);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		if(onlineUsers!=null){

		}
		
	}
	
	@RequestMapping(value = "/logoutAdminAction.htm", method = RequestMethod.GET)
	public String logoutAdmin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session!=null){
			if(session.getAttribute("user")!=null){
				User u = (User) session.getAttribute("user");
				System.out.println(u);
				session.invalidate();
				request.setAttribute("errorCode", "You are not authorized to access this link");
				return "error";
			}
			session.invalidate();
			return "adminLoginPage";
		}
		return null;
	}
	
}
