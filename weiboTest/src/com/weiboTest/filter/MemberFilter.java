package com.weiboTest.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class MemberFilter
 */
@WebFilter(
		urlPatterns = {"/delete.do","/logout.do","/message.do","/member.view.jsp"},
		initParams = {@WebInitParam(name = "LOGIN_VIEW",value = "index.html")}
)
public class MemberFilter extends HttpFilter{
	private String LOGIN_VIEW;
	public void init(FilterConfig fConfig) throws ServletException {
		this.LOGIN_VIEW = fConfig.getInitParameter("LOGIN_VIEW");
	}


	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getSession().getAttribute("login") == null) {
			response.sendRedirect(LOGIN_VIEW);
		}
		else {
			chain.doFilter(request, response);
		}
	}

}
