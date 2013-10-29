<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/global.jsp"%>
<%@ include file="../common/meta.jsp"%>
<title>Index</title>
<script src="${ctx}/javascript/home.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/javascript/jcrop/jquery.Jcrop.js"></script>
<link rel="stylesheet" href="${ctx}/javascript/jcrop/jquery.Jcrop.css"
	type="text/css" />
<script language="Javascript">
	
</script>
</head>
<body class="easyui-layout">
	<div region="west" split="true" title="导航" style="width: 250px;">
	</div>
	<div region="center" id="content"></div>
	<div region="north" style="height: 30px">Test</div>
	<div region="south" border="false" style="height: 20px;">south
		region</div>
</body>
</html>