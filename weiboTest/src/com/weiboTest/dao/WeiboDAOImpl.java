package com.weiboTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.weiboTest.bean.AccountBean;
import com.weiboTest.bean.WeiboBean;

public class WeiboDAOImpl implements WeiboDao{
	private DataSource dataSource;
	public WeiboDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<WeiboBean> getWeibos(String username) {
		Connection connection = null;
		PreparedStatement statement = null;
		SQLException ex = null;
		List<WeiboBean>weibos = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select * from weibo where username = ?");
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				WeiboBean weibo = new WeiboBean(rs.getString("username"), rs.getTimestamp("date"), rs.getString("message"));
				weibos.add(weibo);
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
		return weibos;
	}

	@Override
	public void addWeibo(WeiboBean weibo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("insert into weibo(username,date,message) values(?,?,?)");
			stmt.setString(1, weibo.getUsername());
			stmt.setTimestamp(2, new Timestamp(weibo.getDate().getTime()));
			stmt.setString(3, weibo.getMessage());
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
	public void deleteWeibo(String username, Date date) {
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("delete from weibo where username = ? and date = ?");
			stmt.setString(1, username);
			stmt.setTimestamp(2, new Timestamp(date.getTime()));
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
	
}
