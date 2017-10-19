package com.arex.mydream.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arex.mydream.action.vo.UserInfo;
import com.arex.mydream.dao.UserDao;
import com.arex.mydream.model.User;
import com.arex.mydream.service.UserService;
import com.arex.mydream.util.SHA1jiami;

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService{

	private UserDao userDAO;
	
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

	public UserDao getUserDAO() {
		return userDAO;
	}

	@Resource
	public void setUserDAO(UserDao userDAO) {
	
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

	@Override
	public void updateUser(User user) {
		userDAO.update(user);
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		User user = this.cenvertVO2PO(userInfo);
		userDAO.update(user);
	}

	@Override
	public User searchUser(int uId) {
		return userDAO.searchUser(uId);
	}
	
	@Override
	public User searchUserByUname(String uname) {
		return userDAO.searchUserByUname(uname);
	}

	@Override
	public List<User> searchUser(User user) {
		
		List<User> listUR = null;
		if (user == null) {
			return listUR;
		}
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		if (user.getSex()!=null && !"".equals(user.getSex())) {
			hqlWhere += " and user.sex=? ";
			params.add(user.getSex());
		}
		if (user.getuAddress()!=null && !"".equals(user.getuAddress())) {
			hqlWhere += " and  user.uAddress=? ";
			params.add(user.getuAddress());
		}
		if (user.getuBir()!=null && !"".equals(user.getuBir())) {
			hqlWhere += " and  user.uBir=? ";
			params.add(user.getuBir());
		}
		if (user.getuFlag()!=null && !"".equals(user.getuFlag())) {
			hqlWhere += " and  user.uFlag=? ";
			params.add(user.getuFlag());
		}
		if (user.getuId() != 0) {
			hqlWhere += " and  user.uId=? ";
			params.add(user.getuId());
		}
		if (user.getuLevel()!=null && !"".equals(user.getuLevel())) {
			hqlWhere += " and  user.uLevel=? ";
			params.add(user.getuLevel());
		}
		if (user.getuMail()!=null && !"".equals(user.getuMail())) {
			hqlWhere += " and  user.uMail=? ";
			params.add(user.getuMail());
		}
		if (user.getuName()!=null && !"".equals(user.getuName())) {
			hqlWhere += " and  user.uName=? ";
			params.add(user.getuName());
		}
		if (user.getuPhone()!=null && !"".equals(user.getuPhone())) {
			hqlWhere += " and  user.uPhone like ? ";
			params.add(user.getuPhone());
		}
		if (user.getuPwd()!=null && !"".equals(user.getuPwd())) {
			hqlWhere += " and  user.uPwd=? ";
			params.add(user.getuPwd());
		}
		
		listUR = userDAO.searchUser(hqlWhere, params.toArray(), orderby);
		
		return listUR;
	}

	@Override
	public List<User> searchUser(User user, int pageNo, int pageSize) {
		List<User> listUR = null;
		if (user == null) {
			return listUR;
		}
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		if (user.getSex()!=null && !"".equals(user.getSex())) {
			hqlWhere += " and  user.sex=? ";
			params.add(user.getSex());
		}
		if (user.getuAddress()!=null && !"".equals(user.getuAddress())) {
			hqlWhere += " and  user.uAddress=? ";
			params.add(user.getuAddress());
		}
		if (user.getuBir()!=null && !"".equals(user.getuBir())) {
			hqlWhere += " and  user.uBir=? ";
			params.add(user.getuBir());
		}
		if (user.getuFlag()!=null && !"".equals(user.getuFlag())) {
			hqlWhere += " and  user.uFlag=? ";
			params.add(user.getuFlag());
		}
		if (user.getuId() != 0) {
			hqlWhere += " and  user.uId=? ";
			params.add(user.getuId());
		}
		if (user.getuLevel()!=null && !"".equals(user.getuLevel())) {
			hqlWhere += " and  user.uLevel=? ";
			params.add(user.getuLevel());
		}
		if (user.getuMail()!=null && !"".equals(user.getuMail())) {
			hqlWhere += " and  user.uMail=? ";
			params.add(user.getuMail());
		}
		if (user.getuName()!=null && !"".equals(user.getuName())) {
			hqlWhere += " and  user.uName=? ";
			params.add(user.getuName());
		}
		if (user.getuPhone()!=null && !"".equals(user.getuPhone())) {
			hqlWhere += " and  user.uPhone like ? ";
			params.add(user.getuPhone());
		}
		if (user.getuPwd()!=null && !"".equals(user.getuPwd())) {
			hqlWhere += " and  user.uPwd=? ";
			params.add(user.getuPwd());
		}
		
		listUR = userDAO.searchUser(hqlWhere, params.toArray(), orderby, pageNo, pageSize);
		
		return listUR;
	}

	@Override
	public Integer searchCount(User user) {
		List<User> listUR = this.searchUser(user);
		return listUR.size();
	}
}
