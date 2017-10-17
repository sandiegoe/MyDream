package com.arex.mydream.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.arex.mydream.model.Orders;

public interface OrdersDao {

	
	public List<Orders> searchByStatus(String oStatus, Integer uId);

	public void updateostatusByOid(Orders order);

	
	public List<Orders> searchByUid(Integer uId);

	
	public Orders searchOrder(int oPid);

	
	public List<Orders> search();


	public void add(Orders order);

	
	public void update(Orders order);

	
	public void delete(Integer oId);

	// public List<Orpuusst> searchOrpuusst();
	
//	public List<Orpu> searchOrpu();

	
	public Orders searchByOId(Integer oId);

	

	public List<Orders> searchPage_Orders(Integer pageNo, Integer pageSize);

	
	public Integer searchOrdersCount();

	
//	public Ougp searchByOid(Integer oId);

	
//	public List<Ougp> searchOugp();

	
//	public Ougp searchById(Integer oId);

	// /**
	// * 订单 购买 商品 用户 分页查询
	// * @return
	// */
	// public List<Ougp> searchOugp(Integer pageNo, Integer pageSize);
	// /**
	// * 记录数
	// * @return
	// */
	// public Integer searchAllOugpCount() ;
	
	List<Orders> searchOrder(String hqlWhere, Object[] objects,
			LinkedHashMap<String, String> orderby, Integer pageNo,
			Integer pageSize);
	
	List<Orders> searchOrder(String hqlWhere, Object[] objects,
			LinkedHashMap<String, String> orderby);

	
	public Integer searchCountBypSid(int pSid);


	public void addOrders(Orders order);

}