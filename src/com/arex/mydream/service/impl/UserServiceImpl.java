package com.arex.mydream.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arex.mydream.action.vo.UserInfo;
import com.arex.mydream.dao.UserDAO;
import com.arex.mydream.model.User;
import com.arex.mydream.service.UserService;
import com.arex.mydream.util.SHA1jiami;

@Component("userService")
public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
	public void addUser(UserInfo userInfo) {
		User user = this.cenvertVO2PO(userInfo);
		String SAUpwd = SHA1jiami.SHA1Digest(user.getuPwd());// 让密码加密
		user.setuPwd(SAUpwd); // 将加密串放在User中
		userDAO.add(user);
	}
	
	@Override
	public Boolean checkUser(String uPhone) {
		if (userDAO.searchUser(uPhone) != null) {
			return true;
		} else {
			return false;
		}
	}

	
	@Override
	public User searchUser(String uPhone) {
		// TODO Auto-generated method stub
		return userDAO.searchUser(uPhone);
	}
	
	@Override
	public User loginUser(String uPhone, String uPwd) {
		// TODO Auto-generated method stub
		User loginUser = userDAO.checkUser(uPhone);// 根据用户名查询数据库中的数据

		if (loginUser == null) {
			return null;
		} else {
			String SAUpwd = SHA1jiami.SHA1Digest(uPwd);// 加密用户输入的密码
			if (loginUser.getuPwd().equals(SAUpwd)) {
				// loginUser.setuPwd(null);//让用户密码为空
				return loginUser;
			}

		}

		return null;

	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Resource
	public void setUserDAO(UserDAO userDAO) {
	
		this.userDAO = userDAO;
	}

	private User cenvertVO2PO(UserInfo userInfo) {
		User user = null;
		if(userInfo != null){
			user = new User();
			user.setSex(userInfo.getSex());
			user.setuAddress(userInfo.getuAddress());
			user.setuBir(userInfo.getuBir());
			user.setuFlag(userInfo.getuFlag());
			user.setuId(userInfo.getuId());
			user.setuLevel(userInfo.getuLevel());
			user.setuMail(userInfo.getuMail());
			user.setuName(userInfo.getuName());
			user.setuPhone(userInfo.getuPhone());
			user.setuPwd(userInfo.getuPwd());
		}
		return user;
	}


}
