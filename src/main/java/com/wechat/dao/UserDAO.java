package com.wechat.dao;

import java.text.ParseException;
import java.util.ArrayList;
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
		returnSession(session);
		
		return u;
	}
	
	public ArrayList<User> getUsernameSearch(String username){
		ArrayList<User> searchResults = new ArrayList<User>();
		Session session  =getSession();
		username ="%" + username + "%";
		Query q = session.createQuery("from User u where u.username like :username");
		q.setString("username", username);
		
		searchResults = (ArrayList<User>) q.list();
		returnSession(session);
		
		return searchResults;
	}
	
	public User createNewUser(String firstName, String lastName, String role, String username, String password, String email, String birthDate) throws ParseException{
		
		String queueName  =username.concat(firstName);
		User newUser = new User(firstName, lastName, role, birthDate, username, password, email, queueName);
		
		//Session session = new Configuration().configure().buildSessionFactory().openSession();
		Session session  =getSession();
		Transaction tr = session.beginTransaction();
		session.save(newUser);
		tr.commit();
		returnSession(session);
		return newUser;
	}
	
	public User authenticateUser(String email, String password){
		Session session = getSession();
		Query searchQuery = session.createQuery("from User u where u.email=:email and u.password=:password");
		searchQuery.setString("email", email);
		searchQuery.setString("password", password);
		try{
			User searchUser = (User) searchQuery.uniqueResult();
			returnSession(session);
			if(searchUser!=null)
				return searchUser;
		}
		catch(HibernateException hibException){
			System.out.println(hibException);
		}
		return null;
	}
	
}
