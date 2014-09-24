package com.in.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.in.dto.UserDTO;
import com.in.model.Ybjk;
import com.in.service.YbService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(LoginAction.class);
	@Resource
	private YbService ybjkService;

	private String username;
	private String password;

	public String login() {

		Ybjk ybjk = new Ybjk();
		ybjk.setA1(username);
		ybjk.setA2(password);
		ybjkService.saveYbjk(ybjk);
		log.info("======info>>" + username + ">>>" + password);
		
		if (username.equals(password)) {
			UserDTO user = new UserDTO();
			ActionContext.getContext().getSession().put("user", user);
			
		}
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
