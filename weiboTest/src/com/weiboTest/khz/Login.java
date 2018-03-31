package com.weiboTest.khz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String USERS = "C:/Users/khz/git/weiboTest/users/";
	private final String SUCCESS_VIEW = "member.view.jsp";
	private final String ERROR_VIEW = "error.view.jsp"; 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(checkIn(username, password) == true) {
			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
		}
		else {
			List<String> errors = new ArrayList<>();
			errors.add("帐号或密码错误！");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
		}
	}
	
	private boolean checkIn(String username,String password) throws IOException {
		//这样子读文件也不是线程安全的，因为可能有人同时在进行注册
		for(String _username : new File(USERS).list()) {
			if(_username.equals(username)) {
				BufferedReader reader = new BufferedReader(new FileReader(USERS + _username + "/profile"));
				reader.readLine();
				String _password = reader.readLine();
				if(_password.equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
