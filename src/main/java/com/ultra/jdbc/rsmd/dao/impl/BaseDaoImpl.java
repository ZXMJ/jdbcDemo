package com.ultra.jdbc.rsmd.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ultra.jdbc.rsmd.dao.BaseDao;
import com.ultra.jdbc.util.JdbcUtil;

public class BaseDaoImpl<T> implements BaseDao<T> {

	// private
	@Override
	public List<T> getAll(Class<T> clazz, String sql, Object... args) throws Exception {
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		opreatePreparedStatement(ps, args);
		ResultSet rs = ps.executeQuery();
		List<T> entities = new ArrayList<T>();
		while (rs.next()) {
			T entity = opreateEntity(clazz, rs);
			entities.add(entity);
		}
		return entities;
	}

	@Override
	public T getById(Class<T> clazz, String sql, Object... args) throws Exception {
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		opreatePreparedStatement(ps, args);
		ResultSet rs = ps.executeQuery();
		T entity = null;
		while (rs.next()) {
			entity = opreateEntity(clazz, rs);
		}
		return entity;
	}

	@Override
	public boolean add(String sql, Object... args) throws Exception {
		return opreate(sql, args);
	}

	@Override
	public boolean update(String sql, Object... args) throws Exception {
		return opreate(sql, args);
	}

	@Override
	public boolean del(String sql, Object... args) throws Exception {
		return opreate(sql, args);
	}

	private boolean opreate(String sql, Object... args) throws SQLException {
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		opreatePreparedStatement(ps, args);
		return ps.executeUpdate() == 1 ? true : false;
	}

	private void opreatePreparedStatement(PreparedStatement ps, Object... args) throws SQLException {
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
		}
	}

	private T opreateEntity(Class<T> clazz, ResultSet rs)
			throws InstantiationException, IllegalAccessException, SQLException, NoSuchFieldException {
		T entity;
		entity = clazz.newInstance();
		ResultSetMetaData rsmd = rs.getMetaData();
		int num = rsmd.getColumnCount();
		for (int i = 0; i < num; i++) {
			String lable = rsmd.getColumnLabel(i + 1);
			Object val = rs.getObject(lable);
			Field field = entity.getClass().getDeclaredField(lable);
			field.setAccessible(true);
			field.set(entity, val);
		}
		return entity;
	}
}
