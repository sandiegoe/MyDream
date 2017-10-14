package com.arex.mydream.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.mydream.entity.view.UserDTO;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value="prototype")
public class AdminAction implements ModelDriven<UserDTO>{
	
	
	private UserDTO model = new UserDTO();
	
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
		
		if (model == null || model.getUserName()==null || "".equals(model.getUserName()) || model.getUserPwd()==null ||
				"".equals(model.getUserPwd())) {
			return "loginPage";
		}
		
		String userName = model.getUserName();
		String userPwd = model.getUserPwd();
		
		return "index";
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
}
