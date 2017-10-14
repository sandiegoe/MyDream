package com.arex.mydream.dao.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.dao.UserDAO;
import com.arex.mydream.dao.impl.UserDAOImpl;
import com.arex.mydream.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class UserDaoImplTest {
	
	private UserDao userDAO = new UserDaoImpl();
	@Resource(name="userDAOImpl")
	private UserDao userDAO2;

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

	@Test
	public void testAddUser2() {
		
		User user= new User();
		user.setSex("男");
		user.setuName("xiebo");
		user.setuAddress("安徽合肥");
		user.setuBir("1992.12.13");
		user.setuLevel("VIP1");
		user.setuMail("xiebo@126.com");
		user.setuPhone("18226737408");
		user.setuPwd("123456");
		
		//System.out.println(userDAO2);
		/*ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		UserDAO userDAO = context.getBean("userDAOImpl", UserDAOImpl.class);
		userDAO.add(user);*/
		
		userDAO2.add(user);
	}
}
