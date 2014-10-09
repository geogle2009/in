package com.in.action.check;

import javax.annotation.Resource;

import org.json.JSONObject;
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

	private JSONObject result;//返回的json  
       
    private String rows;//每页显示的记录数  
       
    private String page;//当前第几页  

	public String queryMemberInfoInit() {
		log.info("进入人员信息核对页面---begin---queryMemberInfoInit");

		log.info("进入人员信息核对页面---end---queryMemberInfoInit");

		return SUCCESS;
	}

	public String queryMemberInfo() {
		/*log.info("进入人员信息核对页面---begin---queryMemberInfo");
		//当前页  
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);  
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number;  
           
        List<TblApp> list = appService.findByPageApp(start,number);//每页的数据，放入list  
            Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
            jsonMap.put("total", appService.getCountApp());//total键 存放总记录数，必须的  
            jsonMap.put("rows", list);//rows键 存放每页记录 list  
            result = JSONObject.fromObject(jsonMap);//格式化result   一定要是JSONObject  
            
        //result = JSONArray.fromObject(jsonMap);  
            
		log.info("进入人员信息核对页面---end---queryMemberInfo");*/
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
}
