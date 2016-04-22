package com.wechat.service;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	
	public ArrayList<String> onlineFriendList(ArrayList<String> listOfOnlineMembers, String username){
		if(listOfOnlineMembers!=null){
			if(null!= username && !username.isEmpty()){
				ArrayList<String> onlineFriends = userDAO.returnOnlineFreinds(listOfOnlineMembers, username);
				if(onlineFriends!=null){
					//Gson  gson = new GsonBuilder().serializeNulls().create();
					//String result = gson.toJson(onlineFriends, onlineFriends.getClass());
					//System.out.println(result);
					return onlineFriends;
				}
			}
		}
		return null;
	}
}
