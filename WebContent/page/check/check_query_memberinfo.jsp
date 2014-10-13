<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery/demo/demo.css">
<script type="text/javascript" src="<%=basePath%>jquery/jquery.min.js"></script>
<script type="text/javascript"src="<%=basePath%>jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery/locale/easyui-lang-zh_CN.js"></script>
<title>核对人员信息</title>
</head>
<body>
	<div style="padding: 1px 1px 1px 1px">
			<table cellpadding="1" width="95%">
			<tr>
				<td>家庭编号：<input id="memberDTO_familyno" size="16"
					class="easyui-textbox" type="text" name="memberDTO.familyno"
					data-options=""></input></td>
				<td>姓名：<input id="memberDTO_membername" size="16"
					class="easyui-textbox" type="text" name="memberDTO.membername"
					data-options=""></input></td>
				<td>身份证号：<input id="memberDTO_paperid" size="18"
					class="easyui-textbox" type="text" name="memberDTO.paperid"
					data-options=""></input></td>
				<td>医保卡号：<input id="memberDTO_ssn" size="16"
					class="easyui-textbox" type="text" name="memberDTO.ssn"
					data-options=""></input></td>
				<td>机构： <s:select  theme="simple" cssClass="easyui-combobox"
						list="orgs" listKey="organizationId" id="memberDTO_onNo"
						listValue="asorgname" name="memberDTO.onNo" />
				</td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">查询</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()">导出</a></td>
			</tr>
		</table>
	</div>
	<table id="list_data" 
		style="width: 95%;height:95%; padding: 10px 10px 10px 10px;">
		<thead>
			<tr>
				<th field="familyno"  align="center">家庭编号</th>
				<th field="membername"  align="center">姓名</th>
				<th field="paperid" align="center">身份证号</th>
				<th field="ssn" align="center">医保卡号</th>
				<th field="personstate" align="center">状态</th>
				<th field="assistType" align="center">救助状态</th>
				<th field="asort" align="center">再保障状态</th>
			</tr>
		</thead>
	</table>
	<script>
	//datagrid初始化 
	$('#list_data').datagrid({
	    title:'查询结果',
	    iconCls:'icon-search',//图标  
	    width: 'auto',
	    height: 'auto',
	    url:'<%=basePath%>page/check/querymemberinfo.action',
		remoteSort : false,
		singleSelect : false,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,
		queryParams: {
			'memberDTO.familyno':$('#memberDTO_familyno')[0].value,
			'memberDTO.membername':$('#memberDTO_membername')[0].value,
			'memberDTO.paperid':$('#memberDTO_paperid')[0].value,
			'memberDTO.ssn':$('#memberDTO_ssn')[0].value,
			'memberDTO.onNo':'2202'
		},
		loadFilter: function(data){
			if (data.d){
				return data.d;
			} else {
				for (var i = 0; i < data.rows.length; i++) {
					for (var att in data.rows[i]) {
						if(data.rows[i][att]=="null"){
							data.rows[i][att]="";
						}
		               }
				}
				return data;
			}
		}
	});
		//设置分页控件  
		var p = $('#list_data').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ,20,25,30],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	</script>
	 <script>
        function submitForm(){
        	var year=$('#memberDTO_onNo').combobox('getValue');
        	$('#list_data').datagrid({
        		queryParams: {
        			'memberDTO.familyno':$('#memberDTO_familyno')[0].value,
        			'memberDTO.membername':$('#memberDTO_membername')[0].value,
        			'memberDTO.paperid':$('#memberDTO_paperid')[0].value,
        			'memberDTO.ssn':$('#memberDTO_ssn')[0].value,
        			'memberDTO.onNo':''+year
        		}
        	}); 
        }
        function clearForm(){
        	
        }
    </script>
</body>
</html>