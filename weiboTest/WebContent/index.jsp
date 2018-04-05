<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<b>自行学习使用！</b><br>
<a href='register.html'>还不是会员？点击注册！</a><br>
<form action = "login.do" method = "post" />
	帐号：<input type = "text" name = "username" /><br>
	密码：<input type = "password" name = "password" /><br>
	<input type = "submit" value = "登录" /><br>
</form>
	<a href = 'forget.html'>忘记密码？</a><br><br>
	要查看别人的微博，请访问:http://localhost:8080/weiboTest/user/用户名<br>
	
下面是最近的微博<br>
<c:forEach var = "weibo" items = "${applicationScope.userService.newestWeibos}">
	${weibo}...<br>
</c:forEach>

</body>
</html>