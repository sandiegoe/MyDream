package com.arex.mydream.action;

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
import com.arex.mydream.model.User;
import com.arex.mydream.service.StoreBiz;
import com.arex.mydream.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value = "prototype")
public class StoreAction implements ModelDriven<StoreDTO>, ServletRequestAware,
		ServletResponseAware {

	private StoreDTO model = new StoreDTO();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;

	Integer pagesize = 5;

	@Resource(name = "storeBizImpl")
	private StoreBiz storeBiz;
	@Resource(name = "userService")
	private UserService userBiz;

	@Override
	public StoreDTO getModel() {
		return model;
	}

	public String myStore() {
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toUserPage";
		}
		StoreDTO storeDTO = storeBiz.searchStoreBySuid(user.getuId());
		session.setAttribute("store", storeDTO);
		
		return "toAdminIndex";
	}
	
	public String addStore() {
		String sPhone = request.getParameter("sPhone");
		String sAddress = request.getParameter("sAddress");
		String sName = request.getParameter("sName");
		// 设置当前登录用户的角色为2 店家
		User user = (User) request.getSession().getAttribute("user");
		User user1 = userBiz.searchUser(user.getuPhone());

		//
		// TODO
		UserInfo userInfo = new UserInfo();
		userInfo.setSex(user.getSex());
		userInfo.setuAddress(user.getuAddress());
		userInfo.setuBir(user.getuBir());
		userInfo.setuFlag(user.getuFlag());
		userInfo.setuId(user.getuId());
		userInfo.setuLevel(user.getuLevel());
		userInfo.setuMail(user.getuMail());
		userInfo.setuName(user.getuName());
		userInfo.setuPhone(user.getuPhone());
		userInfo.setuPwd(user.getuPwd());
		// /
		userInfo.setuFlag("2");
		userBiz.updateUser(userInfo);
		// 添加店面记录
		model.setSuid(user1.getuId());
		storeBiz.add(model);
		// request.setAttribute("store", store);
		// 将店面设置到store中
		request.getSession().setAttribute("store", model);
		return "toAdminIndex";
	}

	public String searchStore() {
		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());

		StoreDTO storeDTO = storeBiz.searchStoreBySuid(user1.getuId());
		if (storeDTO == null) {
			// 没有注册为店家
			return "toAdminLoginPage";
		}

		session.setAttribute("user", user1);
		session.setAttribute("store", storeDTO);

		return "dianmianxinxi";
	}

	public String updateStore() {

		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());

		StoreDTO storeDTO = storeBiz.searchStoreBySuid(user1.getuId());
		if (storeDTO == null) {
			// 没有注册为店家
			return "toAdminLoginPage";
		}

		if (model.getsAddress() != null && !"".equals(model.getsAddress())) {
			storeDTO.setsAddress(model.getsAddress());
		}
		if (model.getsPhone() != null && !"".equals(model.getsPhone())) {
			storeDTO.setsPhone(model.getsPhone());
		}
		storeBiz.update(storeDTO);

		session.setAttribute("store", storeDTO);

		return "dianmianxinxi";
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
}
