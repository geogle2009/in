package com.in.action.check;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.in.service.YbService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CheckAction.class);
	@Resource
	private YbService ybjkService;

	public String queryMemberInfoInit() {
		log.info("进入人员信息核对页面---begin---queryMemberInfoInit");

		log.info("进入人员信息核对页面---end---queryMemberInfoInit");

		return SUCCESS;
	}

	public String queryMemberInfo() {
		log.info("进入人员信息核对页面---begin---queryMemberInfo");

		log.info("进入人员信息核对页面---end---queryMemberInfo");
		return SUCCESS;
	}
}
