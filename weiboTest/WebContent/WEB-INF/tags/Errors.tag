<%@ tag language="java" pageEncoding="utf-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name = "otherInfo" %>
失败<br>
<b>${otherInfo}<br>
<c:forEach var = "error" items = "${requestScope.errors}">
	${error}<br>
</c:forEach>