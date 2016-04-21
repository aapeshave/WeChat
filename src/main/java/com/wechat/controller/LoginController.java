package com.wechat.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.pojo.User;
import com.wechat.service.AuthenticationService;
import com.wechat.service.SearchServices;
import com.wechat.service.UserService;

@Controller
public class LoginController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
@Autowired
private AuthenticationService authenticationService;

@Autowired
private SearchServices searchService;

@Autowired
private ServletContext servletContext;

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws ParseException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		/*
		ArrayList<String> users = searchService.searchUserList("aape");
		for(String s : users){
			System.out.println(s);
		}*/
		//System.out.println(userService.getUserByUsername("admin"));
		//System.out.println(userService.adduser("sanket", "bhavsar", "user", "sanket007", "sanket007", "sanket.bhavsar7@gmail.com", "03/15/1993"));
		//System.out.println(userService.adduser("sanket", "bhavsar", "user", "sanket007", "sanket007", "sanket.bhavsar7@gmail.com", "03/15/1993"));
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	
	@RequestMapping(value="/loginMe.htm",method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mav = new ModelAndView();
		String email = request.getParameter("input_email");
		String password = request.getParameter("input_password");
		
		User loggedOnUser = authenticationService.authenticateUser(email, password);
		HttpSession session = request.getSession();
		//System.out.println("In the controller");
		if(null != loggedOnUser){
			logger.info("User Looged in:", loggedOnUser);
			//System.out.println("user found!");
			/*
			session.setAttribute("user", loggedOnUser);
			return "{\"result\":\"Authenticated\", \"TOKEN\":\""+ session.getId() +"\" }";
			*/
			session.setAttribute("user", loggedOnUser);
			
			if(servletContext!=null){
				ArrayList<String> onlineUsers = (ArrayList<String>) servletContext.getAttribute("loggedInUsersList");
				if(onlineUsers==null)
					onlineUsers = new ArrayList<String>();
				onlineUsers.add(loggedOnUser.getUsername());
				servletContext.setAttribute("loggedInUsersList", onlineUsers);
//				System.out.println("Printing Online Users");
//				for(String s:onlineUsers){
//					System.out.println(s);
//				}
				//ArrayList<String> onlineFriends = searchService.onlineFriendList(onlineUsers, loggedOnUser.getUsername());
				//mav.addObject("onlineFriends", onlineFriends);
			}
			
			mav.setViewName("index");
			return mav;
		}
		else{
			session.setAttribute("loginError", "Invalid Credentails");
			mav.setViewName("login");
			return mav;
		}
		//return null;	
	}
	
	@RequestMapping(value="/authenticated.htm/TOKEN/{TOKEN}",method= RequestMethod.GET)
	public String authenticated(@PathVariable(value = "TOKEN") String jsessionId,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		System.out.println("YOLO");
		System.out.println("Current Session = " + session.getId());
		System.out.println("Passed on Session = " + jsessionId);
		*/
		if(session.getId().equals(jsessionId)){
			//response.sendRedirect("index.htm");
			//return null;
			return "forward:/index.htm";
		}
		else
			return "login";
	}
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String indexPage(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		if(session.getAttribute("user")!=null){
			User user = (User)session.getAttribute("user");
			if(authenticationService.authenticateUserByUserRole(user))
				return "index";
		}
		return "login";
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logoutAction(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		User loggedInUser = (User) session.getAttribute("user");
		ArrayList<String> onlineUsers = (ArrayList<String>) servletContext.getAttribute("loggedInUsersList");
		onlineUsers.remove(loggedInUser.getUsername());
		session.invalidate();
		return "login";
	}
	
}
