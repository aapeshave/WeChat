package com.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.dao.UserDAO;
import com.wechat.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public User getUserByUsername(String username){
		if(null != username && !username.isEmpty())
			return userDAO.getUserByUsername(username);
		else
			return null;
	}	
	
	public User adduser(String firstName, String lastName, String role, String username, String password, String email,long personId){
		return userDAO.createNewUser(firstName, lastName, role, username, password, email, personId);
	}
}
