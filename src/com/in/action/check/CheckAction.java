package com.in.action.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.in.dto.MemberDTO;
import com.in.dto.OrganizationDTO;
import com.in.dto.UserDTO;
import com.in.service.YbService;
import com.in.util.Pager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CheckAction.class);
	@Resource
	private YbService ybjkService;

	private JSONObject result;// 返回的json

	@SuppressWarnings("rawtypes")
	private Map map;// 返回的json

	private String rows;// 每页显示的记录数

	private String page;// 当前第几页

	private MemberDTO memberDTO;

	private List<OrganizationDTO> orgs;

	@SuppressWarnings("rawtypes")
	public String queryMemberInfoInit() {
		log.info("进入人员信息核对页面---begin---queryMemberInfoInit");
		Map session = ActionContext.getContext().getSession();
		UserDTO user = (UserDTO) session.get("user");
		String orgno = user.getOrganizationId();
		orgs = ybjkService.findOrganlist(orgno);
		log.info("进入人员信息核对页面---end---queryMemberInfoInit");
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMemberInfo() {
		log.info("进入人员信息核对页面---begin---queryMemberInfo");
		Pager pager = new Pager(page, rows);
		int start = pager.start;
		int end = pager.end;
		List<Object> param = new ArrayList<Object>();
		String sqlwhere = "";
		String orderwhere = " order by t.familyno";

		if (null != memberDTO) {
			if (memberDTO.getOnNo().length() == 4) {
				sqlwhere = " and  t.on_no like ?  and t.on_no like ? ";
				param.add(memberDTO.getOnNo() + "%");
				param.add(memberDTO.getOnNo() + "%");
			} else {
				sqlwhere = " and t.area = ?  and t.on_no like ? ";
				param.add(memberDTO.getOnNo());
				param.add(memberDTO.getOnNo() + "%");
			}
			if (!"".equals(memberDTO.getFamilyno())) {
				sqlwhere = sqlwhere + " and t.familyno=? ";
				param.add(memberDTO.getFamilyno());

			}
			if (!"".equals(memberDTO.getSsn())) {
				sqlwhere = sqlwhere + " and t.ssn=? ";
				param.add(memberDTO.getSsn());

			}
			if (!"".equals(memberDTO.getMembername())) {
				sqlwhere = sqlwhere + " and t.membername like ? ";
				param.add("%" + memberDTO.getMembername() + "%");

			}
			if (!"".equals(memberDTO.getPaperid())) {
				sqlwhere = sqlwhere + " and t.paperid=? ";
				param.add(memberDTO.getPaperid());
			}

		}
		param.add(start);
		param.add(end);

		String sql = "SELECT * FROM (SELECT t.member_id,t.ds,t.familyno,t.membername,t.paperid,t.ssn,t.personstate,t.assist_type,t.asort, row_number() over(ORDER BY t.area) AS num "
				+ " FROM member_baseinfo t where 1=1 "
				+ sqlwhere
				+ orderwhere
				+ ") xx WHERE num BETWEEN ? AND ?";
		String sqlcount = " SELECT count(1) as cnt FROM member_baseinfo t  where 1=1 "
				+ sqlwhere;

		List<MemberDTO> list = ybjkService.finMembers(sql, param);

		param.remove(param.size() - 1);
		param.remove(param.size() - 1);
		String cnt = ybjkService.finMembersCount(sqlcount, param);

		Map jsonMap = new HashMap();
		jsonMap.put("total", cnt);// total键
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		// result = JSONObject.fromObject(jsonMap);// 格式化result 一定要是JSONObject
		map = jsonMap;
		log.info("进入人员信息核对页面---end---queryMemberInfo");
		return SUCCESS;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@SuppressWarnings("rawtypes")
	public Map getMap() {
		return map;
	}

	@SuppressWarnings("rawtypes")
	public void setMap(Map map) {
		this.map = map;
	}

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public List<OrganizationDTO> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<OrganizationDTO> orgs) {
		this.orgs = orgs;
	}
}
