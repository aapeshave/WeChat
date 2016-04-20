package com.wechat.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.sql.Update;
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

	
//	public Boolean addFriend(User userFromMethod, String queryUserName) {
//		Session session = getSession();
//		begin(session);
//		User newFriendUser = (User) session.load(User.class,userDAO.getUserByUsername(queryUserName).getPersonId());
//		if (newFriendUser != null) {
//
//			User currentUser = (User) session.load(User.class, userFromMethod.getPersonId());
//			System.out.println(currentUser);
//			if (!friendAlreadyInDatabase(currentUser, queryUserName)) {
//				
//				Friend newFriend = new Friend();
//				newFriend.setIsAccepted(Boolean.FALSE);
//				newFriend.setStatus(NEW_STATUS);
//				newFriend.getUserList().add(currentUser);
//				newFriend.setConnectedUser(newFriendUser);
//				
//				session.save(newFriend);
//				commit(session);
//				System.out.println("New Friend Added");
//				
//				System.out.println("Before Making Changes");
//				Collection<Friend> friendList = newFriendUser.getFriendList();
//				for(Friend f : friendList){
//					System.out.println(f);
//				}
//				
//				Friend someNewFriend = new Friend();
//				someNewFriend.setIsAccepted(Boolean.FALSE);
//				someNewFriend.setStatus(NEW_STATUS);
//				someNewFriend.getUserList().add(newFriendUser);
//				someNewFriend.setConnectedUser(currentUser);
//				begin(session);
//				session.save(someNewFriend);
//				newFriendUser.getFriendList().add(someNewFriend);
//				session.save(newFriendUser);
//				commit(session);
//				
//				System.out.println("After Making Changes");
//				Collection<Friend> friendListnew = newFriendUser.getFriendList();
//				for(Friend f : friendListnew){
//					System.out.println(f);
//				}
//				
//				try {
//					// Save newFriend to currentuser
//					begin(session);
//					currentUser.getFriendList().add(newFriend);
//					session.save(currentUser);
//					commit(session);
//					
//					returnSession(session);
//					return Boolean.TRUE;
//				} catch (Exception e) {
//					System.out.println(e.toString());
//				}
//			}
//		}
//		return Boolean.FALSE;
//	}

	public Boolean addNewFriend(User c1,String f1){
		Session session = getSession();
		User currentUserAccount = (User) session.load(User.class, c1.getPersonId());
		User friendUserAccount = (User) session.load(User.class,userDAO.getUserByUsername(f1).getPersonId());
		if(friendUserAccount!=null){
			try{
				if(!friendAlreadyInDatabase(currentUserAccount, f1)){
					Collection <Friend > friendList = new ArrayList<Friend>();
//					System.out.println("Before Making Changes");
//					System.out.println(currentUserAccount);
//					System.out.println("Printing friendList of: "+currentUserAccount.getFirstName());
//					friendList = currentUserAccount.getFriendList();
//					for(Friend f : friendList){
//						System.out.println(f);
//					}
//					System.out.println(friendUserAccount);
//					System.out.println("Printing friendList of: "+friendUserAccount.getFirstName());
//					friendList = friendUserAccount.getFriendList();
//					for(Friend f : friendList){
//						System.out.println(f);
//					}
					
					
					//Creating Friend instance for frienUserAccount
					Friend friendInstanceOfFriendUserAccount = new Friend();
					friendInstanceOfFriendUserAccount.setIsAccepted(Boolean.FALSE);
					friendInstanceOfFriendUserAccount.setStatus(NEW_STATUS);
					friendInstanceOfFriendUserAccount.setConnectedUser(friendUserAccount);
					
					//Adding this friend Instance to friendList of User
					currentUserAccount.getFriendList().add(friendInstanceOfFriendUserAccount);
					System.out.println("added to list of user");
					//Updating to session
					begin(session);
					session.save(friendInstanceOfFriendUserAccount);
					session.update(currentUserAccount);
					commit(session);
					
					
					//System.out.println("Commited Changes");
					//Creating Friend Instance of userAccount
					Friend friendInstanceOfUserAccount = new Friend();
					friendInstanceOfUserAccount.setStatus(NEW_STATUS);
					friendInstanceOfUserAccount.setIsAccepted(Boolean.FALSE);
					friendInstanceOfUserAccount.setConnectedUser(currentUserAccount);
					
					//Adding this friend account to FriendList of Friend
					friendUserAccount.getFriendList().add(friendInstanceOfUserAccount);
					
					//Updating to Session
					begin(session);
					session.save(friendInstanceOfUserAccount);
					session.update(friendUserAccount);
					commit(session);
					returnSession(session);
					
//					System.out.println("After Making Changes");
//					System.out.println("Printing friendList of: "+currentUserAccount.getFirstName());
//					friendList = currentUserAccount.getFriendList();
//					for(Friend f : friendList){
//						System.out.println(f);
//					}
//					System.out.println("Printing friendList of: "+friendUserAccount.getFirstName());
//					friendList = friendUserAccount.getFriendList();
//					for(Friend f : friendList){
//						System.out.println(f);
//					}
					
					
					return Boolean.TRUE;
				}
				
			}
			catch(Exception e){
				
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean friendAlreadyInDatabase(User currentUser, String friendUsername) {
		if (currentUser != null && !friendUsername.isEmpty()) {
			if (currentUser.getFriendList() != null) {
				for (Friend friend : currentUser.getFriendList()) {
					if (friend.getConnectedUser().getUsername().equals(friendUsername))
						return Boolean.TRUE;
				}
			} else
				return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	public Boolean acceptFriend(User to, String friendUsername){
		Session session = getSession();
		User currentUserAccount = (User) session.load(User.class, to.getPersonId());
		User friendUserAccount = (User) session.load(User.class,userDAO.getUserByUsername(friendUsername).getPersonId());
		if(friendAlreadyInDatabase(currentUserAccount, friendUsername)){
			if(friendAlreadyInDatabase(friendUserAccount, currentUserAccount.getUsername())){
				System.out.println("Friend Entries are existed");
				try{
					int flag1=0,flag2=0;
					Collection <Friend > friendList = new ArrayList<Friend>();
					friendList = currentUserAccount.getFriendList();
					for(Friend f : friendList){
						if(f.getConnectedUser().getUsername()==friendUserAccount.getUsername()){
							flag1=1;
							begin(session);
							f.setIsAccepted(Boolean.TRUE);
							f.setStatus(ACCEPT_STATUS);
							session.update(f);
							commit(session);
							break;
						}
					}
					friendList = friendUserAccount.getFriendList();
					for(Friend f : friendList){
						if(f.getConnectedUser().getUsername()==currentUserAccount.getUsername()){
							flag2=1;
							begin(session);
							f.setIsAccepted(Boolean.TRUE);
							f.setStatus(ACCEPT_STATUS);
							session.update(f);
							commit(session);
							break;
						}
					}
					returnSession(session);
					return Boolean.TRUE;
				}
				catch(Exception e){
					return Boolean.FALSE;
				}
			}
		}
		return Boolean.FALSE;
	}
		
}
