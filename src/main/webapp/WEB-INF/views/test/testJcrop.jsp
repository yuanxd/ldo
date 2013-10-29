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
	function showCoords(c) {
		$('#x').val(c.x);
		$('#y').val(c.y);
		$('#x2').val(c.x2);
		$('#y2').val(c.y2);
		$('#w').val(c.w);
		$('#h').val(c.h);
	};

	function checkCoords() {
		if (parseInt(jQuery('#w').val()) > 0) {
			return true;
		}
		alert('Please select a crop region then press submit.');
		return false;
	};
	$(function($) {
		$('#testJcrop').Jcrop({
			onSelect : showCoords,
			onChange : showCoords
		});
	});
</script>
</head>
<body>
	选择裁剪区域
	<br />
	<img id="testJcrop" src="${ctx}/image/test/sago.jpg">
	<form action="${ctx}/test/jcrop/crop" method="post"
		onsubmit="return checkCoords();">
		<input type="hidden" id="srcpath" name="srcpath"
			value="${ctx}/image/test/sago.jpg" /> <label>X1 <input
			type="text" size="4" id="x" name="x" /></label> <label>Y1 <input
			type="text" size="4" id="y" name="y" /></label> <label>X2 <input
			type="text" size="4" id="x2" name="x2" /></label> <label>Y2 <input
			type="text" size="4" id="y2" name="y2" /></label> <label>W <input
			type="text" size="4" id="w" name="w" /></label> <label>H <input
			type="text" size="4" id="h" name="h" /></label> <input type="submit"
			value="Crop Image" style="float: left; width: 98px;" />
	</form>
</body>
</html>