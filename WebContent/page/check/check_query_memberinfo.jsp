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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核对人员信息</title>
</head>
<body>
	<div style="padding: 1px 1px 1px 1px">
		<form id="check_query_memberinfo" method="post" action="<%=basePath%>page/check/querymemberinfo.action">
			<table cellpadding="1" width="100%">
				<tr>
					<td>家庭编号</td>
					<td><input size="12" class="easyui-textbox" type="text" name="name"
						data-options=""></input></td>

					<td>姓名</td>
					<td><input size="12"  class="easyui-textbox" type="text" name="name"
						data-options=""></input></td>

					<td>身份证号</td>
					<td><input size="12"  class="easyui-textbox" type="text" name="email"
						data-options=""></input></td>

					<td>医保卡号</td>
					<td><input size="12"  class="easyui-textbox" type="text" name="email"
						data-options=""></input></td>

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
</body>
</html>