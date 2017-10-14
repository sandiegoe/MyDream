package com.arex.mydream.entity.view;

import java.io.Serializable;

public class UserDTO implements Serializable{

	private String userName;
	private String userPwd;

	public UserDTO(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", userPwd=" + userPwd + "]";
	}

}
