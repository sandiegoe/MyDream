package com.arex.mydream.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.UserDao;
import com.arex.mydream.model.Orders;
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

	@Override
	public User searchUser(int uId) {
		List<User> users = (List<User>)hibernateTemplate.find("from User u where u.uId = '" + uId + "'");
		User user = null;
		if(users != null && users.size() > 0){
			user = users.get(0);
		}
		return user;
	}

	@Override
	public User searchUserByUname(String uname) {
		List<User> users = (List<User>)hibernateTemplate.find("from User u where u.uName = '" + uname + "'");
		User user = null;
		if(users != null && users.size() > 0){
			user = users.get(0);
		}
		return user;
	}
	
	@Override
	public List<User> searchUser(String hqlWhere, final Object[] objects, LinkedHashMap<String,String> orderby) {
		
		String orderbyStr = this.orderbyCondition(orderby);
		final String hql = "select user from User user " + hqlWhere + orderbyStr;
		
		List<User> listUR = hibernateTemplate.execute(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParameters(query, objects);
				return (List<User>)query.getResultList();
			}
		});
		return listUR;
	}
	
	private String orderbyCondition(LinkedHashMap<String, String> orderby) {
		String hqlOrderby = "";
		if (orderby != null && !orderby.isEmpty()) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("order by ");
			for (Entry entry : orderby.entrySet()) {
				buffer.append(entry.getKey() + " " + entry.getValue() + " ,");
			}
			hqlOrderby = buffer.deleteCharAt(buffer.length() - 1).toString();
		}
		return hqlOrderby;
	}
	
	private void setQueryParameters(Query query, Object[] objects) {
		for (int i = 0; objects != null && i < objects.length; ++i) {
			query.setParameter(i, objects[i]);
		}
	}

	@Override
	public List<User> searchUser(String hqlWhere, final Object[] objects,
			LinkedHashMap<String, String> orderby, int pageNo, final int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1) * pageSize;
		String orderbyStr = this.orderbyCondition(orderby);
		final String hql = "select user from User user " + hqlWhere + orderbyStr;
		
		List<User> listUR = hibernateTemplate.execute(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParameters(query, objects);
				query.setFirstResult(firstResult).setMaxResults(pageSize);
				return (List<User>)query.getResultList();
			}
		});
		return listUR;
	}

}
