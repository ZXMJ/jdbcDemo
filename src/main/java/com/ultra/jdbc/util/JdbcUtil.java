package com.ultra.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	private static Properties pro;
	private static String user;
	private static String password;
	private static String driverClass;
	private static String url;
	static {
		pro = new Properties();
		InputStream input = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			pro.load(input);
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			driverClass = pro.getProperty("driverClass");
			url = pro.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConByDriverManager() {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public Connection getConByReflectDriver() {
		try {
			Driver driver = (Driver) Class.forName(driverClass).newInstance();
			return driver.connect(url, pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description: 通过创建 Driver获取连接,缺点与集成的驱动耦合.
	 *
	 * @date 2017年5月23日,下午2:45:08
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 * @return
	 */
	public static Connection getConByDriver() {
		Properties pro = new Properties();
		String url = "jdbc:mysql:///test";
		String user = "root";
		String password = "fan123";
		pro.setProperty("user", user);
		pro.setProperty("password", password);
		Connection con = null;
		try {
			Driver driver = new com.mysql.jdbc.Driver();
			con = driver.connect(url, pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con, Statement statement, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
