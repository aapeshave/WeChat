package com.wechat.dao;

import java.sql.Time;
import java.sql.Timestamp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wechat.pojo.ChatSession;
import com.wechat.pojo.User;

@Repository
public class ChatDAO extends BaseDAO
{
	@Autowired
	UserDAO userDAO;
	
	public long createChatSessionFromSenderAndReceiver(String sender, String receiver){
		//User senderUserAccount = userDAO.getUserByUsername(sender);
		//User receiverUserAccount = userDAO.getUserByUsername(receiver);
		if(sender!=null && receiver!=null){
			try{
				Session session = getSession();
				begin(session);
				
				ChatSession chatSession = new ChatSession(sender, receiver);
				long id = (Long) session.save(chatSession);
				commit(session);
				returnSession(session);
				return id;
			}
			catch(Exception e){
				System.out.println("Exception in creating new ChatSession. Exception: "+ e);
			}
			
		}
		return 0;
	}
	
	public ChatSession returnChatSessionIfAvailable(String sender,String receiver){
		User senderUserAccount = userDAO.getUserByUsername(sender);
		User receiverUserAccount = userDAO.getUserByUsername(receiver);
		if(senderUserAccount!=null && receiverUserAccount!=null){
			Session session = getSession();
			begin(session);
			Query q = session.createQuery("From ChatSession where sender=:sender and receiver=:receiver");
//			q.setLong("sender", senderUserAccount.getPersonId());
//			q.setLong("receiver", receiverUserAccount.getPersonId());
			q.setString("sender", sender);
			q.setString("receiver",receiver);
			ChatSession chatSession;
			chatSession = (ChatSession) q.uniqueResult();
			if(chatSession!=null)
				return chatSession;
			else{
				Query newQuery = session.createQuery("From ChatSession where sender=:sender and receiver=:receiver");
//				q.setLong("sender",  receiverUserAccount.getPersonId());
//				q.setLong("receiver", senderUserAccount.getPersonId());
				newQuery.setString("sender", receiver);
				newQuery.setString("receiver",sender);
				chatSession = (ChatSession) newQuery.uniqueResult();
				return chatSession;
			}	
		}
		return null;	
	}
}
