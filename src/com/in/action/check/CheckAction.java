package com.in.action.check;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.in.dto.MemberDTO;
import com.in.dto.OrganizationDTO;
import com.in.dto.UserDTO;
import com.in.service.YbService;
import com.in.util.DateJsonValueProcessor;
import com.in.util.Pager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CheckAction.class);
	@Resource
	private YbService ybjkService;

	private JSONObject result;// ���ص�json

	@SuppressWarnings("rawtypes")
	private Map map;// ���ص�json

	private String rows;// ÿҳ��ʾ�ļ�¼��

	private String page;// ��ǰ�ڼ�ҳ

	private MemberDTO memberDTO;
	
	private MemberDTO mdto;

	private List<OrganizationDTO> orgs;
	
	private String key;

	@SuppressWarnings("rawtypes")
	public String queryMemberInfoInit() {
		log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfoInit");
		Map session = ActionContext.getContext().getSession();
		UserDTO user = (UserDTO) session.get("user");
		String orgno = user.getOrganizationId();
		orgs = ybjkService.findOrganlist(orgno);
		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfoInit");
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMemberInfo() {
		log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfo");
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
		jsonMap.put("total", cnt);// total��
		jsonMap.put("rows", list);// rows�� ���ÿҳ��¼ list
		// result = JSONObject.fromObject(jsonMap);// ��ʽ��result һ��Ҫ��JSONObject
		map = jsonMap;
		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfo");
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String checkSSNInit(){
		MemberDTO m = new MemberDTO();
		m.setMemberId(key.substring(0,key.length()-1));
		m.setDs(key.substring(key.length()-1));
		MemberDTO mdto = ybjkService.findMemeber(m);
/*		Map jsonMap = new HashMap();
		jsonMap.put("mdto", mdto);
		map = jsonMap;*/
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		result = JSONObject.fromObject(mdto,config);
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public MemberDTO getMdto() {
		return mdto;
	}

	public void setMdto(MemberDTO mdto) {
		this.mdto = mdto;
	}
}
