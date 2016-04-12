package com.wechat.pojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
	
	private User sender;
	private User receiver;
	private Timestamp timeStamp;
	private Boolean isArchived;
	
	public ChatSession() {
		this.isArchived  = Boolean.FALSE;
	}
	
	@OneToMany(mappedBy="chatSession", fetch = FetchType.EAGER)
	private Collection<ChatMessage> chatHistory = new ArrayList<ChatMessage>();
	
	public long getChatSessionID() {
		return chatSessionID;
	}
	public void setChatSessionID(long chatSessionID) {
		this.chatSessionID = chatSessionID;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
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
}
