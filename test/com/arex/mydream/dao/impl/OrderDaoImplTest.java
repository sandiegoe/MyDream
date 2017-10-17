package com.arex.mydream.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.dao.OrdersDao;
import com.arex.mydream.model.Orders;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class OrderDaoImplTest {

	@Resource(name="ordersDaoImpl")
	OrdersDao orderDao;
	
	@Test
	public void testSearchByStatus() {
		Integer uId = 10;
		String oStatus = "发送中";
		List<Orders> listOS = orderDao.searchByStatus(oStatus, uId);
		for (Orders orders : listOS) {
			System.out.println(orders);
		}
	}

	@Test
	public void testUpdateostatusByOid() {
		Orders orders = new Orders();
		orders.setoId(1);
		orders.setoStatus("不翼而飞");
		orderDao.updateostatusByOid(orders);
	}

	@Test
	public void testSearchByUid() {
		int uId = 10;
		List<Orders> listOS = orderDao.searchByUid(uId);
		for (Orders orders : listOS) {
			System.out.println(orders);
		}
	}

	@Test
	public void testSearchOrderInt() {
		Orders orders = orderDao.searchOrder(1);
		System.out.println(orders);
	}

	@Test
	public void testSearch() {
		List<Orders> listOS = orderDao.search();
		for (Orders orders : listOS) {
			System.out.println(orders);
		}
	}

	@Test
	public void testAdd() {
		Orders order = new Orders();
		order.setoAddress("安徽省合肥市");
		order.setoEnddate("2017-10-30");
		order.setoPid(5);
		order.setoStartdate("2017-10-16");
		order.setoStatus("发送中");
		orderDao.add(order);
	}

	@Test
	public void testUpdate() {
		Orders orders = orderDao.searchByOId(1);
		orders.setoStatus("已经完成");
		orderDao.update(orders);
	}

	@Test
	public void testDelete() {
		orderDao.delete(4);
	}

	@Test
	public void testSearchByOId() {
		Integer oId = 1;
		Orders orders = orderDao.searchByOId(oId );
		System.out.println(orders);
	}

	@Test
	public void testSearchPage_Orders() {
		Integer pageSize = 2;
		Integer pageNo = 2;
		List<Orders> listOS = orderDao.searchPage_Orders(pageNo, pageSize);
		for (Orders orders : listOS) {
			System.out.println(orders);
		}
	}

	@Test
	public void testSearchOrdersCount() {
		Integer count = orderDao.searchOrdersCount();
		System.out.println(count);
	}

	@Test
	public void testSearchOrderStringObjectArrayLinkedHashMapOfStringStringIntegerInteger() {
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		hqlWhere += " and oStatus=? ";
		params.add("发送中");
		hqlWhere += " and oPid=?";
		params.add(5);
		
		Integer pageNo = 1;
		Integer pageSize = 10;
		List<Orders> listRS = orderDao.searchOrder(hqlWhere, params.toArray(), orderby, pageNo, pageSize);
		for (Orders orders : listRS) {
			System.out.println(orders);
		}
	}

	@Test
	public void testSearchOrderStringObjectArrayLinkedHashMapOfStringString() {
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		hqlWhere += " and oStatus=? ";
		params.add("发送中");
		hqlWhere += " and oPid=?";
		params.add(5);
		
		List<Orders> listRS = orderDao.searchOrder(hqlWhere, params.toArray(), orderby );
		for (Orders orders : listRS) {
			System.out.println(orders);
		}
	}

	@Test
	public void testSearchCountBypSid() {
		int pSid = 8;
		Integer count = orderDao.searchCountBypSid(pSid);
		System.out.println(count);
	}

	@Test
	public void testAddOrders() {
		Orders order = new Orders();
		order.setoAddress("安徽省合肥市");
		order.setoEnddate("2017-10-30");
		order.setoPid(5);
		order.setoStartdate("2017-10-16");
		order.setoStatus("发送中");
		
		orderDao.add(order);
	}

}
