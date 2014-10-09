<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	href="<%=basePath%>jquery/demo.css">
<script type="text/javascript" src="<%=basePath%>jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>jquery/jquery.easyui.min.js"></script>
<title>核对人员信息</title>


</head>
<body>
	<div style="padding: 1px 1px 1px 1px">
		<form id="check_query_memberinfo" >
			<table cellpadding="1" width="100%">
				<tr>
					<td>家庭编号</td>
					<td><input size="12" class="easyui-textbox" type="text"
						name="name" data-options=""></input></td>

					<td>姓名</td>
					<td><input size="12" class="easyui-textbox" type="text"
						name="name" data-options=""></input></td>

					<td>身份证号</td>
					<td><input size="12" class="easyui-textbox" type="text"
						name="email" data-options=""></input></td>

					<td>医保卡号</td>
					<td><input size="12" class="easyui-textbox" type="text"
						name="email" data-options=""></input></td>

					<td>所属机构</td>
					<td><select class="easyui-combobox" name="language"><option
								value="ar">Arabic</option>
							<option value="bg">Bulgarian</option>
							<option value="ca">Catalan</option>
							<option value="zh-cht">Chinese Traditional</option>
					</select></td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="submitForm()">查询</a> <a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="clearForm()">导出</a></td>
				</tr>
			</table>
		</form>
	</div>
	<table id="list_data" class="easyui-datagrid"
		style="width: 100%; padding: 10px 10px 10px 10px;">
		<thead>
			<tr>
				<th field="familyno"  align="center">家庭编号</th>
				<th field="name"  align="center">姓名</th>
				<th field="paperid" align="center">身份证号</th>
				<th field="ssn" align="center">医保卡号</th>
				<th field="personstate" align="center">医保卡号</th>
				<th field="assistType" align="center">医保卡号</th>
				<th field="asort" align="center">医保卡号</th>
			</tr>
		</thead>
	</table>
	<script>
	//datagrid初始化  
	$('#list_data').datagrid({  
	    title:'人员信息列表',  
	    iconCls:'icon-search',//图标  
	    width: 'auto',  
	    height: 'auto',  
	    nowrap: false,  
	    striped: true,  
	    border: true,  
	    collapsible:false,//是否可折叠的  
	    fit: true,//自动大小  
	    url:'<%=basePath%>page/check/querymemberinfo.action',
			//sortName: 'code',  
			//sortOrder: 'desc',  
			remoteSort : false,
			idField : 'memberId',
			singleSelect : false,//是否单选  
			pagination : true,//分页控件  
			rownumbers : true,//行号  
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : false
			} ] ]
		});
		//设置分页控件  
		var p = $('#list_data').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		/*onBeforeRefresh:function(){ 
		    $(this).pagination('loading'); 
		    alert('before refresh'); 
		    $(this).pagination('loaded'); 
		}*/
		});
	</script>
</body>
</html>