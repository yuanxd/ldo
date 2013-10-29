<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="common/global.jsp"%>
<%@ include file="common/meta.jsp"%>
<title>Index</title>
<script src="${ctx }/javascript/index.js" type="text/javascript"></script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx}/index/start" method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<small>Test流程发起</small>
			</legend>
			<table border="1">
				<tr>
					<td>名称：</td>
					<td><input type="text" id="name" name="name" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					   <a id="apply" href="#">申请</a>
					</td>
				</tr>
			</table>
		</fieldset>
	</form:form>
	<fieldset>
		<legend>
			<small>TestMVC</small>
		</legend>
		<table border="1">
			<tr>
				<form:form id="inputForma" action="${ctx}/mvc" method="post">
					<td>
						<button type="submmit">mvc</button>
					</td>
				</form:form>
			</tr>
		</table>
	</fieldset>
</body>
</html>