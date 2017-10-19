package com.arex.mydream.action;

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

import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.action.vo.UserInfo;
import com.arex.mydream.comm.Page;
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
public class VipAction implements ModelDriven<UserInfo>, ServletRequestAware,
		ServletResponseAware {

	private UserInfo model = new UserInfo();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;

	Integer pagesize = 5;

	@Resource(name = "goodsBizImpl")
	private GoodsBiz goodsBiz;
	@Resource(name = "userService")
	private UserService userBiz;
	@Resource(name = "purchaseBizImpl")
	private PurchaseBiz purchaseBiz;
	@Resource(name = "ordersBizImpl")
	private OrdersBiz ordersBiz;
	@Resource(name = "saleBizImpl")
	private SaleBiz saleBiz;
	@Resource(name = "storeBizImpl")
	private StoreBiz storeBiz;

	public String searchVip() {

		// 获取登录的用户
		User searchUser = (User) session.getAttribute("user");
		if (searchUser == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(searchUser.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		Page page = new Page();
		User user = new User();
		user.setuFlag("1");
		String pageNoStr = request.getParameter("pageNo");

		// 如果uphone不为空的话，则设置为查询的一个条件
		// 如果uphone为空的话，则不管
		String uphone = model.getuPhone();
		if (uphone != null && !"".equals(uphone)) {
			uphone = "%" + uphone + "%";
			user.setuPhone(uphone);
		}

		// 设置pageNo
		// System.out.println(pageNoStr);
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

		// 设置分页情况
		Integer count = userBiz.searchCount(user);// 记录总数
		// Integer pagesize=Integer.valueOf(request.getParameter("pagesize"));
		// System.out.println("页面上"+pagesize);
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

		List<User> listvip = userBiz.searchUser(user, page.getPageNo(),
				page.getPageSize());// 会员
		request.setAttribute("listvip", listvip);
		request.setAttribute("page", page);

		return "huiyuanguanli";
	}

	public String searchUpdateVip() {

		if (model==null || model.getuId()==0) {
			return this.searchVip();
		}
		User user = userBiz.searchUser(model.getuId());
		request.setAttribute("vip", user);

		return "updatehuiyuan";
	}
	
	public String updateVip() {
		
		User user = userBiz.searchUser(model.getuId());
		if (model.getuName()!=null && !"".equals(model.getuName())) {
			user.setuName(model.getuName());
		}
		if (model.getSex()!=null && !"".equals(model.getSex())) {
			user.setSex(model.getSex());
		}
		if (model.getuMail()!=null && !"".equals(model.getuMail())) {
			user.setuMail(model.getuMail());
		}
		if (model.getuAddress()!=null && !"".equals(model.getuAddress())) {
			user.setuAddress(model.getuAddress());
		}
		if (model.getuLevel()!=null && !"".equals(model.getuLevel())) {
			user.setuLevel(model.getuLevel());
		}
	
		userBiz.updateUser(user);
		
		return this.searchVip();
	}

	public String delVip() {

		if (model==null || model.getuId()==0) {
			return this.searchVip();
		}
		
		User vip = userBiz.searchUser(model.getuId());
		vip.setuFlag("0");
		userBiz.updateUser(vip);
		
		return this.searchVip();
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
	public UserInfo getModel() {
		return model;
	}
}
