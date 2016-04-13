package com.wechat.controller;

import java.io.IOException;
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

import com.wechat.service.SearchServices;

@Controller
public class IndexController {

	@Autowired
	SearchServices searchService;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(method= RequestMethod.GET,value="/addFriendSearchUsername.htm")
	public void addFriendSearch(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ArrayList<String> searchResults  =searchService.searchUserList(request.getParameter("username"));
		if(searchResults!=null){
			
		}
		else{
			response.sendError(404,"No Matches Found");
		}
	}
}
