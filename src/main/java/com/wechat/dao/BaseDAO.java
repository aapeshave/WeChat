package com.wechat.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wechat.session.SessionHandler;

@Repository
public abstract class BaseDAO {

	@Autowired
	private SessionHandler sessionHandler;
	
	protected Session getSession(){
		return sessionHandler.getSession();
	}
	
	protected void returnSession(Session session){
		sessionHandler.returnSession(session);
	}
}
