package com.arex.mydream.service;

import com.arex.mydream.action.vo.UserInfo;
import com.arex.mydream.model.User;

public interface UserService {
	public void addUser(UserInfo userInfo);

	public Boolean checkUser(String uPhone);

	public User searchUser(String uPhone);

	public User loginUser(String uPhone, String uPwd);
	
	public void updateUser(User user);

	public void updateUser(UserInfo userInfo);
}
