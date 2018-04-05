package com.weiboTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.weiboTest.bean.AccountBean;

public class AccountDAOImpl implements AccountDAO{
	private DataSource dataSource;
	
	public AccountDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean isUsernameExisted(String username) {
		return getAccount(username) != null;
	}

	@Override
	public void addAccount(AccountBean account) {
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("insert into account(username,password,email) values(?,?,?)");
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getEmail());
			stmt.executeUpdate();
		}catch (SQLException e) {
			ex = e;
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				
			}
		}
	}

	@Override
	public AccountBean getAccount(String username) {
		Connection connection = null;
		PreparedStatement statement = null;
		SQLException ex = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select * from account where username = ?");
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if(rs.next() == true) {
				return new AccountBean(rs.getString("username"), rs.getString("email"), rs.getString("password"));
			}
		}catch(SQLException e) {
			ex = e;
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
