package com.ultra.jdbc.tx.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	public List<T> getAll(Connection con, String sql) throws SQLException;

	public List<T> getAll(Connection con, String sql, Object... args) throws SQLException;

	public T getById(Connection con, String sql, Object... args) throws SQLException;

	public <D> D getScalar(Connection con, String sql, Object... args) throws SQLException;

	public boolean add(Connection con, String sql, Object... args) throws SQLException;

	public boolean addBatch(Connection con, String sql, Object[]... args) throws SQLException;

	// 包括修改和删除
	public boolean update(Connection con, String sql, Object... args) throws SQLException;

	public boolean updateBatch(Connection con, String sql, Object[]... args) throws SQLException;
}
