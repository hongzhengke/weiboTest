package com.weiboTest.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weiboTest.service.UserService;



/**
 * 该版本暂时没有模型，此servlet临时也充当模型的功能
 */
@WebServlet(
		urlPatterns = {"/register.do"},
		initParams = {
				@WebInitParam(name = "SUCCESS_VIEW",value = "register.success.view.jsp"),
				@WebInitParam(name = "ERROR_VIEW",value = "error.view.jsp")
		}
)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String SUCCESS_VIEW;
    private String ERROR_VIEW ;
    
    @Override
	public void init() {
    	ServletConfig config = super.getServletConfig();
    	SUCCESS_VIEW = config.getInitParameter("SUCCESS_VIEW");
    	ERROR_VIEW = config.getInitParameter("ERROR_VIEW");
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取得请求参数
		String email = request.getParameter("email");
		String username = request.getParameter("username");  
		String password = request.getParameter("password");
		String confirmedPasswd = request.getParameter("confirmedPasswd");
		
		UserService userService = (UserService)request.getServletContext().getAttribute("userService");
		
		List<String> errors = new ArrayList<>();
		if(!isValidEmail(email)) {
			errors.add("邮箱错误！");
		}
		
		if(!userService.isValidUsername(username)) {
			errors.add("用户名错误！");
		}
		
		if(!isValidPassword(password, confirmedPasswd)) {
			errors.add("密码错误！");
		}
		String resultPage;
		if(errors.size() > 0) {
			resultPage = ERROR_VIEW;
			request.setAttribute("errors", errors);
		}
		else {
			resultPage = SUCCESS_VIEW;
			userService.createUserData(email, username, password);  //这里不是线程安全的！
		}
		request.getRequestDispatcher(resultPage).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean isValidPassword(String password,String confirmedPasswd) {
		if(!password.equals(confirmedPasswd))
			return false;
		if(password.length() < 6 || password.length() > 16)
			return false;
		return true;
	}
	
	private boolean isValidEmail(String email) {
		return Pattern.matches("[0-9a-zA-Z]+@[0-9a-zA-Z\\.]+\\.[a-zA-Z]+", email);
	}
}
