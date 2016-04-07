package com.wechat.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javassist.expr.NewArray;

@Entity
@Table(name="chatroom_table")
public class Chatroom {
	
	@Id
	@GeneratedValue
	private long chatRoomID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Moderator moderator;
	
	@ManyToMany(mappedBy="joinedChatRooms")
	private Collection<User> participants = new ArrayList<User>();
	
	private String exchange;
	private Boolean isActive;
	private Boolean isFileSharingEnabled;
	
	public Chatroom(){
		super();
		this.participants = new ArrayList<User>();
	}
	
	public long getChatRoomID() {
		return chatRoomID;
	}
	public void setChatRoomID(long chatRoomID) {
		this.chatRoomID = chatRoomID;
	}
	public Moderator getModerator() {
		return moderator;
	}
	public void setModerator(Moderator moderator) {
		this.moderator = moderator;
	}
	
	
	public Collection<User> getParticipants() {
		return participants;
	}
	public void setParticipants(Collection<User> participants) {
		this.participants = participants;
	}
	
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsFileSharingEnabled() {
		return isFileSharingEnabled;
	}
	public void setIsFileSharingEnabled(Boolean isFileSharingEnabled) {
		this.isFileSharingEnabled = isFileSharingEnabled;
	}
	
	
}
