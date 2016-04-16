package com.wechat.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wechat.pojo.Friend;
import com.wechat.pojo.User;

@Repository
public class FriendDAO extends BaseDAO {
	
	@Autowired
	private UserDAO userDAO;
	
	public static final String NEW_STATUS = "New Pending Request";
	public static final String REJECT_STATUS = "Request Rejected";		
	public static final String ACCEPT_STATUS = "Request Accepted";
	
	public Boolean addFriend(User currentUser, String queryUserName){
		User newFriendUser = userDAO.getUserByUsername(queryUserName);
		if(newFriendUser!=null){
			System.out.println("creating New Friend");
			Friend newFriend = new Friend();
			newFriend.setIsAccepted(Boolean.FALSE);
			newFriend.setStatus(NEW_STATUS);
			newFriend.getUserList().add(newFriendUser);
			System.out.println("Adding New Friend");
			//Save newFriend to Database
			Session session = getSession();
			begin(session);
			session.save(newFriend);
			commit(session);
			System.out.println("New Friend Added");
			
			try{
				//Save newFriend to currentuser
				
				currentUser.getFriendList().add(newFriend);
				User u = (User) session.load(User.class,currentUser.getPersonId());
				u.getFriendList().add(newFriend);
				begin(session);
				session.save(u);
				commit(session);
				System.out.println(u.getFriendList().toString());
				returnSession(session);
				return Boolean.TRUE;
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		return Boolean.FALSE;	
	}
}
