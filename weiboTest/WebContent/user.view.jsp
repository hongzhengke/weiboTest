<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@page import="com.weiboTest.bean.WeiboBean"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${username}的微博</title>
</head>
<body>

<c:forEach var = "weibo" items = "${weibos}">
	${weibo.message}<br>
	<fmt:formatDate value = "${weibo.date}" type = "both" dateStyle = "full" timeStyle = "full" />
</c:forEach>
<%-- <%
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
	List<WeiboBean>weibos = (List<WeiboBean>)request.getAttribute("weibos");
	for(WeiboBean weibo:weibos){
%>		<%= weibo.getMessage() %><br>
		<%= dateFormat.format(weibo.getDate()) %><br>
<% 
	}
%> --%>
</body>
</html>