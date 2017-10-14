package com.arex.mydream.dao;

import com.arex.mydream.model.User;

public interface UserDAO {

	public void add(User user);

	public User searchUser(String uPhone);

	public User checkUser(String uPhone);
	
}
