package com.arex.mydream.action;


import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.UserInfo;
import com.arex.mydream.model.User;
import com.arex.mydream.service.UserService;
import com.arex.mydream.util.SHA1jiami;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value="prototype")
public class UserAction extends ActionSupport implements ModelDriven,ServletResponseAware,ServletRequestAware{
	private UserInfo userInfo = new UserInfo();
	private UserService userService;
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	public UserAction() {
		
	}
	
	public String userPage() {
		return "user";
	}

	public String indexPage() {
		return "indexPage";
	}
	
	public String loginPage() {
		return "loginPage";
	}
	
	public String zhucePage() {
		return "zhucePage";
	}
	
	public String zhuce(){
		if (userInfo.getuPhone() == null || "".equals(userInfo.getuPhone()) || userInfo.getuPwd() == null
				|| "".equals(userInfo.getuPwd()) || userInfo.getuPwd1() == null || "".equals(userInfo.getuPwd1())
				|| userInfo.getuMail() == null || "".equals(userInfo.getuMail()) || userInfo.getuName() == null
				|| "".equals(userInfo.getuName())) {
			request.setAttribute("errMs", "你的输入不正确！");
			return "zhucePage";
		} else {
			if (userInfo.getuPwd().equals(userInfo.getuPwd1())) {// 2次输入密码一致
				return this.zhuceRand();
			} else {
				request.getSession().setAttribute("errMs", "对不起您输入的不正确");
				return "zhucePage";
			}
		}
	}
	
	public String zhuceRand(){
		String sRand = (String) request.getSession().getAttribute("CHECK_NUMBER_KEY");
		
		if (userInfo.getRand() == null || "".equals(userInfo.getRand())) {
			request.setAttribute("errMs", "请输入正确的验证码！");
			return "zhucePage";
		} else {
			if (userInfo.getRand().equals(sRand)) {
				userInfo.setuFlag("1");
				
				//加密用户密码
				//String SAUpwd = SHA1jiami.SHA1Digest(uPwd);// 加密用户输入的密码
				userInfo.setuPwd(SHA1jiami.SHA1Digest(userInfo.getuPwd()));
				userService.addUser(userInfo);

				User user1 = userService.searchUser(userInfo.getuPhone());

				request.getSession().setAttribute("user", user1);
				
				Cookie registerCookies = new Cookie("register_phone",user1.getuPhone());
				registerCookies.setMaxAge(24*60*60);
				response.addCookie(registerCookies);

				return "zhuce";
			} else {
				request.setAttribute("errMs", "请输入正确的验证码！");
				return "zhucePage";
			}
		}
	}
	
	public String loginRand(){
		String sRand = (String) request.getSession().getAttribute("CHECK_NUMBER_KEY");// 获取生产的验证码

		if (userInfo.getRand() == null || "".equals(userInfo.getRand())) {
			request.setAttribute("errMs", "请输入正确的验证码！");
			return "loginPage";
		} else {
			if (userInfo.getRand().equals(sRand)) {
				return this.loginUser();
			} else {
				request.setAttribute("errMs", "请输入正确的验证码！");
				return "loginPage";
			}
		}
	}
	
	public String loginUser(){
		// System.out.println(uPhone);
		// System.out.println(uPwd);
		if (userInfo.getuPhone() == null || "".equals(userInfo.getuPhone()) || userInfo.getuPwd() == null
				|| "".equals(userInfo.getuPwd())) {
			request.getSession().setAttribute("errMs", "您输入的用户名和密码有误！");
			return "loginPage";
		} else {
			
			//System.out.println(userInfo.getuPhone());
			//System.out.println(userInfo.getuPwd());
			User user = userService.loginUser(userInfo.getuPhone(), userInfo.getuPwd());
			//System.out.println(user.toString());
			if (user != null) {
				request.getSession().setAttribute("user", user);
				return "user";
				// response.sendRedirect(request.getContextPath()+"/user.jsp");
			} else if (user == null) {
				request.getSession().setAttribute("errMs", "您输入的用户名和密码有误！");
				return "loginPage";
			}

		}
		return null;
	}
		
	public String ajax() throws Exception{
		//System.out.println(userInfo.getuPhone());
		Boolean bool = userService.checkUser(userInfo.getuPhone()); // 查询是否有此用户
		// System.out.println(bool);
		PrintWriter out = response.getWriter();
		out.print(bool);
		out.flush(); // 清空
		out.close(); // 关闭
		out.close();
		return null;
	}
	
	public String loginOut(){
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("gwc");
		return "loginPage";
	}

	public String searchjibenziliao(){
		User user = (User) request.getSession().getAttribute("user");
		String uPhone = user.getuPhone();
		User us = userService.searchUser(uPhone);
		// request.setAttribute("us", us);
		request.getSession().setAttribute("user", us);
		return "jibenziliao";
	}
	
	@Override
	public Object getModel() {
		return userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserService getUserService() {
		return userService;
	}
	
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
