package com.arex.mydream.web;

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

import com.arex.mydream.biz.GoodsBiz;
import com.arex.mydream.comm.Page;
import com.arex.mydream.entity.view.GoodsDTO;
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

	@Override
	public GoodsDTO getModel() {
		return model;
	}

	public String searchAllGoods() {
		List<GoodsDTO> listGood = goodsBiz.searchGoods();
		request.setAttribute("listGood", listGood);
		
		return "searchAllGoods";
	}
	
	public String searchGoods() {
		
	
		Page page = new Page();
		String pageNoStr = request.getParameter("pageNo");
		Integer pageNo = 0;
		
		//设置店面ID
		//获取的是店面店主的uid
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
		Integer count = goodsBiz.searchGoodsCount();// 记录总数

		if (count % pagesize != 0) {
			count = count / pagesize + 1;
		} else {
			count = count / pagesize;
			if (count == 0) {
				count = 1;
			}
		}
		page.setPageSize(pagesize);// 当前页面显示数量
		page.setPageCount(count); //设置总共的页面数量
		List<GoodsDTO> listGoods = goodsBiz.searchPage_Goods(page.getPageNo(), page.getPageSize());
		request.setAttribute("page", page);
		request.setAttribute("listGoods", listGoods);
		
		//查询所有产品
		this.searchAllGoods();
		
		return "searchGoods";
	}

	public String addPage() {
		return "addPage";
	}

	public String add() {


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
			GoodsDTO goodsDTO = new GoodsDTO();
			goodsDTO.setgName(gname);
			goodsDTO.setgPrice(gprice);
			goodsDTO.setgType(gtype);
			goodsDTO.setgAddress(gaddress);
			goodsDTO.setgPic(gpic);
			goodsDTO.setgDescribe(gdescribe);
			// 添加商品记录
			goodsBiz.addGoods(goodsDTO);
			return "toSearchGoods";
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
