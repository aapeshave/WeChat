package com.wechat.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.wechat.configuration.RabbitConfiguration;
import com.wechat.dao.ChatDAO;
import com.wechat.pojo.ChatMessage;
import com.wechat.pojo.ChatSession;

@Service
public class ChatServices {

	@Autowired
	ChatDAO chatDAO;
	
	@Autowired
	SearchServices searchService;
	
	@Autowired
	private ApplicationContext myRabbitContext;
	private AmqpTemplate myTemplate;
	private AmqpAdmin myAdmin;
	

	public ChatServices() {
		super();
		this.myRabbitContext = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
		this.myTemplate = this.myRabbitContext.getBean(AmqpTemplate.class);
		this.myAdmin = this.myRabbitContext.getBean(AmqpAdmin.class);
	}

	public Long createChatSessionFromSenderAndReceiver(String sender, String receiver){
		if(null != sender && !sender.isEmpty()){
			if(null != receiver && !receiver.isEmpty()){
				return chatDAO.createChatSessionFromSenderAndReceiver(sender, receiver);
			}
		}
		return null;
	}
	
	public ChatSession returnChatSessionIfAvailable(String sender, String receiver){
		if(null != sender && !sender.isEmpty()){
			if(null != receiver && !receiver.isEmpty()){
				return chatDAO.returnChatSessionIfAvailable(sender, receiver);
			}
		}
		return null;
	}
	
	public void sendMessagge(String friendUsername, String senderUsername, String unconvertedMessage){
		String queueNameOfUser = searchService.getQueueNameOfUserByUsername(friendUsername);
		String message  ="/"+senderUsername+";"+unconvertedMessage;
		try{
			Queue queue = new Queue(queueNameOfUser);
			myAdmin.declareQueue(queue);
			String usernameTest = message.substring(message.indexOf("/")+1, message.indexOf(";"));
			String messageTest  =message.substring(message.indexOf(";")+1);
			System.out.println("usernameTest: "+ usernameTest);
			System.out.println("messageTest: "+messageTest);
			myTemplate.convertAndSend(queue.getName(), message);
		}
		catch(Exception e){
			System.out.println("Error in sending message: "+e);
		}
	}
	
	public String receiveMessage(String keyUserName){
		if(keyUserName!=null){
			String queueNameOfUser = searchService.getQueueNameOfUserByUsername(keyUserName);
			try{
				Queue queue = new Queue(queueNameOfUser);
				myAdmin.declareQueue(queue);
				//String message = (String) myTemplate.receiveAndConvert(queue);
				
				String message = (String) myTemplate.receiveAndConvert(queueNameOfUser);
				if(message!=null){
					String usernameTest = message.substring(message.indexOf("/")+1, message.indexOf(";"));
					String messageTest  =message.substring(message.indexOf(";")+1);
						
					System.out.println("Message from server: "+message);
					
					System.out.println("usernameTest: "+ usernameTest);
					System.out.println("messageTest: "+messageTest);
					
					JsonObject chatMessageInnerObject = new JsonObject();
					chatMessageInnerObject.addProperty("sentFrom", usernameTest);
					chatMessageInnerObject.addProperty("message", messageTest);
					
					JsonObject chatMessageOuterObj = new JsonObject();
					chatMessageOuterObj.add("outer",chatMessageInnerObject);
					
					Gson gson  =new GsonBuilder().serializeNulls().create();
					String jsonMessage = gson.toJson(chatMessageOuterObj, chatMessageOuterObj.getClass());
					
					if(!message.isEmpty())
						addChatMessage(usernameTest, keyUserName, message);
					
					return jsonMessage;
					//String gsonMessage = gson.toJson(messageTest, messageTest.getClass());
					//response.getWriter().write(gsonMessage);
				}
				
				
			}
			catch(Exception e){
				System.out.println("Error while receiving messages: "+e);
			}
		}
		
		return null;
	}
	
	public long addChatMessage(String sender,String receiver,String message){
		return chatDAO.addChatMessage(sender, receiver, message);
	}
	
	public Collection<ChatMessage> getChatHistory(String sender, String receiver){
		if(!sender.isEmpty() && !receiver.isEmpty() && sender!=null && receiver!=null)
			return chatDAO.getChatHistory(sender, receiver);
		return null;	
	}
}
