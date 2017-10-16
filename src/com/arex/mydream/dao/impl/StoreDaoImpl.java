package com.arex.mydream.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.StoreDao;
import com.arex.mydream.model.Goods;
import com.arex.mydream.model.Store;

@Component
@Transactional
public class StoreDaoImpl implements StoreDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void add(Store store) {
		hibernateTemplate.save(store);
	}

	@Override
	public void delete(final int sId) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				session.createQuery("delete from Store o where o.sId=:sId")
					.setParameter("sId", sId).executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void update(Store store) {
		hibernateTemplate.update(store);
	}

	@Override
	public List<Store> search() {
		List<Store> listSE = (List<Store>) hibernateTemplate.find("from Store", null);
		return listSE;
	}

	@Override
	public Store searchStore(int sId) {
		List<Store> listSE  = (List<Store>) hibernateTemplate.find("from Store o where o.sId = ?", sId);
		if (listSE.size() > 0) {
			return listSE.get(0);
		}
		return null;
	}

	@Override
	public List<Store> searchPage_Store(Integer pageNo, final Integer pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1)*pageSize;
		
		List<Store> listSE = hibernateTemplate.execute(new HibernateCallback<List<Store>>(){

			@Override
			public List<Store> doInHibernate(Session session)
					throws HibernateException {
				
				Query query = session.createQuery("from Store s").setFirstResult(firstResult).setMaxResults(pageSize);
				List<Store> listSE = query.getResultList();
				
				return listSE;
			}
			
		});
		
		return listSE;
	}

	@Override
	public Integer searchStoreCount() {
		List<Store> listSE = (List<Store>) hibernateTemplate.find("select sId from Store ", null);
		return listSE.size();
	}

	@Override
	public Store searchStoreBySuid(final Integer sUid) {
		Store store = hibernateTemplate.execute(new HibernateCallback<Store>() {

			@Override
			public Store doInHibernate(Session session) throws HibernateException {
				List<Store> listSE = session.createQuery("from Store o where o.suid=:suid")
					.setParameter("suid", sUid).getResultList();
				if (listSE!=null && listSE.size()>0) {
					return listSE.get(0);
				}
				return null;
			}
		});
		
		return store;
	}

}
