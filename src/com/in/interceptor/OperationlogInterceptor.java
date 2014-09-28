package com.in.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.in.model.Ybjk;
import com.in.service.YbService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class OperationlogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5538146788690142747L;
	@Resource
	private YbService ybjkService;

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();

		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		System.out.println(request.getServletPath());
		//���������־
		return invocation.invoke();
	}

}
