package com.wechat.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.pojo.User;
import com.wechat.service.AuthenticationService;
import com.wechat.service.UserService;

@Controller
public class LoginController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
@Autowired
private UserService userService;
@Autowired
private AuthenticationService authenticationService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//System.out.println(userService.getUserByUsername("admin"));
		//System.out.println(userService.adduser("sumit", "deo", "user", "sanket007", "sanket007", "sanket.bhavsar7@gmail.com", 112));
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	
	@RequestMapping(value="/loginMe.htm",method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		String email = request.getParameter("input_email");
		String password = request.getParameter("input_password");
		User loggedOnUser = authenticationService.authenticateUser(email, password);
		HttpSession session = request.getSession();
		System.out.println("In the controller");
		if(loggedOnUser!=null){
			System.out.println("user found!");
			session.setAttribute("user", loggedOnUser);
			mv.setViewName("index");
			return mv;
		}
		return null;	
	}
	
}
