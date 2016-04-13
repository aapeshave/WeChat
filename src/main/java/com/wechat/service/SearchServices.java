package com.wechat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.dao.UserDAO;
import com.wechat.pojo.User;

@Service
public class SearchServices {

	@Autowired
	UserDAO userDAO;
	
	//To-Do Write AJAX Call Method
	public ArrayList<String> searchUserList(String searchKey){
		if(null != searchKey && !searchKey.isEmpty()){
			ArrayList<User> searchedUsers = userDAO.getUsernameSearch(searchKey);
			if(searchedUsers != null){
				ArrayList<String> searchResults = new ArrayList<String>();
				for(User u : searchedUsers){
					searchResults.add(u.getUsername());
				}
				return searchResults;
			}
		}
		return null;	
	}
}
