package com.weiboTest.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weiboTest.service.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet(
		urlPatterns = {"/login.do"},
		initParams = {
				@WebInitParam(name = "SUCCESS_VIEW",value = "member.view.jsp"),
				@WebInitParam(name = "ERROR_VIEW",value = "error.view.jsp")
		}
)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String SUCCESS_VIEW ;
	private String ERROR_VIEW ;
	
	@Override
	public void init() {
		ServletConfig config = super.getServletConfig();
		SUCCESS_VIEW = config.getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = config.getInitParameter("ERROR_VIEW");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService userService = (UserService)request.getServletContext().getAttribute("userService");
		
		if(userService.checkIn(username, password) == true) {
			HttpSession session = request.getSession();
			session.setAttribute("login", username);
			response.sendRedirect(SUCCESS_VIEW);
		}
		else {
			List<String> errors = new ArrayList<>();
			errors.add("帐号或密码错误！");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
