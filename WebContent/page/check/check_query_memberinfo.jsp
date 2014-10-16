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
					onclick="submitForm()" icon="icon-search">查询</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()" icon="icon-redo">导出</a></td>
			</tr>
		</table>
	</div>
	<table id="list_data" 
		style="width: 95%;height:95%; padding: 10px 10px 10px 10px;">
<!-- 		<thead>
			<tr>
				<th field="familyno"  align="center">家庭编号</th>
				<th field="membername"  align="center">姓名</th>
				<th field="paperid" align="center">身份证号</th>
				<th field="ssn" align="center">医保卡号</th>
				<th field="personstate" align="center">状态</th>
				<th field="assistType" align="center">救助状态</th>
				<th field="asort" align="center">再保障状态</th>
				<th field="do" align="center" formatter="managerstr()">操作</th>
			</tr>
		</thead> -->
	</table>
	<div id="win_check" class="easyui-window" closed="true" style="width:640px;height:300px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
		<form id="check" method="post">
            <div data-options="region:'east',split:true" style="width:310px;padding:8px;">
	            <div style="padding:5px 5px 5px 5px">
	            <table cellpadding="5">
	            	<tr>
	                    <td><font color="#0099FF" size="12px">医保端：</font></td>
	                    <td></td>
	                </tr>
	                <tr>
	                    <td>家庭编号:</td>
	                    <td><input name="familyno" class="easyui-textbox" type="text"></input></td>
	                </tr>
	                <tr>
	                	<td>医保卡号：</td>
	                	<td><input type="radio" name="lang" value="01"><span>111</span><br/>
	                		<input type="radio" name="lang" value="02"><span>222</span><br/>
	                		<input type="radio" name="lang" value="03"><span>333</span><br/>
	                		<input type="radio" name="lang" value="04"><span>444</span><br/>
	                	</td>
	                </tr>
	            </table>
	            </div>
            </div>
            <div data-options="region:'center',split:true" style="width:320px;padding:8px;">
               <div style="padding:5px 5px 5px 5px">
	            <table cellpadding="5">
	            	<tr>
	                    <td><font color="#0099FF" size="12px">民政端：</font></td>
	                    <td></td>
	                </tr>
	                <tr>
	                    <td>家庭编号:</td>
	                    <td><input name="familyno" class="easyui-textbox" type="text" size="30" readonly="readonly"></input></td>
	                </tr>
	                <tr>
	                    <td>姓名:</td>
	                    <td><input name="membername" class="easyui-textbox" type="text" size="30" readonly="readonly"></input></td>
	                </tr>
	                <tr>
	                    <td>性别:</td>
	                    <td><input name="sex" class="easyui-textbox" type="text" size="30" readonly="readonly"></input></td>
	                </tr>
	                <tr>
	                    <td>身份证号码:</td>
	                    <td><input name="paperid" class="easyui-textbox" type="text" size="30" readonly="readonly"></input></td>
	                </tr>
	                <tr>
	                    <td>医保卡号:</td>
	                    <td><input name="ssn" class="easyui-textbox" type="text" size="30" readonly="readonly"></input></td>
	                </tr>
	            </table>
        		</div>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:alert('ok')" style="width:80px">Ok</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWin_check()" style="width:80px">关闭</a>
            </div>
            </form>
        </div>
	</div>
	<div id="win_change" class="easyui-window" closed="true"  style="width:1000px;height:370px;padding:5px;">
	<div class="easyui-layout" data-options="fit:true">
		<form id="change" method="post">
               <div data-options="region:'north',split:true,border:true" style="padding:5px;height:125px;">
				<table width="100%" border="0" cellpadding="5">
					<tr>
						<td align="right">家庭编号：</td>
						<td align="left"><input id="change_familyno" size="40" type="text" readonly="readonly"></input></td>
						<td align="right">姓名：</td>
						<td align="left"><input id="change_membername" size="40" type="text" readonly="readonly"></input></td>
					</tr>
					<tr>
						<td align="right">身份证号码：</td>
						<td align="left"><input id="change_paperid" size="40" type="text" readonly="readonly"></input></td>
						<td align="right">医保卡号：</td>
						<td align="left"><input id="change_ssn" size="40" type="text" readonly="readonly"></input></td>
					</tr>
					<tr>
						<td align="right">当前救助状态：</td>
						<td align="left">
			                <input type="checkbox" id="status1" name="status" value="10"/>固定保障&nbsp;&nbsp;&nbsp;&nbsp;
			                <input type="checkbox" id="status2" name="status" value="1" />再保障
						</td>
						<td align="right">&nbsp;</td>
						<td align="left" ><a class="easyui-linkbutton" data-options="iconCls:'icon-tip'" href="javascript:void(0)" onclick="javascript:startup()" style="width:240px">更改救助状态</a></td>
					</tr>
					<tr>
						<td><input id='change_memberid' type=hidden /></td>
						<td><input id='change_ds' type=hidden /></td>
					</tr>
				</table>
            </div>
            <div data-options="region:'east',split:true" style="width:500px;">
	            <div style="padding:5px 5px 5px 5px">
	            	<table id="toguarantee_data" >
                   	<thead>
						<tr>
							<th field="barFamilyno"  align="center">家庭编号</th>
							<th field="barMaster"  align="center">户主姓名</th>
							<th field="barSubject"  align="center">救助时间</th>
							<th field="barMoney"  align="right">救助金额</th>
						</tr>
					</thead>
                   	</table>
	            </div>
            </div>
            <div data-options="region:'center',split:true" style="width:500px;">
               <div style="padding:5px 5px 5px 5px">
                   	<table id="guarantee_data" >
                   	<thead>
						<tr>
							<th field="barFamilyno"  align="center">家庭编号</th>
							<th field="barMaster"  align="center">户主姓名</th>
							<th field="barSubject"  align="center">救助时间</th>
							<th field="barMoney"  align="right">救助金额</th>
						</tr>
					</thead>
                   	</table>
        		</div>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWin_change()" style="width:80px">关闭</a>
            </div>
            </form>
        </div>
	</div>
	<div id="win_view" style="width:600px;height:400px;"></div>
	<script>
	//datagrid初始化 
	$('#list_data').datagrid({
	    title:'查询结果',
	    iconCls:'icon-search',//图标  
	    width: 'auto',
	    height: 'auto',
	    url:'<%=basePath%>page/check/querymemberinfo.action',
		remoteSort : false,
		singleSelect : true,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,
		queryParams: {
			'memberDTO.familyno':$('#memberDTO_familyno')[0].value,
			'memberDTO.membername':$('#memberDTO_membername')[0].value,
			'memberDTO.paperid':$('#memberDTO_paperid')[0].value,
			'memberDTO.ssn':$('#memberDTO_ssn')[0].value,
			'memberDTO.onNo':'2202'
		},
		columns:[[  
				 {field:'familyno',title:'家庭编号',width:'12%'},
				 {field:'membername',title:'姓名',width:'8%'},
				 {field:'paperid',title:'身份证号',width:'18%'},
				 {field:'ssn',title:'医保卡号',width:'10%'},
				 {field:'ds',title:'来源',width:'6%',
					 formatter:function(value){
						 if(value=='1'){
							 return '城市';
						 }else if(value=='2'){
							 return '农村';							 
						 }
					 }},
				 {field:'personstate',title:'状态',width:'6%'},
				 {field:'assistType',title:'救助状态',width:'8%'},
				 {field:'asort',title:'再保障状态',width:'8%',
					 formatter:function(value){
						 if(value=='0'){
							 return '否';
						 }else if(value=='1'){
							 return '是';
						 }
					 }},
                 {field:'opt',title:'操作',width:'26%',align:'center',
                   formatter:function(value,rec,index){
                       var s = '<a href="javascript:void(0)" onclick="checkssn(\''+ rec.memberId+''+rec.ds + '\')">核对医保卡号</a> ';
                       var e = '<a href="javascript:void(0)" onclick="change(\''+ rec.familyno+'-'+rec.ds +'-'+rec.memberId + '\')">救助状态变更</a> ';
                       var d = '<a href="javascript:void(0)" onclick="view(\''+ rec.ds +'\')">查看救助记录</a> ';
                       return s+'&nbsp;&nbsp;'+e+'&nbsp;&nbsp;'+d;
                   }
                 }
        ]],
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
        };
        function clearForm(){
        	
        };
        function checkssn(key){ //核对ssn页面
         	var win;
        	win = $('#win_check').window({
        		title:"核对医保卡号",
        		modal:true
            }); 
        	$('#check').form('load','<%=basePath%>page/check/checkssninit.action?key='+key);	
            win.window('open');
        };
        function closeWin_check(){
        	$('#win_check').window('close');
        };
        function change(key){ //救助状态变更页面 
         	var win;
        	win = $('#win_change').window({
        		title:"救助状态变更",
        		modal:true
            });
        	<%-- $('#change').form('load','<%=basePath%>page/check/changstatusinit.action?key='+key); --%>
         	$.ajax({
                type: "POST",
                url: '<%=basePath%>page/check/changstatusinit.action?key='+key,
                dataType: "json",
                async: false,
                success: function(data){
                	$('#change_familyno')[0].value = data.familyno;
                    $('#change_membername')[0].value = data.membername;
                    $('#change_paperid')[0].value = data.paperid;
                    $('#change_ssn')[0].value = data.ssn;
                    if(data.assistType=="10"||data.assistType=="11"){
                    	$('#status1').prop("checked",true);
                    }else if(data.assistType=="00"){
                    	$('#status1').prop("checked",false);
                    }
                    if(data.asort=="1"){
                    	$('#status2').prop("checked",true);
                    }else if(data.asort=="0"){
                    	$('#status2').prop("checked",false);
                    }
                    $('#change_memberid')[0].value = data.memberId;
                    $('#change_ds')[0].value = data.ds;
                	win.window('open');
                	$('#guarantee_data').datagrid({
                	    title:'前四个月的救助情况--固定保障',
                	    iconCls:'',//图标  
                	    url:'<%=basePath%>page/check/getguarantee.action?key='+key,
                		remoteSort : false,
                		singleSelect : true,//是否单选  
                		pagination : false,//分页控件  
                		rownumbers : true,
                	});
                	$('#toguarantee_data').datagrid({
                	    title:'前四个月的救助情况--再保障',
                	    iconCls:'',//图标  
                	    url:'<%=basePath%>page/check/gettoguarantee.action?key='+key,
                		remoteSort : false,
                		singleSelect : true,//是否单选  
                		pagination : false,//分页控件  
                		rownumbers : true,
                	});
                	
                }
            });
            
        };
        function closeWin_change(){
        	$('#win_change').window('close');
        };
        function startup(){
        	
        	var checked = [];
        	$('input:checkbox:checked').each(function() {
                	checked.push($(this).val());
            });
        	var assistType="00";
        	var asort="0";
        	if(checked.length==1){
        		if(checked[0]==10){
        			assistType=checked[0];
        		}else if(checked[0]==1){
        			asort=checked[0];
        		}
        	}else if(checked.length==2){
        		assistType=checked[0];
        		asort=checked[1];
        	}
        	var key = $('#change_memberid').val()+ "-" + $('#change_ds').val() + "-" + assistType + "-" + asort;
        	$.ajax({
                type: "POST",
                url: '<%=basePath%>page/check/changstatus.action?key='+key,
                dataType: "json",
                async: false,
                success: function(json){
                	json = eval('(' + json + ')');
    				var val = json['u'];
    				if(val==1){
    					alert("修改成功！");
    				}else{
    					alert("修改失败！");
    				}
                }
            });
        };
        function view(bh){ //查看救助记录页面
        	$('#win_view').window({
        		title:"查看救助记录",
        		width:600,
        		height:400,
        		modal:true
           });
        };
    </script>
</body>
</html>