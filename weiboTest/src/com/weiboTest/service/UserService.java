package com.weiboTest.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

import com.weiboTest.bean.AccountBean;
import com.weiboTest.bean.WeiboBean;
import com.weiboTest.dao.AccountDAO;
import com.weiboTest.dao.WeiboDao;

public class UserService {
	private AccountDAO accountDAO;
	private WeiboDao weiboDao;
	private LinkedList<WeiboBean> newestWeibos;
	
	public UserService(AccountDAO accountDAO,WeiboDao weiboDao) {
		this.accountDAO = accountDAO;
		this.weiboDao = weiboDao;
		newestWeibos = new LinkedList<>();
	}
	
	public boolean isValidUsername(String username) {
		if(username == null || username.length() > 16 || username.length() == 0)
			return false;
		
		if(accountDAO.isUsernameExisted(username))
			return false;
		else
			return true;
	}
	
	public void addAccount(String username,String password,String email) {
		accountDAO.addAccount(new AccountBean(username, email, password));
	}
	
	public boolean checkIn(String username,String password) throws IOException {
		AccountBean account = accountDAO.getAccount(username);
		if(account != null) {
			if(account.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	
	public List<WeiboBean> getWeibos(String username) throws IOException{
		return weiboDao.getWeibos(username);
	}
	
	public void addWeibo(WeiboBean weibo) throws IOException {
		weiboDao.addWeibo(weibo);
		newestWeibos.add(weibo);
	}
	
	public void deleteWeibo(String username,Date date) {
		newestWeibos.remove(new WeiboBean(username,date,""));
		weiboDao.deleteWeibo(username, date);
	}

	public LinkedList<WeiboBean> getNewestWeibos() {
		return newestWeibos;
	}

}
