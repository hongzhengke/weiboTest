package com.weiboTest.servlet;

import java.io.BufferedWriter;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weiboTest.bean.WeiboBean;
import com.weiboTest.service.UserService;



@WebServlet(
		urlPatterns = {"/message.do"},
		initParams = {
				@WebInitParam(name = "SUCCESS_VIEW",value = "member.view.jsp"),
				@WebInitParam(name = "ERROR_VIEW",value = "error.view.jsp")
		}
)
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String ERROR_VIEW;
    private String SUCCESS_VIEW;
    
    @Override
    public void init() {
    	ServletConfig config = super.getServletConfig();
    	SUCCESS_VIEW = config.getInitParameter("SUCCESS_VIEW");
    	ERROR_VIEW = config.getInitParameter("ERROR_VIEW");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = (UserService)request.getServletContext().getAttribute("userService");
		String username = (String)(request.getSession().getAttribute("login"));
		String message = request.getParameter("message");
		
		if(message != null) {
			List<String>errors = new ArrayList<>();
			if(message == null || message.length() > 160) {
				errors.add("不合格的字数");
				response.sendRedirect(ERROR_VIEW);
			}
			userService.addWeibo(new WeiboBean(username,new Date(),message));
		}
		
		List<WeiboBean>weibos = userService.getWeibos(username);
		request.getSession().setAttribute("weibos", weibos);
		response.sendRedirect(SUCCESS_VIEW);
	}
	
}
