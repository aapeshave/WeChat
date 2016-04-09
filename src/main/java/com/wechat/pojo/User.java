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
	
	@ManyToMany
	private Collection<User> hostId = new ArrayList<User>();
	
	@ManyToMany(mappedBy="hostId")
	private Collection<User> friendList = new ArrayList<User>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Chatroom> joinedChatRooms = new ArrayList<Chatroom>();
	
	public User() {
		super();
		this.hostId = new ArrayList<User>();
		this.friendList = new ArrayList<User>();
		this.joinedChatRooms = new ArrayList<Chatroom>();
	}

	public User(String firstName, String lastName, String role, String birthDate,String username, String password, String email, String queueName) throws ParseException{
		super(firstName, lastName, role, birthDate);
		this.username = username;
		this.password = password;
		this.email = email;
		this.queueName = queueName;
		this.hostId = new ArrayList<User>();
		this.friendList = new ArrayList<User>();
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
	public Collection<User> getHostId() {
		return hostId;
	}
	public void setHostId(Collection<User> hostId) {
		this.hostId = hostId;
	}
	public Collection<User> getFriendList() {
		return friendList;
	}
	public void setFriendList(Collection<User> friendList) {
		this.friendList = friendList;
	}
	
	
	public Collection<Chatroom> getJoinedChatRooms() {
		return joinedChatRooms;
	}

	public void setJoinedChatRooms(Collection<Chatroom> joinedChatRooms) {
		this.joinedChatRooms = joinedChatRooms;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
