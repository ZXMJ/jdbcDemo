package com.ultra.jdbc.tx.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ultra.jdbc.tx.dao.Dao;
import com.ultra.jdbc.util.ReflectUtil;

public class DaoImpl<T> implements Dao<T> {

	private QueryRunner qr = new QueryRunner();
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public DaoImpl() {
		clazz = (Class<T>) ReflectUtil.getGenericSuperClass(this.getClass());
		System.out.println("DaoImpl Contructor...");
	}

	@Override
	public <D> D getScalar(Connection con, String sql, Object... args) throws SQLException {
		return qr.query(con, sql, new ScalarHandler<D>(), args);
	}

	@Override
	public T getById(Connection con, String sql, Object... args) throws SQLException {
		return qr.query(con, sql, new BeanHandler<>(clazz), args);
	}

	@Override
	public List<T> getAll(Connection con, String sql) throws SQLException {
		return qr.query(con, sql, new BeanListHandler<>(clazz));
	}

	@Override
	public List<T> getAll(Connection con, String sql, Object... args) throws SQLException {
		return qr.query(con, sql, new BeanListHandler<>(clazz), args);
	}

	@Override
	public boolean add(Connection con, String sql, Object... args) throws SQLException {
		qr.insert(con, sql, new ScalarHandler<>(), args);
		return true;
	}

	@Override
	public boolean addBatch(Connection con, String sql, Object[]... args) throws SQLException {
		qr.insertBatch(con, sql, new ScalarHandler<>(), args);
		return true;
	}

	@Override
	public boolean update(Connection con, String sql, Object... args) throws SQLException {
		qr.update(con, sql, args);
		return true;
	}

	@Override
	public boolean updateBatch(Connection con, String sql, Object[]... args) throws SQLException {
		qr.batch(con, sql, args);
		return true;
	}

}
