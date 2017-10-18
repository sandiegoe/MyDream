package com.arex.mydream.dao;

import com.arex.mydream.model.User;

public interface UserDao {

	public void add(User user);

	public User searchUser(String uPhone);

	public User checkUser(String uPhone);

	public void update(User user);

	public User searchUser(int uId);

	public User searchUserByUname(String uname);
	
}
