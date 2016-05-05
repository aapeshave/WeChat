package com.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.dao.AdminDAO;
import com.wechat.dao.UserDAO;
import com.wechat.pojo.Admin;
import com.wechat.pojo.User;

@Service
public class AuthenticationService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AdminDAO adminDAO;
	
	private static String USER_ROLE="user";
	
	public User authenticateUser(String email, String password){
		if(null != email && !email.isEmpty() || null != password && !password.isEmpty()  )
			return userDAO.authenticateUser(email, password);
		else
			return null;
	}	
	
	public Boolean authenticateUserByUserRole(User user){
		if(user.getRole().equalsIgnoreCase(USER_ROLE))
			return Boolean.TRUE;
	
		return Boolean.FALSE;	
	}
	
	public Admin authenticateAdmin(String email, String password){
		if(null != email && !email.isEmpty() || null != password && !password.isEmpty()  )
			return adminDAO.authenticateAdmin(email, password);
		else
			return null;
	}	
}
