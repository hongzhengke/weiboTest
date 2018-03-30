package com.weiboTest.khz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 该版本暂时没有模型，此servlet临时也充当模型的功能
 */
@WebServlet("/register.do")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String USERS = "C:/Users/khz/git/weiboTest/users/";
    private final String SUCCESS_VIEW = "success.view.jsp";
    private final String ERROR_VIEW = "error.view.jsp";
    
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取得请求参数
		String email = request.getParameter("email");
		String username = request.getParameter("username");  
		String password = request.getParameter("password");
		String confirmedPasswd = request.getParameter("confirmedPasswd");
		
		List<String> errors = new ArrayList<>();
		if(!isValidEmail(email)) {
			errors.add("邮箱错误！");
		}
		else if(!isValidUsername(username)) {
			errors.add("用户名错误！");
		}
		else if(!isValidPassword(password, confirmedPasswd)) {
			errors.add("密码错误！");
		}
		String resultPage;
		if(errors.size() > 0) {
			resultPage = ERROR_VIEW;
			request.setAttribute("errors", errors);
		}
		else {
			resultPage = SUCCESS_VIEW;
			createUserData(email, username, password);  //这里不是线程安全的！
		}
		request.getRequestDispatcher(resultPage).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean isValidEmail(String email) {
		return Pattern.matches("[0-9a-zA-Z]+@[0-9a-zA-Z\\.]+\\.[a-zA-Z]+", email);
	}
	
	private boolean isValidUsername(String username) {
		if(username == null || username.length() > 16 || username.length() == 0)
			return false;
		
		String[] list = new File(USERS).list();
		for(String tmpUserName : list) {
			if(tmpUserName.equals(username))
				return false;
		}
		return true;
	}
	
	private boolean isValidPassword(String password,String confirmedPasswd) {
		if(!password.equals(confirmedPasswd))
			return false;
		if(password.length() < 6 || password.length() > 16)
			return false;
		return true;
	}
	
	private void createUserData(String email,String username,String password) throws IOException {
		File userhome = new File(USERS + username);
		userhome.mkdir();
		BufferedWriter writer = new BufferedWriter(new FileWriter(userhome.toString() + "/profile"));
		writer.write(email + '\t' + password);
		writer.close();
	}
	
	public static void main(String[] args) {
		File file = new File("users");
		file.mkdir();
	}
}
