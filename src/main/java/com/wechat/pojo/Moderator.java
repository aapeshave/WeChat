package com.wechat.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="moderator_table")
@PrimaryKeyJoinColumn(name="personId")
public class Moderator extends User implements Serializable{

	
	private String username;
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="moderator")
	private Collection<Chatroom> managedChatRooms = new ArrayList<Chatroom>();
	
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
	
	@Override
	public String toString() {
		return "Moderator [username=" + username + ", password=" + password + "]";
	}
	
}
