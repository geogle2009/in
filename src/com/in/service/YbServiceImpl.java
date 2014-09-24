package com.in.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.in.dao.BaseDAO;
import com.in.model.Ybjk;

@Service("ybService")
public class YbServiceImpl implements YbService {

	@Resource
	private BaseDAO<Ybjk> baseDAO;

	public void saveYbjk(Ybjk ybjk) {
		baseDAO.save(ybjk);
	}

}
