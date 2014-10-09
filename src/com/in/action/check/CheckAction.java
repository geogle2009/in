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

	private JSONObject result;//���ص�json  
       
    private String rows;//ÿҳ��ʾ�ļ�¼��  
       
    private String page;//��ǰ�ڼ�ҳ  

	public String queryMemberInfoInit() {
		log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfoInit");

		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfoInit");

		return SUCCESS;
	}

	public String queryMemberInfo() {
		/*log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfo");
		//��ǰҳ  
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //ÿҳ��ʾ����  
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);  
        //ÿҳ�Ŀ�ʼ��¼  ��һҳΪ1  �ڶ�ҳΪnumber +1   
        int start = (intPage-1)*number;  
           
        List<TblApp> list = appService.findByPageApp(start,number);//ÿҳ�����ݣ�����list  
            Map<String, Object> jsonMap = new HashMap<String, Object>();//����map  
            jsonMap.put("total", appService.getCountApp());//total�� ����ܼ�¼���������  
            jsonMap.put("rows", list);//rows�� ���ÿҳ��¼ list  
            result = JSONObject.fromObject(jsonMap);//��ʽ��result   һ��Ҫ��JSONObject  
            
        //result = JSONArray.fromObject(jsonMap);  
            
		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfo");*/
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
