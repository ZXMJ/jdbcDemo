package com.ultra.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcC3P0PoolUtil {

	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource("c3p0");
	}

	public static Connection getCon() throws SQLException {
		return dataSource.getConnection();
	}
}
