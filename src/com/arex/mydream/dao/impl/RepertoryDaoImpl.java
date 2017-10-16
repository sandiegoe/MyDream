package com.arex.mydream.dao.impl;

import java.util.ArrayList;
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

import com.arex.mydream.action.vo.RepertoryDTO;
import com.arex.mydream.dao.RepertoryDao;
import com.arex.mydream.model.Repertory;

@Component
@Transactional
public class RepertoryDaoImpl implements RepertoryDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Repertory> search() {
		List<Repertory> listRY = (List<Repertory>) hibernateTemplate.find("from Repertory o ", null);
		return listRY;
	}


	@Override
	public Integer searchRepertoryCount(Repertory repertory) {
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		if (repertory == null) {
			return null;
		}
		if (repertory.getrGid()!=0) {
			hqlWhere += " and rGid=? ";
			params.add(repertory.getrGid());
		}
		if (repertory.getrId() != 0) {
			hqlWhere += " and rId=? ";
			params.add(repertory.getrId());
		}
		if (repertory.getrNum() != 0) {
			hqlWhere += " and rNum=? ";
			params.add(repertory.getrNum());
		}
		if (repertory.getrSid() != 0) {
			hqlWhere += " and rSid=? ";
			params.add(repertory.getrSid());
		}
		
		return this.search(hqlWhere, params.toArray(), orderby).size();
	}
	
	@Override
	public Integer searchRepertoryCount(RepertoryDTO repertoryDTO) {
		
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		
		if (repertoryDTO == null) {
			return null;
		}
		if (repertoryDTO.getrGid()!=0) {
			hqlWhere += " and rGid=? ";
			params.add(repertoryDTO.getrGid());
		}
		if (repertoryDTO.getrId() != 0) {
			hqlWhere += " and rId=? ";
			params.add(repertoryDTO.getrId());
		}
		if (repertoryDTO.getrNum() != 0) {
			hqlWhere += " and rNum=? ";
			params.add(repertoryDTO.getrNum());
		}
		if (repertoryDTO.getrSid() != 0) {
			hqlWhere += " and rSid=? ";
			params.add(repertoryDTO.getrSid());
		}
		if (repertoryDTO.getgName()!=null && !"".equals(repertoryDTO.getgName())) {
			hqlWhere += " and gName like ? ";
			params.add(repertoryDTO.getgName());
		}
		if (repertoryDTO.getgType()!=null && !"".equals(repertoryDTO.getgType())) {
			hqlWhere += " and gType like ? ";
			params.add(repertoryDTO.getgType());
		}
		if (repertoryDTO.getgAddress() != null && !"".equals(repertoryDTO.getgAddress())) {
			hqlWhere += " and gAddress like ? ";
			params.add(repertoryDTO.getgAddress());
		}
		if (repertoryDTO.getgPrice() != 0) {
			if (repertoryDTO.getgPrice() == 1) {
				orderby.put("gPrice", "asc");
			} else if (repertoryDTO.getgPrice() == 2) {
				orderby.put("gPrice", "desc");
			}
		}
		
		return this.search(hqlWhere, params.toArray(), orderby).size();
	}

	@Override
	public Integer searchGiftCount(Repertory repertory) {
		List<Repertory> listRY = hibernateTemplate.execute(new HibernateCallback<List<Repertory>>() {

			@Override
			public List<Repertory> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("select o from Repertory o left join Goods g on (o.rGid=g.gId) where g.gType='礼盒'");
				List<Repertory> listRY = query.getResultList();
				return listRY;
			}
		});
		
		return listRY.size();
	}

	@Override
	public List<Repertory> searchBygId(Integer gid) {
		List<Repertory> listRY = (List<Repertory>) hibernateTemplate.find("from Repertory o where o.rGid=?", gid);
		return listRY;
	}

	@Override
	public List<Repertory> searchGift(Repertory repertory) {
		List<Repertory> listRY = hibernateTemplate.execute(new HibernateCallback<List<Repertory>>() {

			@Override
			public List<Repertory> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("select o from Repertory o left join Goods g on (o.rGid=g.gId) where g.gType='礼盒'");
				List<Repertory> listRY = query.getResultList();
				return listRY;
			}
		});
		return listRY;
	}

	@Override
	public List<Repertory> searchGift(Repertory repertory, Integer pageNo, final Integer pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1)*pageSize;
		List<Repertory> listRY = hibernateTemplate.execute(new HibernateCallback<List<Repertory>>() {

			@Override
			public List<Repertory> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("select o from Repertory o left join Goods g on (o.rGid=g.gId) where g.gType='礼盒'")
						.setFirstResult(firstResult).setMaxResults(pageSize);
				List<Repertory> listRY = query.getResultList();
				return listRY;
			}
		});
		return listRY;
	}

	@Override
	public void addRepertory(Repertory re) {
		hibernateTemplate.save(re);
	}

	@Override
	public void deletRepertory(final Repertory re) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				session.createQuery("delete from Repertory o where o.rGid=:rGid and rSid=:rSid")
					.setParameter("rGid", re.getrGid()).setParameter("rSid", re.getrSid())
					.executeUpdate();
				return null;
			}
		});
	}


	@Override
	public List<Repertory> search(String hqlWhere, final Object[] objects, LinkedHashMap<String, String> orderby) {
		String orderbyStr = this.orderbyCondition(orderby);
		final String hql = "select o from Repertory o left join Goods g on (o.rGid=g.gId) left join Store s on (o.rSid=s.sId) " + hqlWhere + orderbyStr;
		List<Repertory> listRY = hibernateTemplate.execute(new HibernateCallback<List<Repertory>>() {

			@Override
			public List<Repertory> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParameters(query, objects);
				return (List<Repertory>)query.getResultList();
			}
		});
		return listRY;
	}


	@Override
	public List<Repertory> searchPage_Repertory(String hqlWhere, final Object[] objects,
			LinkedHashMap<String, String> orderby, Integer pageNo, final Integer pageSize) {
		String orderbyStr = this.orderbyCondition(orderby);
		final String hql = "select o from Repertory o left join Goods g on (o.rGid=g.gId) left join Store s on (o.rSid=s.sId) " + hqlWhere + orderbyStr;
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1)*pageSize;
		List<Repertory> listRY = hibernateTemplate.execute(new HibernateCallback<List<Repertory>>() {

			@Override
			public List<Repertory> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParameters(query, objects);
				query.setFirstResult(firstResult).setMaxResults(pageSize);
				return (List<Repertory>)query.getResultList();
			}
		});
		return listRY;
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
}
