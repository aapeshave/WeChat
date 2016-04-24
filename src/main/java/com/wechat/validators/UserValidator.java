package com.wechat.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.wechat.dao.UserDAO;
import com.wechat.pojo.User;
import com.wechat.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

	@Override
	public boolean supports(Class aClass) {
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
		User someUser = userService.getUserByUsername(username);

		if (someUser != null) {
			errors.rejectValue("username", "error.invalid.user", "Username Already Exists");
		}

		Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher;
		MultipartFile profilePhoto = user.getProfilePicture();
		if(profilePhoto!=null){
			System.out.println("Veryfying Profile Picture");
			matcher = pattern.matcher(profilePhoto.getOriginalFilename());

			if (0 == profilePhoto.getSize()) {
				errors.rejectValue("profilePicture", "Test", "File is empty");
			}
			if (!matcher.matches()) {
				errors.rejectValue("profilePicture", "Test", "Invalid Image Format");
			}

			if (5000000 < profilePhoto.getSize()) {
				errors.rejectValue("profilePicture", "Test", "File size is over 5mb !");
			}
		}
		else{
			errors.rejectValue("profilePicture", "error.invalid.user", "File is empty");
		}
		

	}

}
