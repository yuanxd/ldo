<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="common/global.jsp"%>
<%@ include file="common/meta.jsp"%>
<title>Index</title>
<script src="${ctx}/javascript/home.js" type="text/javascript"></script>

</head>
<body class="easyui-layout">
	<div region="west" split="true" title="导航" style="width: 250px;">
		<div class="easyui-accordion" fit=true>
			<div title="功能菜单" data-options="selected:true" style="overflow: auto; padding: 5px;">
				<ul id="functionTree" class="ztree"
					style="width: 260px; overflow: auto;"></ul>
			</div>
			<div title="系统配置" 
				style="overflow: auto; padding: 5px;">
				<ul id="systemConfigTree" class="ztree"
					style="width: 260px; overflow: auto;"></ul>
			</div>
		</div>
	</div>
	<div region="center" id="content">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页"></div>
		</div>
	</div>
	<div region="north" style="height: 30px">Test</div>
	<div region="south" border="false" style="height: 20px;">south
		region</div>
	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
</body>
</html>