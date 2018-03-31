<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错误</title>
</head>
<body>
	<b>失败！<br>
	<%
		List<String>errors = (List<String>)request.getAttribute("errors");
		for(String error:errors){
			out.println(error);
		}
	%>
	<a href = "index.html">返回主页</a>
	
</body>
</html>