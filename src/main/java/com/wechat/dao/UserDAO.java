package com.wechat.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;
import com.wechat.pojo.Friend;
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
	
	public User createNewUser(String firstName, String lastName, String role, String username, String password, String email, String birthDate, String profilePictureName) throws ParseException{
		
		String queueName  =username.concat(firstName);
		User newUser = new User(firstName, lastName, role, birthDate, username, password, email, queueName, profilePictureName);
		
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
			if(searchUser!=null && searchUser.getIsActive()==Boolean.TRUE)
				return searchUser;
		}
		catch(HibernateException hibException){
			System.out.println(hibException);
		}
		return null;
	}
	
	public ArrayList<User> getFriendList(String username){
		User currentUser = getUserByUsername(username);
		if(currentUser!=null){
			Collection<Friend> collectionOfFriends = currentUser.getFriendList();
			if(collectionOfFriends!=null){
				//JsonObject jsonObject = new JsonObject();
				//ArrayList<String> friendList = new ArrayList<String>();
				ArrayList<User> userFriendList = new ArrayList<User>();
				for(Friend f : collectionOfFriends){
					//System.out.println(f);
					if(f.getIsAccepted()){
						//System.out.println("adding this user to list");
						User newUser = new User();
						newUser.setEmail(f.getConnectedUser().getEmail());
						newUser.setFirstName(f.getConnectedUser().getFirstName());
						newUser.setLastName(f.getConnectedUser().getLastName());
						newUser.setUsername(f.getConnectedUser().getUsername());
						newUser.setProfilePictureName(f.getConnectedUser().getProfilePictureName());
						userFriendList.add(newUser);
						//jsonObject.addProperty("username", f.getConnectedUser().getUsername());
						//jsonObject.addProperty("firstName", f.getConnectedUser().getFirstName());
						//jsonObject.addProperty("lastName", f.getConnectedUser().getLastName());
						
						//friendList.add(f.getConnectedUser().getUsername());
					}
					
				}
				return userFriendList;
			}
		}
		return null;
	}
	
	public ArrayList<User> getPendingFriendList(String username){
		Session session = getSession();
		User currentUser = (User) session.load(User.class,getUserByUsername(username).getPersonId());
		if(currentUser!=null){
			Collection<Friend> collectionOfFriends = currentUser.getFriendList();
			if(collectionOfFriends!=null){
				//JsonObject jsonObject = new JsonObject();
				//ArrayList<String> friendList = new ArrayList<String>();
				ArrayList<User> userFriendList = new ArrayList<User>();
				System.out.println("Printing Friend List of: "+currentUser.getUsername());
				for(Friend f : collectionOfFriends){
					System.out.println(f);
					if(!f.getIsAccepted()){
						User newUser = new User();
						newUser.setEmail(f.getConnectedUser().getEmail());
						newUser.setFirstName(f.getConnectedUser().getFirstName());
						newUser.setLastName(f.getConnectedUser().getLastName());
						newUser.setUsername(f.getConnectedUser().getUsername());
						newUser.setProfilePictureName(f.getConnectedUser().getProfilePictureName());
						userFriendList.add(newUser);
						//jsonObject.addProperty("username", f.getConnectedUser().getUsername());
						//jsonObject.addProperty("firstName", f.getConnectedUser().getFirstName());
						//jsonObject.addProperty("lastName", f.getConnectedUser().getLastName());
						
						//friendList.add(f.getConnectedUser().getUsername());
					}
					
				}
				returnSession(session);
				return userFriendList;
			}
		}
		return null;
	}
	
	public ArrayList<String> returnOnlineFreinds(ArrayList<String> onlineUsers, String username){
		ArrayList<String> onlineFriendList = new ArrayList<String>();
		Session session = getSession();
		User currentUser = (User) session.load(User.class,getUserByUsername(username).getPersonId());
		if(currentUser!=null){
			Collection<Friend> friendListOfUser = currentUser.getFriendList();
			if(friendListOfUser!=null){
				for(Friend f:friendListOfUser){
					for(String s: onlineUsers){
						if(s.equals(f.getConnectedUser().getUsername())){
							onlineFriendList.add(s);
						}
					}
				}
				return onlineFriendList;	
			}
		}
		return null;
	}
	
	public Boolean checkIfEmailAlreadyExists(String emailAddress){
		
				Session session  =getSession();
				Query q = session.createQuery("from User u where u.email=:emailAddress");
				q.setString("emailAddress", emailAddress);
								
				User u =(User) q.uniqueResult();
				returnSession(session);
				if(u==null){
					return Boolean.TRUE;
				}
		return Boolean.FALSE;
	}
	
	
	public String getQueueNameOfUser(String username){
		User currentUser = getUserByUsername(username);
		if(currentUser!=null){
			return currentUser.getQueueName();
		}
		return null;
	}
	
	public Boolean deleteUserFromDatabase(String username){
		try{
			Session session = getSession();
			User selectedUser = (User) session.load(User.class, getUserByUsername(username).getPersonId());
			if(selectedUser!=null){
				begin(session);
				selectedUser.setIsActive(Boolean.FALSE);
				session.update(selectedUser);
				commit(session);
				returnSession(session);
				return Boolean.TRUE;
			}
		}
		catch(Exception e){
			System.out.println("Error while deleting user. Error: "+e);
		}
		return Boolean.FALSE;
	}
}
