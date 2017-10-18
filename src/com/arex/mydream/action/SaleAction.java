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

import com.arex.mydream.action.vo.SaleDTO;
import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.service.SaleBiz;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author arex
 * @date 2017年10月18日
 *
 */
@Component
@Scope(value = "prototype")
public class SaleAction implements ModelDriven<SaleDTO>, ServletRequestAware, ServletResponseAware {

	private SaleDTO model = new SaleDTO();
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;

	@Resource(name="saleBizImpl")
	private SaleBiz saleBiz;
	
	@Override
	public SaleDTO getModel() {
		return model;
	}

	public SaleAction() {
	}

	public String searchSale() {
		
		StoreDTO store = (StoreDTO) session.getAttribute("store");
		if (store==null || store.getsName()==null || "".equals(store.getsName())) {
			return "toAdminLoginPage";
		}
		
		SaleDTO saleDTO = saleBiz.searchSaleBysName(store.getsName());
		request.setAttribute("sale11", saleDTO);
		
		return "sale";
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
