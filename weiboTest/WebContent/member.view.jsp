<%@page import="java.text.DateFormat"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员主页</title>
</head>
<body>
<%
	if(session == null || session.getAttribute("login") == null){
		response.sendRedirect("index.html");
		return;
	}
%>
<a href = "logout.do">注销</a><br>

写点什么吧,140字以内<br>
<form action = "message.do" method = "post">
	<textarea col = "60" rows = "4" name = "message"></textarea><br>
	<button type = "submit">发表</button><br>
</form>

<%!
	private final String USERS = "C:/Users/khz/git/weiboTest/users/";
	private class TxtFileNameFilter implements FilenameFilter{
	
		@Override
		public boolean accept(File dir,String name){
			return name.endsWith(".txt");
		}
	}
	
	private class reverseDateComparator implements Comparator<Date>{
		@Override
		public int compare(Date d1,Date d2){
			return -d1.compareTo(d2);
		}
	}
	
	private Map<Date,String> readMessage(String username) throws IOException{
		File dir = new File(USERS + username);
		TreeMap<Date,String>messageMap = new TreeMap<>(new reverseDateComparator());
		for(String filename: dir.list(new TxtFileNameFilter())){
			Date date = new Date(Long.parseLong(filename.substring(0,filename.indexOf(".txt"))));
			BufferedReader reader = new BufferedReader(new FileReader(USERS + username + "/" + filename));
			StringBuilder message = new StringBuilder();
			String tmp;
			while((tmp = reader.readLine()) != null){
				message.append(tmp);
				message.append('\n');
			}
			messageMap.put(date,message.toString());
			reader.close();
		}
		return messageMap;
	}
%>

<%
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
	String username = (String)(session.getAttribute("login"));
	Map<Date,String>messageMap = readMessage(username);
	for(Date date:messageMap.keySet()){
		out.println(messageMap.get(date));
%><br><% 
		out.print(dateFormat.format(date));
%>  <a href = "delete.do?date=<%out.print(date.getTime());%>">删除这条微博</a><br>
<% 
	}
%>

</body>
</html>