package com.weiboTest.khz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete.do")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ERROR_VIEW = "error.view.jsp";
	private final String USERS = "C:/Users/khz/git/weiboTest/users/";
	private final String SUCCESS_VIEW = "member.view.jsp";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("login");
		List<String>errors = new ArrayList<>();
		if(username == null) {
			errors.add("非法操作！");
			request.setAttribute("errors", errors);
			response.sendRedirect(ERROR_VIEW);
			return;
		}
		File file = new File(USERS + username + "/" + request.getParameter("date") + ".txt");
		response.sendRedirect(SUCCESS_VIEW);
	}

}
