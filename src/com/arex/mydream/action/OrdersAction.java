package com.arex.mydream.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.GoodsDTO;
import com.arex.mydream.action.vo.OrdersDTO;
import com.arex.mydream.action.vo.PurchaseDTO;
import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.model.User;
import com.arex.mydream.service.GoodsBiz;
import com.arex.mydream.service.OrdersBiz;
import com.arex.mydream.service.PurchaseBiz;
import com.arex.mydream.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value = "prototype")
public class OrdersAction implements ModelDriven<OrdersDTO>,
		ServletRequestAware, ServletResponseAware {

	private OrdersDTO model = new OrdersDTO();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;
	Integer pagesize = 5;

	@Resource(name = "userService")
	private UserService userBiz;
	@Resource(name = "ordersBizImpl")
	private OrdersBiz ordersBiz;
	@Resource(name = "purchaseBizImpl")
	private PurchaseBiz purchaseBiz;
	@Resource(name = "goodsBizImpl")
	private GoodsBiz goodsBiz;

	public String searchOrdersByOid() {

		User user = (User) session.getAttribute("user");
		if (user == null || user.getuPhone() == null
				|| "".equals(user.getuPhone())) {
			return "toUserLoginPage";
		}
		User user2 = userBiz.searchUser(user.getuPhone());

		if (model == null || model.getoId() == 0) {
			return "toUserIndex";
		}

		OrdersDTO ordersDTO = ordersBiz.searchByOid(model.getoId());
		List<OrdersDTO> listOO = new ArrayList<OrdersDTO>();
		listOO.add(ordersDTO);
		for (OrdersDTO temp : listOO) {
			int oPid = temp.getoPid();
			PurchaseDTO purchaseDTO = purchaseBiz.search(oPid);
			temp.setpNum(purchaseDTO.getpNum());
			temp.setpSid(purchaseDTO.getpSid());
			GoodsDTO goodsDTO = goodsBiz.searchGoods(purchaseDTO.getpGid());
			temp.setgName(goodsDTO.getgName());
			temp.setgPic(goodsDTO.getgPic());
			temp.setgPrice(goodsDTO.getgPrice());
		}
		request.setAttribute("listOugp", listOO);
		session.setAttribute("user", user2);

		return "wodedingdan";
	}

	//根据用户id查询用户的订单
	public String searchOrders2() {
		User user = (User) session.getAttribute("user");
		if (user == null || user.getuPhone() == null
				|| "".equals(user.getuPhone())) {
			return "toUserLoginPage";
		}
		User user2 = userBiz.searchUser(user.getuPhone());

		List<OrdersDTO> listOO = ordersBiz.searchByUid(user2.getuId());
		for (OrdersDTO temp : listOO) {
			int oPid = temp.getoPid();
			PurchaseDTO purchaseDTO = purchaseBiz.search(oPid);
			temp.setpNum(purchaseDTO.getpNum());
			temp.setpSid(purchaseDTO.getpSid());
			GoodsDTO goodsDTO = goodsBiz.searchGoods(purchaseDTO.getpGid());
			temp.setgName(goodsDTO.getgName());
			temp.setgPic(goodsDTO.getgPic());
			temp.setgPrice(goodsDTO.getgPrice());
		}

		request.setAttribute("listOugp", listOO);
		session.setAttribute("user", user2);

		return "wodedingdan";
	}

	public String searchOrders2ByStatus() {

		User user = (User) session.getAttribute("user");
		if (user == null || user.getuPhone() == null
				|| "".equals(user.getuPhone())) {
			return "toUserLoginPage";
		}
		User user2 = userBiz.searchUser(user.getuPhone());
		if (model == null || model.getoStatus() == null
				|| "".equals(model.getoStatus())) {
			return "toUserIndex";
		}

		List<OrdersDTO> listOO = ordersBiz.searchByStatus(model.getoStatus(),
				user2.getuId());
		for (OrdersDTO temp : listOO) {
			int oPid = temp.getoPid();
			PurchaseDTO purchaseDTO = purchaseBiz.search(oPid);
			temp.setpNum(purchaseDTO.getpNum());
			temp.setpSid(purchaseDTO.getpSid());
			GoodsDTO goodsDTO = goodsBiz.searchGoods(purchaseDTO.getpGid());
			temp.setgName(goodsDTO.getgName());
			temp.setgPic(goodsDTO.getgPic());
			temp.setgPrice(goodsDTO.getgPrice());
		}

		request.setAttribute("listOugp", listOO);

		return "wodedingdan";
	}

	public String searchupdateOrders2() {

		User user = (User) session.getAttribute("user");
		if (user == null || user.getuPhone() == null
				|| "".equals(user.getuPhone())) {
			return "toUserLoginPage";
		}
		User user2 = userBiz.searchUser(user.getuPhone());
		if (model == null || model.getoStatus() == null
				|| "".equals(model.getoStatus()) || model.getoId() == 0) {
			return "toUserIndex";
		}

		// 获取指定oid的订单
		OrdersDTO ordersDTO = ordersBiz.searchByOid(model.getoId());
		ordersDTO.setoStatus(model.getoStatus());
		ordersBiz.updateostatusByOid(ordersDTO);

		return this.searchOrders2();
	}

	// 后台管理页面
	public String searchOrdersBySid() {

		User user = (User) session.getAttribute("user");
		if (user == null || user.getuPhone() == null
				|| "".equals(user.getuPhone())) {
			return "toAdminLoginPage";
		}
		User user2 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setpSid(storeDTO.getsId());

		Page page = new Page();
		String pageNoStr = request.getParameter("pageNo");
		Integer pageNo = 0;
		if (pageNoStr != null && !"".equals(pageNoStr)) {
			pageNo = Integer.valueOf(pageNoStr);
			if (pageNo > 0) {
				page.setPageNo(pageNo);
			} else {
				page.setPageNo(1);
			}
		} else {
			page.setPageNo(1);
		}
		Integer count = ordersBiz.searchCountBypSid(ordersDTO.getpSid());// 记录总数
		if (count % pagesize != 0) {
			count = count / pagesize + 1;
		} else {
			count = count / pagesize;
			if (count == 0) {
				count = 1;
			}
		}
		page.setPageSize(pagesize);// 当前页面显示数量
		page.setPageCount(count);

		
		// 查询当前店家的所有订单
		List<OrdersDTO> listOO = ordersBiz
				.searchOrders(ordersDTO, page.getPageNo(), page.getPageSize());
		for (OrdersDTO temp : listOO) {
			int oPid = temp.getoPid();
			PurchaseDTO purchaseDTO = purchaseBiz.search(oPid);
			temp.setpNum(purchaseDTO.getpNum());
			temp.setpSid(purchaseDTO.getpSid());
			GoodsDTO goodsDTO = goodsBiz.searchGoods(purchaseDTO.getpGid());
			temp.setgName(goodsDTO.getgName());
			temp.setgPic(goodsDTO.getgPic());
			temp.setgPrice(goodsDTO.getgPrice());
			User buyUser = userBiz.searchUser(purchaseDTO.getpUid());
			temp.setuName(buyUser.getuName());
		}

		request.setAttribute("page", page);
		request.setAttribute("listOugp", listOO);

		return "dingdanguanli";
	}

	public String addOrdersPage() {
		return "addOrdersPage";
	}

	public String addOrders() {

		User user = (User) session.getAttribute("user");
		if (user == null || user.getuPhone() == null
				|| "".equals(user.getuPhone())) {
			return "toAdminLoginPage";
		}
		User user2 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		String ostartdate = model.getoStartdate();
		String ostatus = model.getoStatus();
		String oenddate = model.getoEnddate();
		String uname = model.getuName();
		String gname = model.getgName();
		Integer pnum = model.getpNum();
		String oaddress = model.getoAddress();
		double gprice = model.getgPrice();

		if (model == null || ostartdate == null || "".equals(ostartdate)
				|| ostatus == null || "".equals(ostatus) || oenddate == null
				|| "".equals(oenddate) || uname == null || "".equals(uname)
				|| gname == null || "".equals(gname) || pnum == 0
				|| "".equals(pnum) || gprice == 0 || "".equals(gprice)
				|| oaddress == null || "".equals(oaddress)) {
			return "addOrdersPage";
		} else {
			GoodsDTO searchGoodsDTO = null;
			User searchUser = userBiz.searchUserByUname(uname);// 拿用户的id
			List<GoodsDTO> listGO = goodsBiz.searchByGname(gname);

			if (searchUser == null || listGO == null || listGO.size() == 0) {
				return "addOrdersPage";
			} else {
				searchGoodsDTO = listGO.get(0);
				// user.setuName(uname);
				// goods.setgName(gname);
				PurchaseDTO purchaseDTO = new PurchaseDTO();
				purchaseDTO.setpGid(searchGoodsDTO.getgId());
				purchaseDTO.setpNum(pnum);
				purchaseDTO.setpSid(storeDTO.getsId());
				purchaseDTO.setpUid(searchUser.getuId());
				purchaseBiz.add(purchaseDTO);
				PurchaseDTO tempPurchase = purchaseBiz.searchPurchaseDTO(
						searchUser.getuId(), storeDTO.getsId(),
						searchGoodsDTO.getgId());

				OrdersDTO ordersDTO = new OrdersDTO();
				ordersDTO.setoStartdate(ostartdate);
				ordersDTO.setoStatus(ostatus);
				ordersDTO.setoEnddate(oenddate);
				ordersDTO.setoPid(tempPurchase.getpId());
				ordersDTO.setoAddress(oaddress);
				ordersBiz.add(ordersDTO);
				
				return this.searchOrdersBySid();
			}

		}
	}

	//根据查询的订单号来修改订单（后台）
	public String updateOrdersPage() {
		
		if (model==null || model.getoId()==0) {
			return this.searchOrdersBySid();
		}
		
		OrdersDTO ordersDTO = ordersBiz.searchByOid(model.getoId());
		
		int oPid = ordersDTO.getoPid();
		PurchaseDTO purchaseDTO = purchaseBiz.search(oPid);
		ordersDTO.setpNum(purchaseDTO.getpNum());
		ordersDTO.setpSid(purchaseDTO.getpSid());
		GoodsDTO goodsDTO = goodsBiz.searchGoods(purchaseDTO.getpGid());
		ordersDTO.setgName(goodsDTO.getgName());
		ordersDTO.setgPic(goodsDTO.getgPic());
		ordersDTO.setgPrice(goodsDTO.getgPrice());
		
		request.setAttribute("ougp", ordersDTO);
		
		return "updateOrdersPage";
	}
	
	public String updateOrders() {
		
		if (model==null || model.getoId()==0) {
			return this.searchOrdersBySid();
		}
		
		OrdersDTO ordersDTO = ordersBiz.searchByOid(model.getoId());
		
		String ostartdate = model.getoStartdate();
		String ostatus = model.getoStatus();
		String oenddate = model.getoEnddate();
		String uname = model.getuName();
		String gname = model.getgName();
		Integer pnum = model.getpNum();
		// Double gprice=Double.valueOf(request.getParameter("gprice"));
		String oaddress = model.getoAddress();
		
		ordersDTO.setoStartdate(ostartdate);
		ordersDTO.setoStatus(ostatus);
		ordersDTO.setoEnddate(oenddate);
		ordersDTO.setoAddress(oaddress);
		
		ordersBiz.updateOrders(ordersDTO);
		
		return this.searchOrdersBySid();
	}
	
	public String deleteOrders() {
		
		if (model==null || model.getoId()==0) {
			return this.searchOrdersBySid();
		}
		
		ordersBiz.deleteOrders(model.getoId());
		
		return this.searchOrdersBySid();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public OrdersDTO getModel() {
		return model;
	}
}
