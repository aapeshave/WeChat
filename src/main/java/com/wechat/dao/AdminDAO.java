package com.wechat.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wechat.pojo.Admin;
import com.wechat.pojo.User;

@Repository
public class AdminDAO extends BaseDAO {

	public void createNewAdmin(Admin admin){
		Session session = getSession();
		try{
			begin(session);
			session.save(admin);
			commit(session);
			returnSession(session);
		}
		catch(Exception e){
			System.out.println("Exception while creating admin. Exception: "+e);
		}
	}
	
	public Admin authenticateAdmin(String username, String password){
		Session session = getSession();
		Query searchQuery = session.createQuery("from Admin u where u.username=:username and u.password=:password");
		searchQuery.setString("username", username);
		searchQuery.setString("password", password);
		try{
			Admin searchUser = (Admin) searchQuery.uniqueResult();
			returnSession(session);
			if(searchUser!=null)
				return searchUser;
		}
		catch(HibernateException hibException){
			System.out.println("Error while authenticating admin: "+hibException);
		}
		return null;
	}
}
