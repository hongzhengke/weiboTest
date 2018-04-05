package com.weiboTest.dao;

import java.util.Date;
import java.util.List;

import com.weiboTest.bean.WeiboBean;

public interface WeiboDao {
	List<WeiboBean> getWeibos(String username);
	void addWeibo(WeiboBean weibo);
	void deleteWeibo(String username,Date date);
}
