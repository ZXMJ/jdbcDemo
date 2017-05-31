package com.ultra.jdbc.rsmd.dao;

import java.util.List;

public interface BaseDao<T> {

	public List<T> getAll(Class<T> clazz, String sql, Object... args) throws Exception;

	public T getById(Class<T> clazz, String sql, Object... args) throws Exception;

	public boolean add(String sql, Object... args) throws Exception;

	public boolean update(String sql, Object... args) throws Exception;

	public boolean del(String sql, Object... args) throws Exception;

}
