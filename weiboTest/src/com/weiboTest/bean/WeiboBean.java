package com.weiboTest.bean;

import java.io.Serializable;
import java.util.*;

public class WeiboBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private Date date;
	private String message;
	
	public WeiboBean(String username,Date date,String message) {
		this.username = username;
		this.date = date;
		this.message = message;
	}
	
	public WeiboBean() {
		this(null, null, null);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
