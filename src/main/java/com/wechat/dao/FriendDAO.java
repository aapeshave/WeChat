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

	public Boolean addFriend(User currentUser, String queryUserName) {
		User newFriendUser = userDAO.getUserByUsername(queryUserName);
		if (newFriendUser != null) {

			Session session = getSession();
			User u = (User) session.load(User.class, currentUser.getPersonId());

			if (!friendAlreadyInDatabase(u, queryUserName)) {
				//System.out.println("creating New Friend");
				Friend newFriend = new Friend();
				newFriend.setIsAccepted(Boolean.FALSE);
				newFriend.setStatus(NEW_STATUS);
				newFriend.getUserList().add(currentUser);
				//System.out.println("Adding New Friend");
				// Save newFriend to Database
				newFriend.setConnectedUser(newFriendUser);
				begin(session);
				session.save(newFriend);
				commit(session);
				System.out.println("New Friend Added");

				try {
					// Save newFriend to currentuser
					begin(session);
					u.getFriendList().add(newFriend);
					session.save(u);
					commit(session);
					// System.out.println(u.getFriendList().toString());
					returnSession(session);
					return Boolean.TRUE;
				} catch (Exception e) {
					System.out.println(e.toString());
				}
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
	
}
