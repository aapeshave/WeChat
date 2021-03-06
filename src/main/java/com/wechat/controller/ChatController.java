package com.wechat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.hibernate.mapping.Collection;
import org.jboss.logging.annotations.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.configuration.RabbitConfiguration;
import com.wechat.pojo.ChatMessage;
import com.wechat.pojo.User;
import com.wechat.service.ChatServices;
import com.wechat.service.SearchServices;

@Controller
public class ChatController {

	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	private ApplicationContext myRabbitContext;
	private AmqpTemplate myTemplate;
	private AmqpAdmin myAdmin;
	
	@Autowired
	private SearchServices searchService;
	
	@Autowired
	private ChatServices chatService;
	
	public ChatController() {
		super();
		this.myRabbitContext = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
		this.myTemplate = this.myRabbitContext.getBean(AmqpTemplate.class);
		this.myAdmin = this.myRabbitContext.getBean(AmqpAdmin.class);
	}

	@RequestMapping(value = "/sendMessage.htm", method = RequestMethod.POST)
	public void sendMessage(HttpServletRequest request, HttpServletResponse response) {
		String unconvertedMessage = request.getParameter("message").replaceAll("[^\\dA-Za-z ]","").replaceAll("\\s+","").trim();
		String user = request.getParameter("user");
		String friendUsername  =request.getParameter("friendUsername");
		
//		String queueNameOfUser = searchService.getQueueNameOfUserByUsername(friendUsername);
//		System.out.println(queueNameOfUser);
//		String message  ="/"+user+";"+unconvertedMessage;
//		System.out.println(message);
//		try{
//			Queue queue = new Queue(queueNameOfUser);
//			myAdmin.declareQueue(queue);
//			String usernameTest = message.substring(message.indexOf("/")+1, message.indexOf(";"));
//			String messageTest  =message.substring(message.indexOf(";")+1);
//			System.out.println("usernameTest: "+ usernameTest);
//			System.out.println("messageTest: "+messageTest);
//			myTemplate.convertAndSend(queue.getName(), message);
//		}
//		catch(Exception e){
//			System.out.println(e);
//		}
		chatService.sendMessagge(friendUsername, user, unconvertedMessage);
	}
	
	
	@Transactional
	@RequestMapping(value = "/receiveMessage.htm", method = RequestMethod.POST)
	public void receiveMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, AmqpException {
		
//		String message;
//		String queueNameOfUser="";
//		try{
//			String user = request.getParameter("user");
//			queueNameOfUser = searchService.getQueueNameOfUserByUsername(user);
//			Queue queue = new Queue(queueNameOfUser);
//			myAdmin.declareQueue(queue);
//			
//			
//		}
//		catch(Exception e){
//			System.out.println(e);
//		}
//		message = (String) myTemplate.receiveAndConvert(queueNameOfUser);
//		if(message!=null){
//			String usernameTest = message.substring(message.indexOf("/")+1, message.indexOf(";"));
//			String messageTest  =message.substring(message.indexOf(";")+1);
//			//Gson gson  =new GsonBuilder().serializeNulls().create();
//			//Map<String, String> messageMap = new HashMap<String, String>();
//			
//			//String gsonMessage = gson.toJson(messageTest, messageTest.getClass());
//			//response.getWriter().write(gsonMessage);
//		}
		String keyUserName = request.getParameter("username");
		String message = chatService.receiveMessage(keyUserName);
		if(message!=null)
			response.getWriter().write(message);
	}
	
	@RequestMapping(value="/printPdfChatHistory.htm",method = RequestMethod.GET)
	public ModelAndView printChatHistory (HttpServletRequest request, HttpServletResponse response){
		Map<Long,ChatMessage> revenueData = new HashMap<Long,ChatMessage>();
		ChatMessage messsage = new ChatMessage();
		messsage.setMessage("Sample Message");
		revenueData.put((long) 1, messsage);
		return new ModelAndView("PdfRevenueSummary","revenueData",revenueData);
	}
	
	@RequestMapping(value="/printChatHistory.htm",method = RequestMethod.GET)
	public String printChatHistoryJSP (HttpServletRequest request, HttpServletResponse response){
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("errorCode", "Please Log in to system to access your chat");
			return "error";
		}
		else{
			if(!sender.isEmpty()&&!receiver.isEmpty()){
				if(user.getUsername().equals(sender) || user.getUsername().equals(receiver)){
					List<ChatMessage> chatHistory = (List) chatService.getChatHistory(sender, receiver);
					if(chatHistory!=null){
							request.setAttribute("chatHistory", chatHistory);
							for(ChatMessage m : chatHistory){
								System.out.println(m);
							}
					}
					return "chatHistory";
				}
				else{
					request.setAttribute("errorCode", "UnAuthorized Access");
					return "error";
				}
			}
		}
		return null;
	}
}
