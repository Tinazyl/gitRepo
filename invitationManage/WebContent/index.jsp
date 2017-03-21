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
	<h1 style="text-align: center">帖子列表</h1>
	<div style="text-align:center">
		帖子标题：<input type="text" id="title" value="${sessionScope.title}"/>
		<input type="submit" value="搜索" id="search">
	</div>
	<table class="table table-striped table-hover">
		<thead>
			<th>标题</th>
			<th>内容摘要</th>
			<th>作者</th>
			<th>发布时间</th>
			<th>操作</th>
		</thead>
		<tbody id="data">
		<c:forEach items="${sessionScope.messages}" var="message">
			<tr>
				<td style="display:none">${message.id}</td>
				<td>${message.title}</td>
				<td>${message.summary}</td>
				<td>${message.author}</td>
				<td>${message.date}</td>
				<td><a href="#" data-info='query'>查看回复</a>&nbsp;&nbsp;<a href="#" data-info="delete">删除</a></td>
			</tr>
		</c:forEach>
			
		</tbody>
	</table>
	<div id="tip" align="center" style="color:red"></div>
	<div>
		第<span class="currentPage">${sessionScope.pageIndex}</span>页
		<span class="hidden">${sessionScope.totalPage}</span>
		<a href="#" onclick="firstPage(this);">首页</a>
		<a href="#" onclick="prePage(this);">上一页</a>
		<a href="#" onclick="nextPage(this);">下一页</a>
		<a href="#" onclick="lastPage(this);">尾页</a>
	</div>
	
</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/index.js"></script>
</html>