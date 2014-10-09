package com.in.action.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.in.dto.MemberDTO;
import com.in.service.YbService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CheckAction.class);
	@Resource
	private YbService ybjkService;

	private JSONObject result;// 返回的json
	
	private Map map;// 返回的json

	private String rows;// 每页显示的记录数

	private String page;// 当前第几页

	public String queryMemberInfoInit() {
		log.info("进入人员信息核对页面---begin---queryMemberInfoInit");

		log.info("进入人员信息核对页面---end---queryMemberInfoInit");

		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMemberInfo() {
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;
		int end = (intPage) * number;
		String sql = "SELECT * FROM (SELECT t.member_id,t.ds,t.familyno,t.membername,t.paperid,t.ssn,t.personstate,t.assist_type,t.asort, row_number() over(ORDER BY t.area) AS num "
				+ " FROM member_baseinfo t where t.area = '220201' "
				+ " and t.on_no like '220201%') xx WHERE num BETWEEN "
				+ start + " AND   " + end;
		String sqlcount = " SELECT count(1) as cnt FROM member_baseinfo t  where t.on_no like '220201%'";
		List<MemberDTO> list = ybjkService.finMembers(sql);
		Map jsonMap = new HashMap();// 定义map
		// 存放总记录数，必须的
	String a=	ybjkService.finMembersCount(sqlcount);
	log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+a);
	log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+list.size());
		jsonMap.put("total", a);// total键
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		//result = JSONObject.fromObject(jsonMap);// 格式化result 一定要是JSONObject
		map=jsonMap;
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

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
}
