<%@page import="com.weiboTest.bean.WeiboBean"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员主页</title>
</head>
<body>
<a href = "logout.do">注销</a><br>

写点什么吧,140字以内<br>
<form action = "message.do" method = "post">
	<textarea col = "60" rows = "4" name = "message"></textarea><br>
	<button type = "submit">发表</button><br>
</form>
<c:forEach var = "weibo" items = "${weibos}">
	${weibo.message }<br>
	<fmt:formatDate value = "${weibo.date}" type="both" dateStyle = "full" timeStyle = "full"/>
	<a href = "delete.do?date=${weibo.date.time}">删除</a><br>
</c:forEach>

</body>
</html>