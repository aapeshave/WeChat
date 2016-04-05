package com.wechat.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "User")
@Table(name="user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2595870480101596305L;

	@Id
	@GeneratedValue
	@Column(name="userid")
	private int userID;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
