package com.wechat.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="friend_table")
public class Friend implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="friendID")
	private long friendId;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<User> userList  = new ArrayList<User>();
	
	private Boolean isAccepted;
	private String status;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="personId")
	private User connectedUser;
	
	public Friend(){
		super();
	}
	
	
	public long getFriendId() {
		return friendId;
	}
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<User> getUserList() {
		return userList;
	}

	public void setUserList(Collection<User> userList) {
		this.userList = userList;
	}


	public User getConnectedUser() {
		return connectedUser;
	}


	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}
	
	
	
}
