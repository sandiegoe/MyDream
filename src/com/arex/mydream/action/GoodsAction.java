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

import com.arex.mydream.action.vo.GoodsDTO;
import com.arex.mydream.action.vo.RepertoryDTO;
import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.model.User;
import com.arex.mydream.service.GoodsBiz;
import com.arex.mydream.service.RepertoryBiz;
import com.arex.mydream.service.StoreBiz;
import com.arex.mydream.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value = "prototype")
public class GoodsAction implements ModelDriven<GoodsDTO>, ServletRequestAware, ServletResponseAware {

	private GoodsDTO model = new GoodsDTO();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;

	Integer pagesize = 5;

	@Resource(name = "goodsBizImpl")
	private GoodsBiz goodsBiz;
	@Resource(name = "userService")
	private UserService userBiz;
	@Resource(name = "storeBizImpl")
	private StoreBiz storeBiz;
	@Resource(name = "repertoryBizImpl")
	private RepertoryBiz repertoryBiz;

	@Override
	public GoodsDTO getModel() {
		return model;
	}

	public String searchupdateGoods() {

		if (model == null || model.getgId() == 0) {
			return "toSearchGoods";
		}
		GoodsDTO goodsDTO = goodsBiz.searchGoods(model.getgId());
		request.setAttribute("goods", goodsDTO);
		return "goodsxiugai";
	}

	public String updateGoods() {

		if (model == null) {
			return "toSearchGoods";
		}

		GoodsDTO goodsDTO = goodsBiz.searchGoods(model.getgId());
		if (goodsDTO != null) {
			if (model.getgAddress() != null && !"".equals(model.getgAddress())) {
				goodsDTO.setgAddress(model.getgAddress());
			}
			if (model.getgDescribe() != null && !"".equals(model.getgDescribe())) {
				goodsDTO.setgDescribe(model.getgDescribe());
			}
			if (model.getgName() != null && !"".equals(model.getgName())) {
				goodsDTO.setgName(model.getgName());
			}
			if (model.getgType() != null && !"".equals(model.getgType())) {
				goodsDTO.setgType(model.getgType());
			}
			if (model.getgPrice() != null) {
				goodsDTO.setgPrice(model.getgPrice());
			}
			goodsBiz.updateGoods(goodsDTO);
		}

		return "toSearchGoods";
	}

	public String deleteGoods() {
		if (model == null || model.getgId() == 0) {
			return "toSearchGoods";
		}

		goodsBiz.deleteGoods(model.getgId());

		return "toSearchGoods";
	}

	public String searchnameGoods() {
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

		if (model == null || model.getgName() == null || "".equals(model.getgName())) {
			return "toSearchGoods";
		}

		List<GoodsDTO> listGood = goodsBiz.searchByGname(model.getgName());
		request.setAttribute("listGood", listGood);
		return "goodsone";
	}

	/**
	 * 查找指定店面的所有产品, 提供select列表显示
	 * 
	 * @return
	 */
	public String searchAllGoods() {

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

		List<GoodsDTO> listGood = goodsBiz.searchGoodBysId(storeDTO.getsId());
		// List<GoodsDTO> listGood = goodsBiz.searchGoods();
		request.setAttribute("listGood", listGood);

		return "searchAllGoods";
	}

	public String searchAllGoods2() {
		
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
		
		List<GoodsDTO> listGoods = goodsBiz.searchGoodBysId(storeDTO.getsId());
		// List<GoodsDTO> listGood = goodsBiz.searchGoods();
		request.setAttribute("listGoods", listGoods);
		
		this.searchAllGoods();
		
		return "searchGoods";
	}

	/**
	 * 查找指定店面的所有产品，分页查询
	 * 
	 * @return
	 */
	public String searchGoods() {

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

		Page page = new Page();
		String pageNoStr = request.getParameter("pageNo");
		Integer pageNo = 0;

		// 设置店面ID
		// 获取的是店面店主的uid
		// if (sids!=null&&!"".equals(sids)) {
		// sid=Integer.valueOf(sids);
		// regost.setsId(sid);
		// }
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
		// Integer count = goodsBiz.searchGoodsCount();// 记录总数
		// RepertoryDTO repertoryDTO = new RepertoryDTO();
		// repertoryDTO.setrSid(storeDTO.getsId());
		// Integer count = repertoryBiz.searchRepertoryCount(repertoryDTO);
		// goodsBiz.searchGoodsCountBySid(storeDTO.getsId());
		Integer count = goodsBiz.searchGoodsCountBySid(storeDTO.getsId());

		if (count % pagesize != 0) {
			count = count / pagesize + 1;
		} else {
			count = count / pagesize;
			if (count == 0) {
				count = 1;
			}
		}
		page.setPageSize(pagesize);// 当前页面显示数量
		page.setPageCount(count); // 设置总共的页面数量

		// 查找当前用户的所有库存
		// List<RepertoryDTO> listRO = repertoryBiz.searchPage_Repertory(repertoryDTO,
		// page.getPageNo(), page.getPageSize());
		// goodsBiz.searchGoodBysId(storeDTO.getsId(), page.getPageNo(),
		// page.getPageSize());
		// List<GoodsDTO> listGoods = goodsBiz.searchPage_Goods(page.getPageNo(),
		// page.getPageSize());
		List<GoodsDTO> listGoods = goodsBiz.searchGoodBysId(storeDTO.getsId(), page.getPageNo(), page.getPageSize());

		request.setAttribute("page", page);
		request.setAttribute("listGoods", listGoods);

		// 查询所有产品
		this.searchAllGoods();

		return "searchGoods";
	}

	public String addPage() {
		return "addPage";
	}

	public String add() {

		GoodsDTO goodsDTO = new GoodsDTO();

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

		String gname = request.getParameter("gname");
		String gprice1 = request.getParameter("gprice");
		double gprice = 0;
		if (gprice1 == null || "".equals(gprice1)) {
			gprice = 0;
		} else {
			gprice = Double.parseDouble(request.getParameter("gprice"));
		}
		String gtype = request.getParameter("gtype");
		String gaddress = request.getParameter("gaddress");
		String gpic = request.getParameter("gpic");
		String gdescribe = request.getParameter("gdescribe");
		if (gname == null || "".equals(gname) || gprice == 0 || "".equals(gprice) || gtype == null || "".equals(gtype)
				|| gaddress == null || "".equals(gaddress) || gpic == null || "".equals(gpic) || gdescribe == null
				|| "".equals(gdescribe)) {
			return "addPage";
		} else {
			goodsDTO.setgName(gname);
			goodsDTO.setgPrice(gprice);
			goodsDTO.setgType(gtype);
			goodsDTO.setgAddress(gaddress);
			goodsDTO.setgPic(gpic);
			goodsDTO.setgDescribe(gdescribe);
			// 添加商品记录
			goodsBiz.addGoods(goodsDTO);
		}

		GoodsDTO searchGoodsDTO = goodsBiz.searchByGname(gname).get(0);
		String rnums = request.getParameter("rnum");
		Integer rnum = 0;
		if (rnums != null && !"".equals(rnums)) {
			rnum = Integer.valueOf(rnums);
			RepertoryDTO repetoryDTO = new RepertoryDTO();
			repetoryDTO.setrGid(searchGoodsDTO.getgId());
			repetoryDTO.setrNum(rnum);
			repetoryDTO.setrSid(storeDTO.getsId());
			// 添加库存记录
			repertoryBiz.addRepertory(repetoryDTO);
			return "toSearchGoods";
		} else {
			return "addPage";
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
}
