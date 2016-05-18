package com.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.dao.AdminDAO;
import com.wechat.pojo.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	public void createNewAdmin(Admin admin){
		if(admin!=null){
			adminDAO.createNewAdmin(admin);
		}
	}
	
	public String getOnlineUserTable(){
		return null;
	}
}
