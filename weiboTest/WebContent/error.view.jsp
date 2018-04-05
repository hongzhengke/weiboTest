<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix = "html" tagdir = "/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错误</title>
</head>
<body>
	<html:Errors otherInfo = "^-^"/>
	<%-- <b>失败！<br>
	<c:forEach var = "error" items = "${errors}">
		 ${error }<br>
	</c:forEach> --%>
	
	
<%-- 	<%
		List<String>errors = (List<String>)request.getAttribute("errors");
		for(String error:errors){
			%>
			<%=error %>
<% 
		}
	%> --%>
	<a href = "index.jsp">返回主页</a>
	
</body>
</html>