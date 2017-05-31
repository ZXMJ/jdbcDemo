package com.ultra.jdbc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class JdbcDBCPPoolUtil {
	private static DataSource dataSource;
	static {
		InputStream in = JdbcDBCPPoolUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
		Properties info = new Properties();
		try {
			info.load(in);
			dataSource = BasicDataSourceFactory.createDataSource(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() throws SQLException {
		return dataSource.getConnection();
	}
}
