package com.arex.mydream.service;

import java.util.List;

import com.arex.mydream.action.vo.UserInfo;
import com.arex.mydream.model.User;

public interface UserService {
	public void addUser(UserInfo userInfo);

	public Boolean checkUser(String uPhone);

	public User searchUser(String uPhone);

	public User loginUser(String uPhone, String uPwd);
	
	public void updateUser(User user);

	public void updateUser(UserInfo userInfo);
	
	public User searchUser(int uId);

	public User searchUserByUname(String uname);

	/**
	 * 按照user指定的条件查询用户
	 * @param user
	 * @return
	 */
	public List<User> searchUser(User user);
	
	public List<User> searchUser(User user, int pageNo, int pageSize);

	/**
	 * 根据user条件查询个数
	 * @param user
	 * @return
	 */
	public Integer searchCount(User user);
}
