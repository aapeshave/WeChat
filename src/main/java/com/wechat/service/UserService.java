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
	
	
}
