package com.ultra.jdbc.qr.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ultra.jdbc.qr.dao.BaseDao;
import com.ultra.jdbc.util.JdbcUtil;

public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> type;

	public BaseDaoImpl() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type[] types = pt.getActualTypeArguments();
			this.type = (Class<T>) types[0];
		}
	}

	@Override
	public List<T> getAll(String sql, Object... args) throws Exception {
		QueryRunner qr = new QueryRunner();
		return qr.query(JdbcUtil.getConByDriverManager(), sql, new BeanListHandler<T>(type), args);
	}

	@Override
	public T getById(String sql, Object... args) throws Exception {
		QueryRunner qr = new QueryRunner();
		return qr.query(JdbcUtil.getConByDriverManager(), sql, new BeanHandler<T>(type), args);
	}

	@Override
	public boolean add(String sql, Object... args) throws Exception {
		QueryRunner qr = new QueryRunner();
		// 返回插入后的主键
		qr.insert(JdbcUtil.getConByDriverManager(), sql, new ScalarHandler<T>(), args);
		return true;
	}

	@Override
	public boolean update(String sql, Object... args) throws Exception {
		QueryRunner qr = new QueryRunner();
		// 返回值是影响的行数
		qr.update(JdbcUtil.getConByDriverManager(), sql, args);
		return true;
	}

	@Override
	public boolean del(String sql, Object... args) throws Exception {
		QueryRunner qr = new QueryRunner();
		// 返回值是影响的行数
		qr.update(JdbcUtil.getConByDriverManager(), sql, args);
		return true;
	}

}
