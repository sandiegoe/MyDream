package com.arex.mydream.dao.impl.test;

import org.junit.Test;

import com.arex.mydream.dao.UserDAO;
import com.arex.mydream.dao.impl.UserDAOImpl;
import com.arex.mydream.entity.User;

public class UserDAOImplTest {
	
	private UserDAO userDAO = new UserDAOImpl();

	@Test
	public void testAddUser() {
		
		User user= new User();
		user.setSex("男");
		user.setuName("xiebo");
		user.setuAddress("安徽合肥");
		user.setuBir("1992.12.13");
		user.setuLevel("VIP1");
		user.setuMail("xiebo@126.com");
		user.setuPhone("18226737408");
		user.setuPwd("123456");
		
		userDAO.add(user);
	}

}
