package com.wechat.pojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chatSession_table")
public class ChatSession {
	
	@Id
	@GeneratedValue
	private long chatSessionID;
	
	private String sender;
	private String receiver;
	private Timestamp timeStamp;
	private Boolean isArchived;
	
	public ChatSession() {
		this.isArchived  = Boolean.FALSE;
	}
	
	
	
	public ChatSession(String sender, String receiver) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.isArchived = Boolean.FALSE;
		this.timeStamp  =new Timestamp(new Date().getTime());
		this.chatHistory = new ArrayList<ChatMessage>();
	}

	@OneToMany(mappedBy="chatSession", fetch = FetchType.EAGER)
	private Collection<ChatMessage> chatHistory = new ArrayList<ChatMessage>();
	
	public long getChatSessionID() {
		return chatSessionID;
	}
	public void setChatSessionID(long chatSessionID) {
		this.chatSessionID = chatSessionID;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Boolean getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	@Override
	public String toString() {
		return "ChatSession [chatSessionID=" + chatSessionID + ", sender=" + sender + ", receiver=" + receiver
				+ ", timeStamp=" + timeStamp + ", isArchived=" + isArchived + "]";
	}	
	
	
	
}
