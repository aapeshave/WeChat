package com.wechat.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

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
	
	@Autowired
	ServletContext servletContext;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
		//binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	} 
	
	@RequestMapping(method = RequestMethod.GET) 
    public String initializeForm(@ModelAttribute("user")User user, BindingResult result,HttpServletRequest request, HttpServletResponse response) { 
        return "signup"; 
    } 
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user")User user, @RequestParam("profilePicture")MultipartFile profilePicture,BindingResult result,HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Sending to Validator");
		user.setProfilePicture(profilePicture);
		validator.validate(user, result);
		if(result.hasErrors()){
    		return "signup"; 
    	}
		//Entered Information is Error Free
		try{
			System.out.println("Done from validation");
			File file;
			
			String path = null;
			
			
		    String photolink = null;    
		    System.out.println("Path: "+path);
			if(user.getProfilePicture()!=null){
				System.out.println("Profile Picture is not empty");
		        path = "/Users/ajinkya/Library/Mobile Documents/com~apple~CloudDocs/SpringWorkspace/WeChatApplication/src/main/webapp/resources/images/";
				String fileNamewithExt  =System.currentTimeMillis()+user.getProfilePicture().getOriginalFilename();
				file = new File(path + fileNamewithExt);
		        String context = servletContext.getContextPath();
		        
		        //path = "/Users/ajinkya/Library/Mobile Documents/com~apple~CloudDocs/SpringWorkspace/WeChatApplication/src/main/webapp/resources/";
		        
		        user.getProfilePicture().transferTo(file);
		        photolink = "/resources/images/"+fileNamewithExt;
		        
		        System.out.println("Link to photo: "+photolink);
		        String BirthDate = new SimpleDateFormat("mm/dd/yyyy").format((user.getBirthDate()));
				
		        User newUser = userService.adduser(user.getFirstName(), user.getLastName(), "User", user.getUsername(), user.getPassword(), user.getEmail(), BirthDate, photolink);
				HttpSession webSession = request.getSession();
				webSession.setAttribute("user", newUser);
				
				if(servletContext!=null){
					ArrayList<String> onlineUsers = (ArrayList<String>) servletContext.getAttribute("loggedInUsersList");
					if(onlineUsers==null)
						onlineUsers = new ArrayList<String>();
					onlineUsers.add(newUser.getUsername());
					servletContext.setAttribute("loggedInUsersList", onlineUsers);
				}
				
				
				return "index";
		        //return null;
			}
			
		}
		catch(Exception e){
			logger.debug(e.toString());
			e.printStackTrace();
		}
		return null;
	}
}
