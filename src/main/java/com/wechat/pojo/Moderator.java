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

	
	private String usernameModeraotr;
	private String passowrdModerator;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="moderator")
	private Collection<Chatroom> managedChatRooms = new ArrayList<Chatroom>();
	
	public String getUsernameModerator() {
		return usernameModeraotr;
	}
	public void setUsernameModerator(String username) {
		this.usernameModeraotr = username;
	}
	public String getPasswordModerator() {
		return passowrdModerator;
	}
	public void setPasswordModerator(String password) {
		this.passowrdModerator = password;
	}
	
	@Override
	public String toString() {
		return "Moderator [username=" + usernameModeraotr + ", password=" + passowrdModerator + "]";
	}
	
}
