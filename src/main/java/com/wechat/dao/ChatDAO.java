package com.wechat.dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wechat.pojo.ChatMessage;
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
				Long id = (Long) session.save(chatSession);
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
	
	public long addChatMessage(String sender,String receiver,String message){
		ChatSession oldChatSession = returnChatSessionIfAvailable(sender, receiver);
		Long id=(long) 0;
		int flag=0;
		if(oldChatSession==null){
			flag=1;
			id = createChatSessionFromSenderAndReceiver(sender, receiver);
			
		}
		else{
			id=oldChatSession.getChatSessionID();
		}
		Session hibSession = getSession();
		begin(hibSession);
		ChatMessage newMessage = new ChatMessage();
		
		newMessage.setMessage(message);
		newMessage.setContentType("String");
		
		ChatSession newChatSession = (ChatSession) hibSession.load(ChatSession.class, id);
		newMessage.setChatSession(newChatSession);
		try{
			long messageID = (Long) hibSession.save(newMessage);
			commit(hibSession);
			begin(hibSession);
			newChatSession.getChatHistory().add(newMessage);
				hibSession.update(newChatSession);
			commit(hibSession);
			return 0;
		}
		catch(ConstraintViolationException e){
			System.out.println("Error while adding new Message: "+e.getConstraintName());
		}
		catch(Exception e){
			System.out.println("Error while adding message: "+e);
		}
		finally {
			returnSession(hibSession);
		}
		
		return 0;
	}
	
	public Collection<ChatMessage> getChatHistory(String sender, String receiver){
		ChatSession chatSession = returnChatSessionIfAvailable(sender, receiver);
		if(chatSession!=null)
			return chatSession.getChatHistory();
		return null;	
	}
}
