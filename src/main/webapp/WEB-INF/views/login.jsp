<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String ctx = request.getContextPath();
%>
<html>
<head>
<%@ include file="common/global.jsp"%>
<%@ include file="common/meta.jsp"%>
<title>登录</title>

</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#loginDiv').dialog({
            title : 'login',
            closable : false,
            minimizable : false,
            maximizable : false,
            collapsible : false,
            resizable : false,
            modal : true,
            shadow : true,
            buttons : [ {
                text : 'login',
                handler : function() {
                    $.ajax({
                        type : "post",
                        url : "${ctx}/login/doLogin",
                        data : $('#loginForm').serialize(),
                        cache : false,
                        datatype : 'json',
                        success : function(data) {
                            data = eval(data);
                            if (data.success) {
                                window.location = data.originURI;
                            }else{
                                alert("用户名或密码错误");
                            }
                        }
                    });
                }
            } ]

        });
    });
</script>
	<div id="loginDiv">
		<form id="loginForm" action="${ctx}/login/doLogin" method="post">
			<input type="hidden" id="originURI" name="originURI"
				value="${param.originURI }">
			<table>
				<tr>
					<td>UserName</td>
					<td><input type="text" id="code" name="code" value="${code }" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" id="password" name="password" value="${password }" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>