package com.weiboTest.servlet;

import java.io.File;
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
 * Servlet implementation class Delete
 */
@WebServlet(
		urlPatterns = {"/delete.do"},
		initParams = {
				@WebInitParam(name = "SUCCESS_VIEW",value = "member.view.jsp"),
				@WebInitParam(name = "ERROR_VIEW",value = "error.view.jsp")
		}
)
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ERROR_VIEW ;
	private String SUCCESS_VIEW ;
	
	@Override
	public void init() {
		ServletConfig config = super.getServletConfig();
		SUCCESS_VIEW = config.getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = config.getInitParameter("ERROR_VIEW");
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = (UserService)request.getServletContext().getAttribute("userService");
		String username = (String)request.getSession().getAttribute("login");
		String date = request.getParameter("date");
		userService.deleteMessage(username, date);
		response.sendRedirect(SUCCESS_VIEW);
	}

}
