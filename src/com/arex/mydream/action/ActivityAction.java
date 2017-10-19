package com.arex.mydream.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.ActivityDTO;
import com.arex.mydream.action.vo.GoodsDTO;
import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.model.User;
import com.arex.mydream.service.ActivityBiz;
import com.arex.mydream.service.GoodsBiz;
import com.arex.mydream.service.StoreBiz;
import com.arex.mydream.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author arex
 * @date 2017年10月18日
 * 
 */
@Component
@Scope(value = "prototype")
public class ActivityAction implements ModelDriven<ActivityDTO>,
		ServletRequestAware, ServletResponseAware {

	private ActivityDTO model = new ActivityDTO();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;
	Integer pagesize = 5;

	@Resource(name = "userService")
	private UserService userBiz;
	@Resource(name = "goodsBizImpl")
	private GoodsBiz goodsBiz;
	@Resource(name = "activityBizImpl")
	private ActivityBiz activityBiz;
	@Resource(name = "storeBizImpl")
	private StoreBiz storeBiz;

	@Override
	public ActivityDTO getModel() {
		return model;
	}

	public ActivityAction() {
	}

	private void searchAllActivityGname() {
		List<ActivityDTO> listAO = null;
		if (model.getaDate()!=null && !"".equals(model.getaDate())) {
			listAO = activityBiz.searchByadate(model.getaDate());
		} else {
			listAO = activityBiz.search();	
		}
		Set<String> goodsNameSet = new HashSet<String>(); 
		for (ActivityDTO activityDTO : listAO) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(activityDTO.getaGid());
			goodsNameSet.add(goodsDTO.getgName());
		}
		
		request.setAttribute("goodsNameSet", goodsNameSet);
	}
	
	/**
	 * 根据日期分页查询
	 * 
	 * @return
	 */
	public String searchActivity() {
		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		// 设置分页情况
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
		Integer count = activityBiz.searchCountByaDate(model.getaDate());// 记录总数
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
		request.setAttribute("page", page);

		List<ActivityDTO> listAO = activityBiz.searchPage(model.getaDate(),
				page.getPageNo(), page.getPageSize());
		for (ActivityDTO activityDTO : listAO) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(activityDTO.getaGid());
			activityDTO.setgName(goodsDTO.getgName());
			activityDTO.setgPrice(goodsDTO.getgPrice());
		}

		request.setAttribute("listActivity", listAO);
		this.searchAllActivityGname();

		return "1111";
	}

	public String searchActivityWithNoPage() {

		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		List<ActivityDTO> listAO = activityBiz.searchByadate(model.getaDate());
		for (ActivityDTO activityDTO : listAO) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(activityDTO.getaGid());
			activityDTO.setgName(goodsDTO.getgName());
			activityDTO.setgPrice(goodsDTO.getgPrice());
		}

		request.setAttribute("listActivity", listAO);
		request.setAttribute("model", model);

		return "1111";
	}

	public String searchOneActivity() {

		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		// activityBiz.sear
		if (model.getgName()!=null && !"".equals(model.getgName())) {
			List<GoodsDTO> listGO = goodsBiz.searchByGname(model.getgName());
			if (listGO!=null && listGO.size()>0) {
				List<ActivityDTO> listAO = activityBiz.searchByaGid(listGO.get(0).getgId());
				for (ActivityDTO activityDTO : listAO) {
					GoodsDTO goodsDTO = goodsBiz.searchGoods(activityDTO.getaGid());
					activityDTO.setgName(goodsDTO.getgName());
					activityDTO.setgPrice(goodsDTO.getgPrice());
				}
				request.setAttribute("listActivity", listAO);
			}
		}
	
		this.searchAllActivityGname();
		request.setAttribute("model", model);

		return "1111";
	}

	public String addActivityPage() {

		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		this.searchAllActivityGname();
		return "addActivityPage";
	}

	public String addActivity() {

		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		String agname = model.getgName();
		double aprice = model.getaPrice();
		String adate = model.getaDate();
		String apic = model.getaPic();
		if (agname == null || "".equals(agname) || aprice == 0
				|| "".equals(aprice) || adate == null || "".equals(adate)
				|| apic == null || "".equals(apic)) {
			return "addActivityPage";
		} else {

			List<GoodsDTO> listGO = goodsBiz.searchByGname(agname);
			if (listGO == null || listGO.size() == 0) {
				return "addActivityPage";
			}
			GoodsDTO goodsDTO = listGO.get(0);
			ActivityDTO activityDTO = new ActivityDTO();
			activityDTO.setaPrice(aprice);
			activityDTO.setaPic(apic);
			activityDTO.setaDate(adate);
			activityDTO.setaGid(goodsDTO.getgId());
			activityBiz.add(activityDTO);
			return this.searchActivity();
		}
	}

	public String deleteActivity() {

		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		if (model == null || model.getaId() == 0) {
			return "toAdminLoginPage";
		}

		activityBiz.delete(model.getaId());

		return this.searchActivity();
	}

	public String searchActivityByaId() {
		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}

		ActivityDTO activityDTO = activityBiz.searchById(model.getaId());

		if (activityDTO != null) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(activityDTO.getaGid());
			activityDTO.setgName(goodsDTO.getgName());
			activityDTO.setgPrice(goodsDTO.getgPrice());
		}

		request.setAttribute("acgoods", activityDTO);

		return "updateActivity";
	}

	public String updateActivity() {
		// 获取登录的用户
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "toAdminLoginPage";
		}
		User user1 = userBiz.searchUser(user.getuPhone());
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("store");
		if (storeDTO == null) {
			return "toAdminLoginPage";
		}
		
		ActivityDTO activityDTO = activityBiz.searchById(model.getaId());
		if (model!=null && model.getgName()!=null && !"".equals(model.getgName())) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(model.getaGid());
			goodsDTO.setgName(model.getgName());
			goodsBiz.updateGoods(goodsDTO);
		}
		if (model!=null && model.getaPrice()!=0) {
			activityDTO.setaPrice(model.getaPrice());
		}
		if (model!=null && model.getaDate()!=null && !"".equals(model.getaDate())) {
			activityDTO.setaDate(model.getaDate());
		}
		if (model!=null && model.getaPic()!=null && !"".equals(model.getaPic())) {
			activityDTO.setaPic(model.getaPic());
		}
	
		activityBiz.update(activityDTO);

		return this.searchActivity();
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
