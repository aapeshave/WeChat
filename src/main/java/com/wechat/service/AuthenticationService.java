package com.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.dao.UserDAO;
import com.wechat.pojo.User;

@Service
public class AuthenticationService {

	@Autowired
	UserDAO userDAO;
	
	public User authenticateUser(String email, String password){
		if(null != email && !email.isEmpty() || null != password && !password.isEmpty()  )
			return userDAO.authenticateUser(email, password);
		else
			return null;
	}	
}
