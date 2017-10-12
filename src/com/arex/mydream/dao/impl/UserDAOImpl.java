package com.arex.mydream.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.UserDAO;
import com.arex.mydream.entity.User;

@Component
public class UserDAOImpl implements UserDAO {

	@Override
	public void add(User user) {
		Configuration config = new Configuration();
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		factory.close();
	}

}
