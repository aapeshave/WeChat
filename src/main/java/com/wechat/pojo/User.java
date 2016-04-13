package com.wechat.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="user_table")
@PrimaryKeyJoinColumn(name="personId")
public class User extends Person implements Serializable{
	@Column(nullable=false)
	private String username;
	private String password;
	private String email;
	private String queueName;
	private Date isCreatedOn;
	private Boolean isActive;
	
	@ManyToMany(mappedBy="userList")
	private Collection<Friend> friendList = new ArrayList<Friend>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Chatroom> joinedChatRooms = new ArrayList<Chatroom>();
	
	public User() {
		super();
		this.joinedChatRooms = new ArrayList<Chatroom>();
		this.friendList = new ArrayList<Friend>();
	}

	public User(String firstName, String lastName, String role, String birthDate,String username, String password, String email, String queueName) throws ParseException{
		super(firstName, lastName, role, birthDate);
		this.username = username;
		this.password = password;
		this.email = email;
		this.queueName = queueName;
		this.isActive = true;
		this.isCreatedOn = new Date();
		this.friendList = new ArrayList<Friend>();
		this.joinedChatRooms = new ArrayList<Chatroom>();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public Collection<Chatroom> getJoinedChatRooms() {
		return joinedChatRooms;
	}

	public void setJoinedChatRooms(Collection<Chatroom> joinedChatRooms) {
		this.joinedChatRooms = joinedChatRooms;
	}
	public Date getIsCreatedOn() {
		return isCreatedOn;
	}

	public void setIsCreatedOn(Date isCreatedOn) {
		this.isCreatedOn = isCreatedOn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Collection<Friend> getFriendList() {
		return friendList;
	}

	public void setFriendList(Collection<Friend> friendList) {
		this.friendList = friendList;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
