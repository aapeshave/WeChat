package com.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wechat.pojo.User;
import com.wechat.service.UserService;

@Controller
public class ProfileController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method= RequestMethod.GET,value="/showProfile.htm")
	public String showProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		User sessionedUser = (User) session.getAttribute("user");
		String username = request.getParameter("username");
		User user = userService.getUserByUsername(username);
		if(user!=null){
			if(sessionedUser!=null && sessionedUser.getUsername().equals(user.getUsername())){
				request.setAttribute("safety", "no");
			}
			else{
				request.setAttribute("safety", null);
			}
			
			request.setAttribute("user", user);
			System.out.println("Showing Profile");
			return "profile";
		}
		request.setAttribute("errorCode", "No Profile Exists for this username");
		return "error";
	}
	
	@RequestMapping(method= RequestMethod.GET,value="/deleteUser.htm")
	public String deleteProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("username");
		User user = (User) session.getAttribute("user");
		if(session.getAttribute("user")==null)
			return null;
		if(user.getUsername().equals(username)){
			System.out.println("Calling Delete");
			userService.deleteUserFromDatabase(username);
			return "forward:/logout.htm";
		}
		else{
			
		}
		return null;
	}
}
