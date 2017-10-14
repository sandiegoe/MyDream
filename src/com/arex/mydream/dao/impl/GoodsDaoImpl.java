package com.arex.mydream.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.GoodsDao;
import com.arex.mydream.entity.Goods;

@Component
@Transactional
public class GoodsDaoImpl implements GoodsDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void add(Goods goods) {
		hibernateTemplate.save(goods);
	}

	@Override
	public void delete(final int gId) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				session.createQuery("delete from Goods o where o.gId=:gId")
					.setParameter("gId", gId).executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void update(Goods goods) {
		hibernateTemplate.update(goods);
	}

	@Override
	public List<Goods> search() {
		List<Goods> listGS = (List<Goods>) hibernateTemplate.find("from Goods", null);
		return listGS;
	}

	@Override
	public Goods search(int gId) {
		List<Goods> listGS = (List<Goods>) hibernateTemplate.find("from Goods o where o.gId = ?", gId);
		if (listGS.size() > 0) {
			return listGS.get(0);
		}
		return null;
	}

	@Override
	public List<Goods> searchPage_Goods(Integer pageNo, final Integer pageSize) {
		
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1)*pageSize;
		
		List<Goods> listGS = hibernateTemplate.execute(new HibernateCallback<List<Goods>>(){

			@Override
			public List<Goods> doInHibernate(Session session)
					throws HibernateException {
				
				Query query = session.createQuery("from Goods o").setFirstResult(firstResult).setMaxResults(pageSize);
				List<Goods> listGS = query.getResultList();
				
				return listGS;
			}
			
		});
		
		return listGS;
	}

	@Override
	public Integer searchGoodsCount() {
		List<Goods> listGS = (List<Goods>) hibernateTemplate.find("select gId from Goods ", null);
		return listGS.size();
	}

	@Override
	public Goods searchByGname(final String gName) {
		
		Goods goods = null;
		
		List<Goods> listGS  = hibernateTemplate.execute(new HibernateCallback<List<Goods>>() {

			@Override
			public List<Goods> doInHibernate(Session session) throws HibernateException {
				List<Goods> listGS = (List<Goods>) session.createQuery("from Goods o where o.gName=:gName").setParameter("gName", gName)
					.getResultList();
				return listGS;
			}
		});
		
		if (listGS!=null && listGS.size()>0) {
			goods = listGS.get(0);
		}
		
		return goods;
	}

	@Override
	public List<Goods> searchByCondition(String hqlWhere, final Object[] objects, LinkedHashMap<String, String> orderby) {
		String orderbyStr = this.orderbyCondition(orderby);
		final String hql = "from Goods o " + hqlWhere + orderbyStr;
		List<Goods> listGS = hibernateTemplate.execute(new HibernateCallback<List<Goods>>() {

			@Override
			public List<Goods> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParameters(query, objects);
				return (List<Goods>)query.getResultList();
			}
		});
		return listGS;
	}

	private String orderbyCondition(LinkedHashMap<String, String> orderby) {
		String hqlOrderby = "";
		if (orderby != null) {
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
}
