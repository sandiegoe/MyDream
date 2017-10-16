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
import com.arex.mydream.action.vo.UserDTO;
import com.arex.mydream.model.User;
import com.arex.mydream.service.StoreBiz;
import com.arex.mydream.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value = "prototype")
public class AdminAction implements ModelDriven<UserDTO>, ServletRequestAware, ServletResponseAware {

	private UserDTO model = new UserDTO();
	@Resource(name = "userService")
	private UserService userService;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	private HttpServletResponse response;

	@Resource(name = "storeBizImpl")
	private StoreBiz storeBiz;

	@Override
	public UserDTO getModel() {
		return model;
	}

	public AdminAction() {
	}

	public String index() {
		return "index";
	}

	public String loginPage() {
		return "loginPage";
	}

	public String login() {
		if (model.getuPhone() == null || "".equals(model.getuPhone()) || model.getuPwd() == null
				|| "".equals(model.getuPwd())) {
			request.getSession().setAttribute("errMs", "您输入的用户名和密码有误！");
			return "loginPage";
		} else {
			User user = userService.loginUser(model.getuPhone(), model.getuPwd());
			if (user != null) {
				StoreDTO storeDTO = storeBiz.searchStoreBySuid(user.getuId());
				if (storeDTO != null) {
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("store", storeDTO);
					return "toIndex";
				} else {
					return "loginPage";
				}
			} else if (user == null) {
				request.getSession().setAttribute("errMs", "您输入的用户名和密码有误！");
				return "loginPage";
			}

		}
		return null;
	}

	public String loginOut() {

		session.removeAttribute("user");
		session.removeAttribute("store");
		
		return "loginPage";
	}

	public String header() {
		return "header";
	}

	public String menu() {
		return "menu";
	}

	public String welcome() {
		return "welcome";
	}

	public String footer() {
		return "footer";
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
