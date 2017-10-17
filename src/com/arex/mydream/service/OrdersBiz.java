package com.arex.mydream.service;

import java.util.List;

import com.arex.mydream.action.vo.OrdersDTO;


public interface OrdersBiz {
	/**
	 * 根据订单状态和用户ID联表查询
	 * @param oStatus
	 * @param uId
	 * @return
	 */
	public List<OrdersDTO> searchByStatus(String oStatus, Integer uId);

	/**
	 * 根据订单ID修改订单状态
	 * @param od
	 */
	public void updateostatusByOid(OrdersDTO ordersDTO);

	/**
	 * 根据买家ID联表查询
	 * @param uId
	 * @return
	 */
	public List<OrdersDTO> searchByUid(Integer uId);

	/**
	 * 根据下单ID查询订单
	 * @param oPid
	 * @return
	 */
	public OrdersDTO searchOrder(int oPid);

	/**
	 * 查询订单信息
	 * 
	 * @return
	 */
	public List<OrdersDTO> searchOrders();

	/**
	 * 添加订单信息
	 * 
	 * @param od
	 */
	public void addOrders(OrdersDTO ordersDTO);

	/**
	 * 修改订单信息
	 * 
	 * @param od
	 */
	public void updateOrders(OrdersDTO ordersDTO);

	/**
	 * 删除订单信息
	 * 
	 * @param oId
	 */
	public void deleteOrders(Integer oId);

	// /**
	// * 购买订单用户店面信息
	// * @return
	// */
	// public List<Orpuusst> searchOrpuusst();
	/**
	 * 购买订单信息
	 */
//	public List<Orpu> searchOrpu();

	/**
	 * 根据订单编号查询
	 * 
	 * @param oId
	 * @return
	 */
	public OrdersDTO searchOrdersByOId(Integer oId);

	/**
	 * 分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<OrdersDTO> searchPage_Orders(Integer pageNo, Integer pageSize);

	/**
	 * 查询多少页
	 * 
	 * @return
	 */
	public Integer searchOrdersCount();

	/**
	 * 多表连接查询 查询id
	 * 
	 * @param oId
	 * @return
	 */
	public OrdersDTO searchByOid(Integer oId);

	/**
	 * 订单 购买 商品 用户 查询
	 * 
	 * @return
	 */
	public List<OrdersDTO> searchOrders(OrdersDTO ordersDTO, Integer pageNo, Integer PageSize);
	
	public List<OrdersDTO> searchOrders(OrdersDTO ordersDTO);

	public OrdersDTO searchById(Integer oId);

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
	
	

	/**
	 * 记录数
	 * 
	 * @return
	 */
	public Integer searchCountBypSid(OrdersDTO ordersDTO);
	
	public Integer searchCountBypSid(int pSid);

	/**
	 * 增加订单
	 * @param orders
	 */
	public void add(OrdersDTO ordersDTO);
}