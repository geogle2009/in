package com.in.service;

import java.util.List;

import com.in.dto.MemberDTO;
import com.in.dto.OrganizationDTO;
import com.in.dto.UserDTO;

public interface YbService {
	public UserDTO findUser(UserDTO userDTO);

	public List<MemberDTO> finMembers(String sql, List<Object> param);

	public String finMembersCount(String sql, List<Object> param);

	public List<OrganizationDTO> findOrganlist(String onno);
	
	public MemberDTO findMemeber(MemberDTO memberDTO);
}
