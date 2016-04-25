package com.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.spi.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.pojo.ChatSession;
import com.wechat.pojo.User;
import com.wechat.service.ChatServices;
import com.wechat.service.SearchServices;
import com.wechat.service.UserService;

@Controller
public class IndexController {

	@Autowired
	SearchServices searchService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChatServices chatService;
	
	@Autowired
	ServletContext servletContext;
	
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
	public String showProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//Boolean someValue = userService.addFriend(userService.getUserByUsername("aapeshave"), "sanket007");
		//System.out.println(someValue);
		//System.out.println(userService.getFriendList("sanket007"));
		//System.out.println(userService.acceptFriend(userService.getUserByUsername("aapeshave"), "sanket007"));
		//long result = chatService.createChatSessionFromSenderAndReceiver("sanket007", "aapeshave");
		//ChatSession chatSession = chatService.returnChatSessionIfAvailable("aapeshave", "sanket007");
		//System.out.println(result);
		//System.out.println(chatSession);
		System.out.println(chatService.receiveMessage("aapeshave"));
		System.out.println("Showing Profile");
		
		
		return "profile";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addNewFreind.htm")
	public void newFreindRequest(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String friendUserName = request.getParameter("friendUserName");
		User currentUser = (User) session.getAttribute("user");
		Boolean someValue = userService.addFriend(currentUser, friendUserName);
		if(someValue==Boolean.TRUE){
			logger.info("New Friend for user:"+currentUser.getUsername()+" is added!");
			response.getWriter().write("New Friend Added"); 
		}
			
		else{
			response.getWriter().write("Can not add new friend");
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/showFriendList.htm")
	public void showFriendListCollectoin(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		User currentUser = (User) session.getAttribute("user");
		String friendList = userService.getFriendList(currentUser.getUsername());
		if(friendList!=null){
			//System.out.println(friendList);
			response.getWriter().write(friendList);
			
		}
		else
		{
			System.out.println("sending error");
			response.sendError(404);
		}	
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/showPendingFriendList.htm")
	public void showPendingFriendListCollectoin(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		User currentUser = (User) session.getAttribute("user");
		String friendList = userService.getPendingFriendList(currentUser.getUsername());
		if(friendList!=null)
			response.getWriter().write(friendList);
		else
		{
			System.out.println("sending error");
			response.sendError(404);
		}
			
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/acceptFriend.htm")
	public void acceptFriendRequest(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String friendUserName = request.getParameter("friendUserName");
		User currentUser = (User) session.getAttribute("user");
		Boolean someValue = userService.acceptFriend(currentUser, friendUserName);
		if(someValue==Boolean.TRUE){
			response.getWriter().write("Friend Requst Accepted!"); 
		}
			
		else{
			response.getWriter().write("Error in Accepting Friend Reqest");
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/getOnlineFreinds.htm",produces="application/json")
	public @ResponseBody Collection<String> sendOnlineFriends(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ArrayList<String> onlineUsers = (ArrayList<String>) servletContext.getAttribute("loggedInUsersList");
		User currentUser = (User) session.getAttribute("user");
		
		if(currentUser.getRole().equals("User")){
			Collection<String> onlineFriendList = searchService.onlineFriendList(onlineUsers, currentUser.getUsername());
			//response.getWriter().write(onlineFriendList); 
			return onlineFriendList;
		}
		else{
			//response.getWriter().write("Error in Getting Friends");
		}
		return null;
	}
	
}
