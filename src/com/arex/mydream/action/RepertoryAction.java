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
import com.arex.mydream.action.vo.RepertoryDTO;
import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.model.Comment;
import com.arex.mydream.service.CommentBiz;
import com.arex.mydream.service.GoodsBiz;
import com.arex.mydream.service.RepertoryBiz;
import com.arex.mydream.service.StoreBiz;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value = "prototype")
public class RepertoryAction implements ModelDriven<RepertoryDTO>, ServletRequestAware, ServletResponseAware {

	Integer pagesize = 8;
	private RepertoryDTO model = new RepertoryDTO();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;
	@Resource
	private CommentBiz commentBiz;

	@Resource(name="repertoryBizImpl")
	private RepertoryBiz repertoryBiz;	
	@Resource(name="goodsBizImpl")
	private GoodsBiz goodsBiz;
	@Resource(name="storeBizImpl")
	private StoreBiz storeBiz;

	@Override
	public RepertoryDTO getModel() {
		return model;
	}

	public String searchBygid(){
		
		if (model==null || model.getrGid()==0) {
			return "toUserIndex";
		}
		
		int gid = model.getrGid();
		
		GoodsDTO goodsDTO = goodsBiz.searchGoods(gid);
		if (goodsDTO == null) {
			return "toUserIndex";
		}
		
		List<RepertoryDTO> listRO = repertoryBiz.searchBygId(gid);
		if (listRO==null || listRO.size()==0) {
			return "toUserIndex";
		}
		
		request.setAttribute("goodsDTO", goodsDTO);
		request.setAttribute("repertoryDTO", listRO.get(0));
		request.setAttribute("goods", goodsDTO.getgPic());
		
		//
		List<Comment> listcomname = commentBiz.searchComment();
		request.setAttribute("listcomment", listcomname);
		
		return "goumai";
	}
	
	public String searchfruit() {
		
		List<RepertoryDTO> listRO = repertoryBiz.searchRepertory();
		List<GoodsDTO> listGO = new ArrayList<GoodsDTO>();
		for (RepertoryDTO repertoryDTO : listRO) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(repertoryDTO.getrGid());
			StoreDTO storeDTO = storeBiz.searchStore(repertoryDTO.getrSid());
			goodsDTO.setsName(storeDTO.getsName());
			listGO.add(goodsDTO);
		}
		
		request.setAttribute("listregost", listGO);
		
		return "fruit";
	}
	
	public String searchGiftCondition() {
		
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
		Integer count = repertoryBiz.searchGiftCount(model);// 记录总数
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
		
		
		List<RepertoryDTO> listRO = repertoryBiz.searchGift(model, page.getPageNo(), page.getPageSize());
		List<GoodsDTO> listGO = new ArrayList<GoodsDTO>();
		for (RepertoryDTO repertoryDTO : listRO) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(repertoryDTO.getrGid());
			StoreDTO storeDTO = storeBiz.searchStore(repertoryDTO.getrSid());
			goodsDTO.setsName(storeDTO.getsName());
			listGO.add(goodsDTO);
		}
	
		request.setAttribute("listgtype", listGO);
		request.getSession().setAttribute("regost", model);
		request.setAttribute("page", page);
		
		return "gift";
	}
	
	public String searchCondition() {
		
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
		
		Integer count = repertoryBiz.searchRepertoryCount(model);
		
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
		
		List<RepertoryDTO> listRO = repertoryBiz.searchPage_Repertory(model, page.getPageNo(), page.getPageSize());
		List<GoodsDTO> listGO = new ArrayList<GoodsDTO>();
		for (RepertoryDTO repertoryDTO : listRO) {
			GoodsDTO goodsDTO = goodsBiz.searchGoods(repertoryDTO.getrGid());
			StoreDTO storeDTO = storeBiz.searchStore(repertoryDTO.getrSid());
			goodsDTO.setsName(storeDTO.getsName());
			listGO.add(goodsDTO);
		}
		
		request.setAttribute("listregost", listGO);
		request.setAttribute("page", page);
		request.getSession().setAttribute("regost", model);
		
		return "fruit";
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
