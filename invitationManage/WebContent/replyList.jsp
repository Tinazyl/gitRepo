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
	<h1 style="text-align: center">回复信息列表</h1>
	<div style="text-align:right">
		<a href="addReply.jsp" id="add">添加回复</a>
		<a href="#" id="back">返回帖子列表</a>
	</div>
	<input type="text" value="${sessionScope.id}" class="hidden">
	<table class="table table-striped table-hover">
		<thead>
			<th>回复内容</th>
			<th>回复昵称</th>
			<th>发布时间</th>
		</thead>
		<tbody>
		<c:forEach items="${sessionScope.replies}" var="reply">
			<tr>
				<td>${reply.content}</td>
				<td>${reply.author}</td>
				<td>${reply.date}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<c:if test="${sessionScope.totalPage > 0 }">
			第<span class="currentPage">${sessionScope.pageIndex}</span>页
			<span class="hidden">${sessionScope.totalPage}</span>
			<a href="#" onclick="firstPage1(this);">首页</a>
			<a href="#" onclick="prePage1(this);">上一页</a>
			<a href="#" onclick="nextPage1(this);">下一页</a>
			<a href="#" onclick="lastPage1(this);">尾页</a>
		</c:if>
	</div>
	
</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/replyList.js"></script>
</html>