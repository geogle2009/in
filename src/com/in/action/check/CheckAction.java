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
		log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfoInit");

		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfoInit");

		return SUCCESS;
	}

	public String queryMemberInfo() {
		log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfo");

		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfo");
		return SUCCESS;
	}
}
