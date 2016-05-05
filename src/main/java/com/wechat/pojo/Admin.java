package com.wechat.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="admin_table")
@PrimaryKeyJoinColumn(name="personId")
public class Admin extends Person implements Serializable{
	private String username;
	private String password;
	private String branch;
	private Date isCreatedOn;
	
	public Admin(){
		this.isCreatedOn = new Date();
	}
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
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
		return "Admin [username=" + username + ", branch=" + branch + "]";
	}
	
	public Date getIsCreatedOn() {
		return isCreatedOn;
	}
	
}
