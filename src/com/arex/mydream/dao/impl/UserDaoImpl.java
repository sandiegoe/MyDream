package com.arex.mydream.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.UserDao;
import com.arex.mydream.model.User;



@Component("userDAO")
public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void add(User user) {
		hibernateTemplate.save(user);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public User searchUser(String uPhone) {
		List<User> users = (List<User>)hibernateTemplate.find("from User u where u.uPhone = '" + uPhone + "'");
		User user = null;
		if(users != null && users.size() > 0){
			user = users.get(0);
		}
		return user;
	}

	@Override
	public User checkUser(String uPhone) {
		// TODO Auto-generated method stub
		List<User> users = (List<User>)hibernateTemplate.find("from User u where u.uPhone = '" + uPhone + "'");
		User user = null;
		if(users != null && users.size() > 0){
			user = users.get(0);
		}
		return user;
	}

	@Override
	public void update(User user) {
		hibernateTemplate.update(user);
	}

}
