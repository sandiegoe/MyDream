package com.arex.mydream.web;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

@Component
@Scope(value="prototype")
public class UserAction{


	public UserAction() {
		
	}

	public String loginPage() {
		
		return "loginPage";
	}
}
