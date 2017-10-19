package com.arex.mydream.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.ActivityDao;
import com.arex.mydream.model.Activity;

@Component
@Transactional
public class ActivityDaoImpl implements ActivityDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	public void add(Activity activity) {
		hibernateTemplate.save(activity);
	}

	@Override
	public void delete(final int aId) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("delete  Activity activity "
						+ " where activity.aId=:aId")
						.setParameter("aId", aId);
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void deleteByaGid(final int aGid) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("delete Activity activity "
						+ " where activity.aGid=:aGid")
						.setParameter("aGid", aGid);
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void update(Activity activity) {
		hibernateTemplate.update(activity);
	}

	@Override
	public Activity searchById(int aId) {
		Activity activity = null;
		List<Activity> listAY = (List<Activity>) hibernateTemplate.find("select activity from Activity activity left join Goods goods on (activity.aGid=goods.gId)"
						+ " where activity.aId=? ", aId);
		if (listAY!=null && listAY.size()>0) {
			activity = listAY.get(0);
		}
		return activity;
	}

	@Override
	public List<Activity> search() {
		
		List<Activity> listAY = (List<Activity>) hibernateTemplate.find("select activity from Activity activity left join Goods goods on (activity.aGid=goods.gId) ", null);
		return listAY;
	}

	@Override
	public List<Activity> searchByadate(String aDate) {
		
		List<Activity> listAY = (List<Activity>) hibernateTemplate.find("select activity from Activity activity left join Goods goods on (activity.aGid=goods.gId) "
				+ " where activity.aDate=? ", aDate);
		return listAY;
	}

	@Override
	public List<Activity> searchByadateWithPage(final String aDate, Integer pageNo,
			final Integer pageSize) {
		
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1) * pageSize;
		
		List<Activity> listAY = hibernateTemplate.execute(new HibernateCallback<List<Activity>>() {

			@Override
			public List<Activity> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("select activity from Activity activity left join Goods goods on (activity.aGid=goods.gId)"
						+ " where activity.aDate=:aDate")
						.setParameter("aDate", aDate).setFirstResult(firstResult).setMaxResults(pageSize);
				List<Activity> listAY = query.getResultList();
				return listAY;
			}
		});
		
		return listAY;
	}

	@Override
	public Integer searchCountByadate(String aDate) {
		List<Activity> listAY = this.searchByadate(aDate);
		int count = 0;
		if (listAY != null) {
			count = listAY.size();
		}
		return count;
	}

	@Override
	public List<Activity> searchByaGid(int aGid) {
		List<Activity> listAY = (List<Activity>) hibernateTemplate.find("select activity from Activity activity left join Goods goods on (activity.aGid=goods.gId) "
				+ " where activity.aGid=? ", aGid);
		return listAY;
	}
	
}
