package com.in.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.in.dao.BaseDAO;
import com.in.dto.MemberDTO;
import com.in.dto.UserDTO;
import com.in.model.MemberBaseinfo;
import com.in.model.SysTEmployee;

@Service("ybService")
public class YbServiceImpl implements YbService {

	@Resource
	private BaseDAO<SysTEmployee> sysTEmployeeDAO;
	@Resource
	private BaseDAO<MemberBaseinfo> memberBaseinfoDAO;

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

	@SuppressWarnings("rawtypes")
	@Override
	/*
	 * MEMBER_ID 121031 DS 1 FAMILYNO 22020102010247 MEMBERNAME ÕÅ²© PAPERID
	 * 220202810813422 SSN 677939 PERSONSTATE Õý³£ ASSIST_TYPE 00 ASORT 0 NUM 1
	 */
	public List<MemberDTO> finMembers(String sql) {
		List<MemberDTO> resultlist = new ArrayList<MemberDTO>();
		Object[] param = null;
		List rs = memberBaseinfoDAO.findJDBCSql(sql, param);
		for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
			MemberDTO e = new MemberDTO();
			Object[] s = (Object[]) iterator.next();
			e.setMemberId(" " + s[0]);
			e.setDs("" + s[1]);
			e.setFamilyno("" + s[2]);
			e.setMembername("" + s[3]);
			e.setPaperid("" + s[4]);
			e.setSsn("" + s[5]);
			e.setPersonstate("" + s[6]);
			e.setAssistType("" + s[7]);
			e.setAsort(new BigDecimal(s[8].toString()));
			System.out.println(e.getMembername());
			resultlist.add(e);
		}
		
		return resultlist;
	}

	@Override
	public String finMembersCount(String sql) {
		Object[] param = null;
		Long cnt = memberBaseinfoDAO.countJDBCsql(sql, param);
		if (null != cnt) {
			return cnt+"";
		} else {
			return "0";
		}
	}

}
