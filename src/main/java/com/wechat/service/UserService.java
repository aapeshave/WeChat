package com.wechat.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.wechat.dao.FriendDAO;
import com.wechat.dao.UserDAO;
import com.wechat.pojo.Friend;
import com.wechat.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FriendDAO friendDAO;
	
	public User getUserByUsername(String username){
		if(null != username && !username.isEmpty())
			return userDAO.getUserByUsername(username);
		else
			return null;
	}	
	
	public User adduser(String firstName, String lastName, String role, String username, String password, String email, String birthDate) throws ParseException{
		return userDAO.createNewUser(firstName, lastName, role, username, password, email, birthDate);
	}
	
	public Boolean addFriend(User user, String newFrienduserName){
		if(user !=null && !(newFrienduserName.isEmpty())){
			return friendDAO.addFriend(user, newFrienduserName);
		}
		return null;
	}
	
	public String getFriendList(String username){
		if(null != username && !username.isEmpty()){
			//ArrayList<String> friendList = userDAO.getFriendList(username);
			JsonObject friendList = userDAO.getFriendList(username);
			if(friendList!=null){
				Gson  gson = new GsonBuilder().serializeNulls().create();
				String friendListJSONObject = gson.toJson(friendList,friendList.getClass());
				return friendListJSONObject;
			}
		}
		return null;
	}
}
