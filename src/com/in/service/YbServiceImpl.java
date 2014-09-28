package com.in.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.in.dao.BaseDAO;
import com.in.dto.UserDTO;
import com.in.model.SysTEmployee;

@Service("ybService")
public class YbServiceImpl implements YbService {

	@Resource
	private BaseDAO<SysTEmployee> sysTEmployeeDAO;

	public UserDTO findUser(UserDTO userDTO) {
		String hql = "";
		Object[] param = null;
		if ("95588".equals(userDTO.getToken())) {
			hql = "select emp from SysTEmployee emp where emp.account=? and emp.password=?";
			param = new Object[2];
			param[0] = userDTO.getAccount();
			param[1] = userDTO.getPassword();
		} else {
			hql = "select emp from SysTEmployee emp where emp.account=? and emp.password=? and emp.idcard=?";
			param = new Object[3];
			param[0] = userDTO.getAccount();
			param[1] = userDTO.getPassword();
			param[2] = userDTO.getToken();
		}

		List<SysTEmployee> rs = sysTEmployeeDAO.find(hql, param);
		if (null != rs && rs.size() > 0) {
			SysTEmployee sysTEmployee = rs.get(0);
			BeanUtils.copyProperties(userDTO, sysTEmployee);
		} else {
			userDTO = null;
		}
		return userDTO;
	}

}
