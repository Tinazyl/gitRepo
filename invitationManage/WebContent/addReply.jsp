<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=basePath%>static/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1 style="text-align: center">添加回复</h1>
	<input type="text" class="hidden" value="${sessionScope.id}">
	<table class="table">
		<tbody align="center">
		<tr>
			<td ><span>回复内容：</span><textarea  clos="20" rows="5"></textarea></td>
		</tr>
		<tr>
			<td>回复昵称：<input type="text" id="author"></td>
		</tr>
		<tr>
			<td><input type="button" value="提交"/></td>
		</tr>
		</tbody>
	</table>
	
	
</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/addReply.js"></script>
</html>