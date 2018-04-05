package com.weiboTest.dao;

import com.weiboTest.bean.AccountBean;

public interface AccountDAO {
	boolean isUsernameExisted(String username);
	void addAccount(AccountBean account);
	AccountBean getAccount(String username);
}
