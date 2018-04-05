package com.weiboTest.listener;

import javax.naming.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.weiboTest.dao.AccountDAOImpl;
import com.weiboTest.dao.WeiboDAOImpl;
import com.weiboTest.service.UserService;

/**
 * Application Lifecycle Listener implementation class weiboListener
 *
 */
@WebListener
public class weiboListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         try {
        	 Context initContext = new InitialContext();
        	 Context envContext = (Context)initContext.lookup("java:/comp/env");
        	 DataSource dataSource = (DataSource)envContext.lookup("jdbc/weiboTest");
        	 ServletContext context = sce.getServletContext();
        	 context.setAttribute("userService", new UserService(new AccountDAOImpl(dataSource), new WeiboDAOImpl(dataSource)));
         }catch(NamingException ex) {
        	 throw new RuntimeException(ex);
         }
    }
	
}
