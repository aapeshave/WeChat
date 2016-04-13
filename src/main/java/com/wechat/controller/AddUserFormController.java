package com.wechat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wechat.pojo.User;
import com.wechat.service.UserService;
import com.wechat.validators.UserValidator;


@Controller
@RequestMapping("/signup.htm")
public class AddUserFormController {

	private static final Logger logger = LoggerFactory.getLogger(AddUserFormController.class);
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	} 
	
	@RequestMapping(method = RequestMethod.GET) 
    public String initializeForm(@ModelAttribute("user")User user, BindingResult result,HttpServletRequest request, HttpServletResponse response) { 
        return "signup"; 
    } 
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user")User user, BindingResult result,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//System.out.println("Sending to Validator");
		validator.validate(user, result);
		if(result.hasErrors()){
    		return "signup"; 
    	}
		//Entered Information is Error Free
		try{
			String BirthDate = new SimpleDateFormat("mm/dd/yyyy").format((user.getBirthDate()));
			User newUser = userService.adduser(user.getFirstName(), user.getLastName(), "User", user.getUsername(), user.getPassword(), user.getEmail(), BirthDate);
			HttpSession webSession = request.getSession();
			webSession.setAttribute("user", newUser);
			return "index";
		}
		catch(Exception e){
			logger.debug(e.toString());
			e.printStackTrace();
		}
		return null;
	}
}
