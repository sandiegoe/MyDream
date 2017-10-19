package com.arex.mydream.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.arex.mydream.model.User;

public interface UserDao {

	public void add(User user);

	public User searchUser(String uPhone);

	public User checkUser(String uPhone);

	public void update(User user);

	public User searchUser(int uId);

	public User searchUserByUname(String uname);

	/**
	 * 根据添加查询
	 * @param hqlWhere
	 * @param objects
	 * @param orderby
	 * @return
	 */
	List<User> searchUser(String hqlWhere, Object[] objects,
			LinkedHashMap<String, String> orderby);
	
	List<User> searchUser(String hqlWhere, Object[] objects,
			LinkedHashMap<String, String> orderby, int pageNo, int pageSize);
}
