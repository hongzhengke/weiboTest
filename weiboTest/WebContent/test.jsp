<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id = "now" class = "java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="true">
		<h1>${param.name }登录成功</h1>
	</c:if>
	<c:choose>
		<c:when test = "${param.name == 'khz'}">
			<h1>hello khz</h1>
		</c:when>
		<c:otherwise>
			<h1>hello others</h1>
		</c:otherwise>
	</c:choose>
	<fmt:formatDate value = "${now}" type = "both" dateStyle = "full" timeStyle = "full"/><br>
	<c:import url = "http://www.baidu.com" charEncoding = "UTF-8">
		
	</c:import>
</body>
</html>