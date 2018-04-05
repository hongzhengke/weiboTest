package com.weiboTest.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weiboTest.bean.WeiboBean;
import com.weiboTest.service.UserService;

/**
 * Servlet implementation class User
 */
@WebServlet(
		urlPatterns = {"/user/*"},
		initParams = {
				@WebInitParam(name = "SUCCESS_VIEW",value = "/user.view.jsp"),
				@WebInitParam(name = "ERROR_VIEW",value = "index.jsp")
		}
)
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;
    public void init() {
    	SUCCESS_VIEW = super.getServletConfig().getInitParameter("SUCCESS_VIEW");
    	ERROR_VIEW = super.getServletConfig().getInitParameter("ERROR_VIEW");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getPathInfo().substring(1);
		UserService userService = (UserService)request.getServletContext().getAttribute("userService");
		if(userService.isValidUsername(username) == true) {
			response.sendRedirect(ERROR_VIEW);
		}
		else {
			List<WeiboBean>weibos = userService.getWeibos(username);
			request.setAttribute("weibos", weibos);
			request.setAttribute("username", username);
			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
