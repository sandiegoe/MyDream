package com.arex.mydream.action.vo;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private String uPhone; // 电话
	private String uPwd; // 用户密码
	private String uPwd1;// 确认密码

	public UserDTO() {
		super();
	}

	public UserDTO(String uPhone, String uPwd, String uPwd1) {
		super();
		this.uPhone = uPhone;
		this.uPwd = uPwd;
		this.uPwd1 = uPwd1;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public String getuPwd1() {
		return uPwd1;
	}

	public void setuPwd1(String uPwd1) {
		this.uPwd1 = uPwd1;
	}

	@Override
	public String toString() {
		return "UserDTO [uPhone=" + uPhone + ", uPwd=" + uPwd + ", uPwd1="
				+ uPwd1 + "]";
	}
}
