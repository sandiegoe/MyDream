package com.arex.mydream.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.OrdersDao;
import com.arex.mydream.model.Orders;

@Component
@Transactional
public class OrdersDaoImpl implements OrdersDao {
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	/**
	 * 根据用户Id和订单状态查询所有订单
	 */
	@Override
	public List<Orders> searchByStatus(String oStatus, Integer uId) {
		List<Orders> listOR = (List<Orders>) hibernateTemplate.find("select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
				+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId) "
				+ "where orders.oStatus like ? and user.uId=? ", oStatus, uId);
		return listOR;
		
	}

	/**
	 * 根据订单id更新订单状态
	 */
	@Override
	public void updateostatusByOid(Orders orders) {
		Orders searchOrder = this.searchByOId(orders.getoId());
		searchOrder.setoStatus(orders.getoStatus());
		this.update(searchOrder);
	}

	/**
	 * 查询指定用户的所有订单
	 */
	@Override
	public List<Orders> searchByUid(Integer uId) {
		List<Orders> listOR = (List<Orders>) hibernateTemplate.find("select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
				+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId) "
				+ "where user.uId=?", uId);
		return listOR;
	}

	/**
	 * 根据订单id查询订单
	 */
	@Override
	public Orders searchOrder(int oPid) {
		
		List<Orders> listOR = (List<Orders>) hibernateTemplate.find("select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
				+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId) "
				+ "where orders.oPid=? ", oPid);
		Orders orders = null;
		if (listOR!=null && listOR.size()>0) {
			orders = listOR.get(0);
		}
		
		return orders;
	}

	/**
	 * 查询所有订单
	 */
	@Override
	public List<Orders> search() {
		List<Orders> listOR = (List<Orders>) hibernateTemplate.find("select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
				+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId) ", null);
		return listOR;
	}

	/**
	 * 添加一个订单
	 */
	@Override
	public void add(Orders orders) {
		hibernateTemplate.save(orders);
	}

	/**
	 * 更新订单
	 */
	@Override
	public void update(Orders orders) {
		hibernateTemplate.update(orders);
	}

	/**
	 * 根据oId删除订单
	 */
	@Override
	public void delete(final Integer oId) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("delete from Orders orders where orders.oId=:oId")
					.setParameter("oId", oId);
				query.executeUpdate();
				return null;
			}
		});
	}

	/**
	 * 根据订单oId查询订单
	 */
	@Override
	public Orders searchByOId(Integer oId) {
		List<Orders> listOR = (List<Orders>) hibernateTemplate.find("select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
				+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId) "
				+ "where orders.oId=? ", oId);
		Orders orders = null;
		if (listOR!=null && listOR.size()>0) {
			orders = listOR.get(0);
		}
		
		return orders;
	}

	/**
	 * 分页查询订单
	 */
	@Override
	public List<Orders> searchPage_Orders(Integer pageNo, final Integer pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1) * pageSize;
		List<Orders> listOR = hibernateTemplate.execute(new HibernateCallback<List<Orders>>() {

			@Override
			public List<Orders> doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery("select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
						+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId)")
						.setFirstResult(firstResult).setMaxResults(pageSize);
				return query.getResultList();
			}
		});
		
		return listOR;
	}

	/**
	 * 查询订单的数量
	 */
	@Override
	public Integer searchOrdersCount() {
		Integer count = null;
		List<Orders> listOR = this.search();
		if (listOR != null) {
			count = listOR.size();
		}
		return count;
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

	/**
	 * 按照条件分页查询
	 */
	@Override
	public List<Orders> searchOrder(String hqlWhere, final Object[] objects, LinkedHashMap<String, String> orderby, Integer pageNo, final Integer pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		final int firstResult = (pageNo-1) * pageSize;
		
		String orderbyStr = this.orderbyCondition(orderby);
		final String hql = "select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
				+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId) " + hqlWhere + orderbyStr;
		
		List<Orders> listOR = hibernateTemplate.execute(new HibernateCallback<List<Orders>>() {

			@Override
			public List<Orders> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParameters(query, objects);
				query.setFirstResult(firstResult).setMaxResults(pageSize);
				return (List<Orders>)query.getResultList();
			}
		});
		return listOR;
	}
	
	/**
	 * 按照条件查询订单
	 */
	@Override
	public List<Orders> searchOrder(String hqlWhere, final Object[] objects,
			LinkedHashMap<String, String> orderby) {
		
		String orderbyStr = this.orderbyCondition(orderby);
		final String hql = "select orders from Orders orders left join Purchase purchase on(orders.oPid=purchase.pId) "
				+ "left join Goods goods on (purchase.pGid=goods.gId) left join User user on (purchase.pUid=user.uId) " + hqlWhere + orderbyStr;
		
		List<Orders> listOR = hibernateTemplate.execute(new HibernateCallback<List<Orders>>() {

			@Override
			public List<Orders> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParameters(query, objects);
				return (List<Orders>)query.getResultList();
			}
		});
		
		return listOR;
	}
	
	

	/**
	 * 按照店家sid查询订单数量
	 */
	@Override
	public Integer searchCountBypSid(int pSid) {
		
		Integer count = null;
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		if (pSid != 0) {
			hqlWhere += " and pSid=? ";
			params.add(pSid);
		}
		List<Orders> listOR = this.searchOrder(hqlWhere, params.toArray(), orderby);
		
		if (listOR!=null) {
			count = listOR.size();
		}
		
		return count;
	}

	/**
	 * 添加一个订单
	 */
	@Override
	public void addOrders(Orders orders) {
		hibernateTemplate.save(orders);
	}

}
