package com.hmwl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 作用:向外提供Connection对象.
 * 关闭各种资源
 * */
public class ConnectionPool {
	private static DataSource dataSource = null;
	static {
		//1.去读配置文件
		InputStream in = ConnectionPool.class
			.getResourceAsStream("/jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			dataSource=DruidDataSourceFactory
						.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//静态方法用来向外提供Connection
	public static Connection getConnection() {
		Connection conn =null;
		try {
			conn =dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭各种资源
	public static void closeResources(ResultSet rs , Statement stm,Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stm!=null) {
				stm.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
