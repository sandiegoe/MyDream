package com.arex.mydream.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.arex.mydream.action.vo.GowuDTO;
import com.arex.mydream.action.vo.GowuItem;
import com.arex.mydream.action.vo.OrdersDTO;
import com.arex.mydream.action.vo.PurchaseDTO;
import com.arex.mydream.action.vo.SaleDTO;
import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.model.User;
import com.arex.mydream.service.GoodsBiz;
import com.arex.mydream.service.OrdersBiz;
import com.arex.mydream.service.PurchaseBiz;
import com.arex.mydream.service.SaleBiz;
import com.arex.mydream.service.StoreBiz;
import com.arex.mydream.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value = "prototype")
public class GowuAction implements ModelDriven<GowuDTO>, ServletRequestAware,
		ServletResponseAware {

	private GowuDTO model = new GowuDTO();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;

	@Resource(name = "goodsBizImpl")
	private GoodsBiz goodsBiz;
	@Resource(name = "userService")
	private UserService userBiz;
	@Resource(name="purchaseBizImpl")
	private PurchaseBiz purchaseBiz;
	@Resource(name="ordersBizImpl")
	private OrdersBiz ordersBiz;
	@Resource(name="saleBizImpl")
	private SaleBiz saleBiz;
	@Resource(name="storeBizImpl")
	private StoreBiz storeBiz;

	
	public String pNumAjax() {
		String numStr = model.getpNum();
		int gId = model.getgId();
		if (numStr==null || "".equals(numStr)) {
			return null;
		}
		int num = Integer.valueOf(numStr);
		
		User user = (User) request.getSession().getAttribute("user");
		Map<Integer, List<GowuItem>> gwc = (Map<Integer, List<GowuItem>>) request
				.getSession().getAttribute("gwc");
		List<GowuItem> list = gwc.get(user.getuId());
		
		for (GowuItem gowuItem : list) {
			if (gId == gowuItem.getgId()) {
				gowuItem.setpNum(num);
			}
		}
		
		return null;
		
	}
	
	public String jiesuanPage() {
		
		// 获取当前登录的用户
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getuPhone()==null || "".equals(user.getuPhone())) {
			return "toUserLoginPage";
		}
		User user2 = userBiz.searchUser(user.getuPhone());
		session.setAttribute("user", user2);
		
//		model.getpNum();
		
		return "jiesuanPage";
	}
	
	public String jiesuan() {

		String date = request.getParameter("date");
		User user = (User) request.getSession().getAttribute("user");
		if (user==null || user.getuPhone()==null || "".equals(user.getuPhone())) {
			return "toUserLoginPage";
		}
		// 获取当前登录的用户
		User user1 = userBiz.searchUser(user.getuPhone());
		// 获取当前用户的购物车
		Map<Integer, List<GowuItem>> gwc = (Map<Integer, List<GowuItem>>) request
				.getSession().getAttribute("gwc");
		List<GowuItem> list = gwc.get(user.getuId());
		
		//OrderBiz ob = new OrdersBizImpl();
		//PurchaseBiz pb = new PurchaseBizImpl();

		// 对于购物车中的每一个商品都会生成一条购买和一个订单
		for (GowuItem gowuItem : list) {// psid,pgid,puid,pnum

			PurchaseDTO purchaseDTO = new PurchaseDTO();
			//TODO    添加店家Sid
			purchaseDTO.setpSid(8);
			purchaseDTO.setpGid(gowuItem.getgId());
			purchaseDTO.setpUid(user.getuId());
			purchaseDTO.setpNum(gowuItem.getpNum());
			// 添加一条购买记录
			purchaseBiz.add(purchaseDTO);
			
			//查询刚刚添加的购买记录
			PurchaseDTO tempPur = purchaseBiz.searchPurchaseDTO(purchaseDTO.getpUid(), purchaseDTO.getpSid(), purchaseDTO.getpGid());
			
			OrdersDTO ordersDTO = new OrdersDTO();
			// oPid,oStartdate,oAddress
			// System.out.println(user1.getuAddress());
			ordersDTO.setoAddress(user1.getuAddress());
			ordersDTO.setoStartdate(date);
			ordersDTO.setoPid(tempPur.getpId());
			ordersDTO.setoStatus("未完成");
			// 添加一个订单
			ordersBiz.add(ordersDTO);
			
			//更新销售
			StoreDTO storeDTO = storeBiz.searchStore(tempPur.getpSid());
			SaleDTO saleDTO = saleBiz.searchSaleBysName(storeDTO.getsName());
			saleDTO.setSaNum(saleDTO.getSaNum() + 1);
			saleDTO.setSaPrice(saleDTO.getSaPrice() + gowuItem.getgPrice()*gowuItem.getpNum());
			saleBiz.updateSale(saleDTO);
			

			//查询刚刚添加的订单
			OrdersDTO o = ordersBiz.searchOrder(tempPur.getpId());
			request.getSession().setAttribute("oid", o.getoId());
		}

		request.setAttribute("list", list);
		request.getSession().removeAttribute("gwc");

		return "successed";
	}

	public String gouwuche() {

		// 获取当前登录的用户
		User user = (User) request.getSession().getAttribute("user");

		if (model == null || model.getgId() == 0 || model.getAdd() == 0) {
			return "toUserIndex";
		}

		if (user != null) {// 用户不为空
			int gId = model.getgId();// 商品的ID
			int add = model.getAdd();// 购买的数量
			GoodsDTO goodsDTO = goodsBiz.searchGoods(gId);// 拿到这个商品
			User tempUser = userBiz.searchUser(user.getuPhone());// 拿到这个用户的所有信息;
			// 获取购物车
			Map<Integer, List<GowuItem>> gouwu = (Map<Integer, List<GowuItem>>) request
					.getSession().getAttribute("gwc");

			if (gouwu == null) {// 第一次进入
				Map<Integer, List<GowuItem>> gwc = new HashMap<Integer, List<GowuItem>>();// 创建一个购物车
				List<GowuItem> listGowuItem = new ArrayList<GowuItem>();// 购物车中的东西
				GowuItem gowuItem = new GowuItem();
				gowuItem.setgId(gId);
				gowuItem.setgName(goodsDTO.getgName());// 购买商品
				gowuItem.setgPrice(goodsDTO.getgPrice());// 商品的单价
				gowuItem.setpNum(add);// 购买数量
				gowuItem.setgPic(goodsDTO.getgPic());// 图片
				// System.out.println(ougp.toString());
				// ougp.setoAddress(tempUser.getuAddress());//订单地址
				// ougp.setoStartdate("2016.3.1");//订单日期
				listGowuItem.add(gowuItem);
				gwc.put(tempUser.getuId(), listGowuItem);
				request.getSession().setAttribute("gwc", gwc);// 将信息放入购物车中

				return "gouwuche";
			} else {// 第二次进入
				List<GowuItem> listGowuItem = gouwu.get(user.getuId()); // 获取当前用户的购物车的List
				Boolean newGowuItem = true;// 没有当前要购买的商品
				for (GowuItem guwoItem : listGowuItem) {
					if (gId == guwoItem.getgId()) {// 判断购物车list中有此商品
						newGowuItem = false;
						int a = guwoItem.getpNum() + add;
						guwoItem.setpNum(a);// 只改数量
					}
				}
				if (newGowuItem) {// 没有此商品 是一个新的购物项
					GowuItem gowuItem = new GowuItem();
					gowuItem.setgId(gId);
					gowuItem.setgName(goodsDTO.getgName());
					gowuItem.setpNum(add);
					gowuItem.setgPrice(goodsDTO.getgPrice());
					gowuItem.setgPic(goodsDTO.getgPic());
					listGowuItem.add(gowuItem);
					return "gouwuche";
				} else {// 有此商品
					return "gouwuche";
				}
			}
		} else {// 用户为空
			return "toUserLoginPage";
		}
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
	public GowuDTO getModel() {
		return model;
	}
}
