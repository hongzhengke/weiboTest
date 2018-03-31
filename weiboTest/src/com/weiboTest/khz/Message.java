package com.weiboTest.khz;

import java.io.BufferedWriter;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Message
 */
@WebServlet("/message.do")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String USERS = "C:/Users/khz/git/weiboTest/users/";
    private final String ERROR_VIEW = "error.view.jsp";
    private final String SUCCESS_VIEW = "member.view.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String)(request.getSession().getAttribute("login"));
		List<String>errors = new ArrayList<>();
		if(username == null) {
			errors.add("未登录！非法操作！");
			response.sendRedirect(ERROR_VIEW);
		}
		request.setCharacterEncoding("utf-8");
		String message = request.getParameter("message");
		if(message == null || message.length() > 160) {
			errors.add("不合格的字数");
			response.sendRedirect(ERROR_VIEW);
		}
		writeMessage(username, message);
		response.sendRedirect(SUCCESS_VIEW);
	}
	
	private void writeMessage(String username,String message) throws IOException {
		Date nowTime = new Date();
		BufferedWriter writer = new BufferedWriter(new FileWriter(USERS + username + "/" + nowTime.getTime() + ".txt"));
		writer.write(message);
		writer.close();
	}
}
