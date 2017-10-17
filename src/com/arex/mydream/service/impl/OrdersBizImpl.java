package com.arex.mydream.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.OrdersDTO;
import com.arex.mydream.dao.OrdersDao;
import com.arex.mydream.model.Orders;
import com.arex.mydream.service.OrdersBiz;

@Component
@Transactional
public class OrdersBizImpl implements OrdersBiz {
	
	@Resource(name="ordersDaoImpl")
	private OrdersDao ordersDao;

	@Override
	public List<OrdersDTO> searchByStatus(String oStatus, Integer uId) {
		List<Orders> listOS = ordersDao.searchByStatus(oStatus, uId);
		List<OrdersDTO> listOO = this.convertPO2VOList(listOS);
		return listOO;
	}

	private List<OrdersDTO> convertPO2VOList(List<Orders> listOS) {
		List<OrdersDTO> listOO = new ArrayList<OrdersDTO>();
		OrdersDTO ordersDTO = null;
		Orders orders = null;
		for (int i=0; listOS!=null && i<listOS.size(); ++i) {
			ordersDTO = new OrdersDTO();
			orders = listOS.get(i);
			ordersDTO.setoAddress(orders.getoAddress());
			ordersDTO.setoEnddate(orders.getoEnddate());
			ordersDTO.setoId(orders.getoId());
			ordersDTO.setoPid(orders.getoPid());
			ordersDTO.setoStartdate(orders.getoStartdate());
			ordersDTO.setoStatus(orders.getoStatus());
			listOO.add(ordersDTO);
		}
		return listOO;
	}

	@Override
	public void updateostatusByOid(OrdersDTO ordersDTO) {
		Orders orders = this.convertVO2PO(ordersDTO);
		ordersDao.updateostatusByOid(orders);
	}

	private Orders convertVO2PO(OrdersDTO ordersDTO) {
		Orders orders = null;
		if (ordersDTO != null) {
			orders = new Orders();
			orders.setoAddress(ordersDTO.getoAddress());
			orders.setoEnddate(ordersDTO.getoEnddate());
			orders.setoId(ordersDTO.getoId());
			orders.setoPid(ordersDTO.getoPid());
			orders.setoStartdate(ordersDTO.getoStartdate());
			orders.setoStatus(ordersDTO.getoStatus());
		}
		
		return orders;
	}

	@Override
	public List<OrdersDTO> searchByUid(Integer uId) {
		List<Orders> listOS = ordersDao.searchByUid(uId);
		List<OrdersDTO> listOO = this.convertPO2VOList(listOS);
		return listOO;
	}

	@Override
	public OrdersDTO searchOrder(int oPid) {
		Orders orders = ordersDao.searchOrder(oPid);
		OrdersDTO ordersDTO = this.convertPO2VO(orders);
		return ordersDTO;
	}
	
	private OrdersDTO convertPO2VO(Orders orders) {
		OrdersDTO ordersDTO  = null;
		if (orders != null) {
			ordersDTO = new OrdersDTO();
			ordersDTO.setoAddress(orders.getoAddress());
			ordersDTO.setoEnddate(orders.getoEnddate());
			ordersDTO.setoId(orders.getoId());
			ordersDTO.setoPid(orders.getoPid());
			ordersDTO.setoStartdate(orders.getoStartdate());
			ordersDTO.setoStatus(orders.getoStatus());
		}
		
		return ordersDTO;
	}

	@Override
	public List<OrdersDTO> searchOrders() {
		List<Orders> listOS = ordersDao.search();
		List<OrdersDTO> listOO = this.convertPO2VOList(listOS);
		return listOO;
	}

	@Override
	public void addOrders(OrdersDTO ordersDTO) {
		Orders orders = this.convertVO2PO(ordersDTO);
		ordersDao.add(orders);
	}

	@Override
	public void updateOrders(OrdersDTO ordersDTO) {
		Orders orders = this.convertVO2PO(ordersDTO);
		ordersDao.update(orders);
	}

	@Override
	public void deleteOrders(Integer oId) {
		ordersDao.delete(oId);
	}

	@Override
	public OrdersDTO searchOrdersByOId(Integer oId) {
		Orders orders = ordersDao.searchByOId(oId);
		OrdersDTO ordersDTO = this.convertPO2VO(orders);
		return ordersDTO;
	}

	@Override
	public List<OrdersDTO> searchPage_Orders(Integer pageNo, Integer pageSize) {
		List<Orders> listOS = ordersDao.searchPage_Orders(pageNo, pageSize);
		List<OrdersDTO> listOO = this.convertPO2VOList(listOS);
		return listOO;
	}

	@Override
	public Integer searchOrdersCount() {
		Integer count = ordersDao.searchOrdersCount();
		return count;
	}

	@Override
	public OrdersDTO searchByOid(Integer oId) {
		Orders orders = ordersDao.searchByOId(oId);
		OrdersDTO ordersDTO = this.convertPO2VO(orders);
		return ordersDTO;
	}

	@Override
	public List<OrdersDTO> searchOrders(OrdersDTO ordersDTO, Integer pageNo,
			Integer pageSize) {
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		List<OrdersDTO> listOO = null;
		
		if (ordersDTO == null) {
			return listOO;
		}
		if (ordersDTO.getoAddress()!=null && !"".equals(ordersDTO.getoAddress())) {
			hqlWhere += " and orders.oAddress=? ";
			params.add(ordersDTO.getoAddress());
		}
		if (ordersDTO.getoEnddate()!=null && !"".equals(ordersDTO.getoEnddate())) {
			hqlWhere += " and orders.oEnddate=? ";
			params.add(ordersDTO.getoEnddate());
		}
		if (ordersDTO.getoId() != 0) {
			hqlWhere += " and orders.oId=? ";
			params.add(ordersDTO.getoId());
		}
		if (ordersDTO.getoPid() != 0) {
			hqlWhere += " and orders.oPid=? ";
			params.add(ordersDTO.getoPid());
		}
		if (ordersDTO.getoStartdate()!=null && !"".equals(ordersDTO.getoStartdate())) {
			hqlWhere += " and orders.oStartdate=? ";
			params.add(ordersDTO.getoStartdate());
		}
		if (ordersDTO.getoStatus()!=null && !"".equals(ordersDTO.getoStatus())) {
			hqlWhere += " and orders.oStatus=? ";
			params.add(ordersDTO.getoStatus());
		}
		
		List<Orders> listOS = ordersDao.searchOrder(hqlWhere, params.toArray(), orderby , pageNo, pageSize);
		listOO = this.convertPO2VOList(listOS);
		
		return listOO;
	}

	/**
	 *    按照条件查询
	 */
	@Override
	public List<OrdersDTO> searchOrders(OrdersDTO ordersDTO) {
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		List<OrdersDTO> listOO = null;
		
		if (ordersDTO == null) {
			return listOO;
		}
		if (ordersDTO.getoAddress()!=null && !"".equals(ordersDTO.getoAddress())) {
			hqlWhere += " and orders.oAddress=? ";
			params.add(ordersDTO.getoAddress());
		}
		if (ordersDTO.getoEnddate()!=null && !"".equals(ordersDTO.getoEnddate())) {
			hqlWhere += " and orders.oEnddate=? ";
			params.add(ordersDTO.getoEnddate());
		}
		if (ordersDTO.getoId() != 0) {
			hqlWhere += " and orders.oId=? ";
			params.add(ordersDTO.getoId());
		}
		if (ordersDTO.getoPid() != 0) {
			hqlWhere += " and orders.oPid=? ";
			params.add(ordersDTO.getoPid());
		}
		if (ordersDTO.getoStartdate()!=null && !"".equals(ordersDTO.getoStartdate())) {
			hqlWhere += " and orders.oStartdate=? ";
			params.add(ordersDTO.getoStartdate());
		}
		if (ordersDTO.getoStatus()!=null && !"".equals(ordersDTO.getoStatus())) {
			hqlWhere += " and orders.oStatus=? ";
			params.add(ordersDTO.getoStatus());
		}
		
		List<Orders> listOS = ordersDao.searchOrder(hqlWhere, params.toArray(), orderby);
		listOO = this.convertPO2VOList(listOS);
		
		return listOO;
	}

	@Override
	public OrdersDTO searchById(Integer oId) {
		return this.searchByOid(oId);
	}

	@Override
	public Integer searchCountBypSid(OrdersDTO ordersDTO) {
		Integer counts = this.searchCountBypSid(ordersDTO.getpSid());
		return counts;
	}

	@Override
	public Integer searchCountBypSid(int pSid) {
		Integer counts = ordersDao.searchCountBypSid(pSid);
		return counts;
	}

	@Override
	public void add(OrdersDTO ordersDTO) {
		Orders orders = this.convertVO2PO(ordersDTO);
		ordersDao.add(orders);
	}

}
