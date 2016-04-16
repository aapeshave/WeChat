package com.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.spi.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.pojo.User;
import com.wechat.service.SearchServices;
import com.wechat.service.UserService;

@Controller
public class IndexController {

	@Autowired
	SearchServices searchService;
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(method= RequestMethod.GET,value="/addFriendSearchUsername.htm")
	public void addFriendSearch(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String username = request.getParameter("username");
		if(username.length()>3){
			User currentUser = (User) session.getAttribute("user");
			ArrayList<String> searchResults  =searchService.searchUserList(username);
			if(searchResults!=null){
				//Remove current loggedIn User from search Results!
				if(searchResults.contains(currentUser.getUsername())){
					searchResults.remove(currentUser.getUsername());
				}
				if(searchResults!=null){
					Gson  gson = new GsonBuilder().serializeNulls().create();
					String jsonResults = gson.toJson(searchResults, searchResults.getClass());
			        response.getWriter().write(jsonResults);
				}
			}
			else{
				response.sendError(404,"No Matches Found");
			}	
		}
			
	}
	
	@RequestMapping(method= RequestMethod.GET,value="/showProfile.htm")
	public void showProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Boolean someValue = userService.addFriend(userService.getUserByUsername("aapeshave"), "sanket007");
		System.out.println(someValue);
		System.out.println("Showing Profile");
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addNewFreind.htm")
	public void newFreindRequest(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String friendUserName = request.getParameter("friendUserName");
		User currentUser = (User) session.getAttribute("user");
		System.out.println("In the controller");
		System.out.println("Friend Username: "+friendUserName+" & current User: "+currentUser.getUsername());
		System.out.println("In the if loop");
		Boolean someValue = userService.addFriend(currentUser, friendUserName);
		if(someValue==Boolean.TRUE){
			response.getWriter().write("New Friend Added");
			System.out.println("Writing new Friend Added to Response");
		}
			
		else{
			response.getWriter().write("Can not add new friend");
			System.out.println("Writing new Friend can not be Added to Response");
		}
	}
	
}
