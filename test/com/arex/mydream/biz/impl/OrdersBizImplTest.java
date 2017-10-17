package com.arex.mydream.biz.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.OrdersDTO;
import com.arex.mydream.model.Orders;
import com.arex.mydream.service.OrdersBiz;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class OrdersBizImplTest {
	
	@Resource(name="ordersBizImpl")
	private OrdersBiz ordersBiz;

	@Test
	public void testSearchByStatus() {
		Integer uId = 10;
		String oStatus = "发送中";
		List<OrdersDTO> listOO = ordersBiz.searchByStatus(oStatus, uId);
		for (OrdersDTO ordersDTO : listOO) {
			System.out.println(ordersDTO);
		}
	}

	@Test
	public void testUpdateostatusByOid() {
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setoId(2);
		ordersDTO.setoStatus("不翼而飞");
		ordersBiz.updateostatusByOid(ordersDTO);
	}

	@Test
	public void testSearchByUid() {
		int uId = 10;
		List<OrdersDTO> listOO = ordersBiz.searchByUid(uId);
		for (OrdersDTO ordersDTO : listOO) {
			System.out.println(ordersDTO);
		}
	}

	@Test
	public void testSearchOrder() {
		int oPid = 4;
		OrdersDTO ordersDTO = ordersBiz.searchOrder(oPid );
		System.out.println(ordersDTO);
	}

	@Test
	public void testSearchOrders() {
		List<OrdersDTO> listOO = ordersBiz.searchOrders();
		for (OrdersDTO ordersDTO : listOO) {
			System.out.println(ordersDTO);
		}
	}

	@Test
	public void testAddOrders() {
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setoAddress("American");
		ordersDTO.setoEnddate("2017-10-26");
		ordersDTO.setoPid(1);
		ordersDTO.setoStartdate("2017-10-17");
		ordersDTO.setoStatus("unknown");
		ordersDTO.setpSid(8);
		
		ordersBiz.addOrders(ordersDTO);
	}

	@Test
	public void testUpdateOrders() {
		OrdersDTO ordersDTO = ordersBiz.searchById(3);
		ordersDTO.setoStatus("unknown");
		ordersBiz.updateOrders(ordersDTO);
	}

	@Test
	public void testDeleteOrders() {
		ordersBiz.deleteOrders(10);
	}

	@Test
	public void testSearchOrdersByOId() {
		OrdersDTO ordersDTO = ordersBiz.searchOrdersByOId(1);
		System.out.println(ordersDTO);
	}

	@Test
	public void testSearchPage_Orders() {
		int pageNo = 1;
		int pageSize = 5;
		List<OrdersDTO> listOO = ordersBiz.searchPage_Orders(pageNo, pageSize);
		for (OrdersDTO ordersDTO : listOO) {
			System.out.println(ordersDTO);
		}
	}

	@Test
	public void testSearchOrdersCount() {
		int count = ordersBiz.searchOrdersCount();
		System.out.println(count);
	}

	@Test
	public void testSearchByOid() {
		OrdersDTO ordersDTO = ordersBiz.searchOrdersByOId(1);
		System.out.println(ordersDTO);
	}

	@Test
	public void testSearchOrdersOrdersDTOIntegerInteger() {
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setoStatus("发送中");
		int pageNo = 2;
		int pageSize = 4;
		List<OrdersDTO> listOO = ordersBiz.searchOrders(ordersDTO, pageNo, pageSize);
		for (OrdersDTO ordersDTO2 : listOO) {
			System.out.println(ordersDTO2);
		}
	}

	@Test
	public void testSearchOrdersOrdersDTO() {
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setoStatus("发送中");
		List<OrdersDTO> listOO = ordersBiz.searchOrders(ordersDTO);
		for (OrdersDTO ordersDTO2 : listOO) {
			System.out.println(ordersDTO2);
		}
	}

	@Test
	public void testSearchById() {
		OrdersDTO ordersDTO = ordersBiz.searchById(1);
		System.out.println(ordersDTO);
	}

	@Test
	public void testSearchCountBypSidOrdersDTO() {
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setpSid(8);
		Integer counts = ordersBiz.searchCountBypSid(ordersDTO );
		System.out.println(counts);
	}

	@Test
	public void testSearchCountBypSidInt() {
		Integer counts = ordersBiz.searchCountBypSid(8 );
		System.out.println(counts);
	}

	@Test
	public void testAdd() {
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setoAddress("American");
		ordersDTO.setoEnddate("2017-10-26");
		ordersDTO.setoPid(1);
		ordersDTO.setoStartdate("2017-10-17");
		ordersDTO.setoStatus("unknown");
		ordersDTO.setpSid(8);
		
		ordersBiz.add(ordersDTO);
	}

}
