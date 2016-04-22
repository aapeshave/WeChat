package com.wechat.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wechat.dao.UserDAO;
import com.wechat.pojo.User;
import com.wechat.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	 public boolean supports(Class aClass)
    {
        return aClass.equals(User.class);
    }

	@Override
	public void validate(Object object, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "Username Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.user", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.user", "Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "error.invalid.user", "BirthDate Required");
		
		
		User user = (User) object;
		String username = user.getUsername();
		System.out.println(username);
		User someUser = userService.getUserByUsername(username);
		
		if(someUser!=null){
			errors.rejectValue("username","error.invalid.user","Username Already Exists");
		}
		
	}

}
