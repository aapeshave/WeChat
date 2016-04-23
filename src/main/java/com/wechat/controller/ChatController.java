package com.wechat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.configuration.RabbitConfiguration;
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
	
	public ChatController() {
		super();
		this.myRabbitContext = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
		this.myTemplate = this.myRabbitContext.getBean(AmqpTemplate.class);
		this.myAdmin = this.myRabbitContext.getBean(AmqpAdmin.class);
	}

	@RequestMapping(value = "/sendMessage.htm", method = RequestMethod.POST)
	public void sendMessage(HttpServletRequest request, HttpServletResponse response) {
		String unconvertedMessage = request.getParameter("message");
		String user = request.getParameter("user");
		String friendUsername  =request.getParameter("friendUsername");
		String queueNameOfUser = searchService.getQueueNameOfUserByUsername(friendUsername);
		System.out.println(queueNameOfUser);
		String message  ="/"+user+";"+unconvertedMessage;
		System.out.println(message);
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
			System.out.println(e);
		}
	}
	
	
	@Transactional
	@RequestMapping(value = "/receiveMessage.htm", method = RequestMethod.GET)
	public void receiveMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, AmqpException {
		
		String message;
		
		/*
		ApplicationContext context =
			    new AnnotationConfigApplicationContext(RabbitConfiguration.class);
		AmqpTemplate template = context.getBean(AmqpTemplate.class);
	
		*/
		/*
		Message m;
		m = template.receive("myQueue");
		System.out.println(m.toString());
		Gson  gson = new GsonBuilder().serializeNulls().create();
		String gsonMessage = gson.toJson(m.toString(), m.toString().getClass());
		response.getWriter().write(gsonMessage);
		*/
		
		//AmqpTemplate template = myRabbitContext.getBean(AmqpTemplate.class);
		String queueNameOfUser="";
		try{
			String user = request.getParameter("user");
			queueNameOfUser = searchService.getQueueNameOfUserByUsername(user);
			Queue queue = new Queue(queueNameOfUser);
			myAdmin.declareQueue(queue);
			
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		message = (String) myTemplate.receiveAndConvert(queueNameOfUser);
		if(message!=null){
			String usernameTest = message.substring(message.indexOf("/")+1, message.indexOf(";"));
			String messageTest  =message.substring(message.indexOf(";")+1);
			Gson gson  =new GsonBuilder().serializeNulls().create();
			Map<String, String> messageMap = new HashMap<String, String>();
			
			//String gsonMessage = gson.toJson(messageTest, messageTest.getClass());
			//response.getWriter().write(gsonMessage);
		}
				
	}
	
}
