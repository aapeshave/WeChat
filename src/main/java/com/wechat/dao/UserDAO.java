package com.wechat.dao;

import java.util.List;

import org.hibernate.HibernateException;
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
		close();
	}
	
	public User getUserByUsername(String username){
		//Session session = new Configuration().configure().buildSessionFactory().openSession();
		Session session  =getSession();
		Query q = session.createQuery("from User u where u.username=:username");
		q.setString("username", username);
		
		//User user = (User) session.createQuery("from User where username =: username").setString("username", username).uniqueResult();
		User u =(User) q.uniqueResult();
		//System.out.println(u);
		//returnSession(session);
		close();
		return u;
	}
	
	public User createNewUser(String firstName, String lastName, String role, String username, String password, String email,long personId){
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPassword(password);
		newUser.setPersonId(personId);
		newUser.setQueueName(username+firstName);
		newUser.setUsername(username);
		newUser.setPassword(password);
		
		//Session session = new Configuration().configure().buildSessionFactory().openSession();
		Session session  =getSession();
		Transaction tr = session.beginTransaction();
		session.save(newUser);
		tr.commit();
		close();
		return newUser;
	}
	
	public User authenticateUser(String email, String password){
		Session session = getSession();
		Query searchQuery = session.createQuery("from User u where u.email=:email and u.password=:password");
		searchQuery.setString("email", email);
		searchQuery.setString("password", password);
		try{
			User searchUser = (User) searchQuery.uniqueResult();
			if(searchUser!=null)
				return searchUser;
		}
		catch(HibernateException hibException){
			System.out.println(hibException);
		}
		return null;
	}
}
