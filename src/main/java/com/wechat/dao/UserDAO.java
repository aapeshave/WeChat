package com.wechat.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.wechat.pojo.User;

@Repository
public class UserDAO extends BaseDAO{

	public void save(User user){
		Session session = getSession();
		Transaction tr = session.beginTransaction();
		session.save(user);
		tr.commit();
		returnSession(session);
	}
	
	public User getUserByUsername(String username){
		
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query q = session.createQuery("from User u where u.username=:username");
		q.setString("username", username);
		
		//User user = (User) session.createQuery("from User where username =: username").setString("username", username).uniqueResult();
		User u =(User) q.uniqueResult();
		//System.out.println(u);
		returnSession(session);
		return u;
	}
	
}
