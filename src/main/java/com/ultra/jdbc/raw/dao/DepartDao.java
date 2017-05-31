package com.ultra.jdbc.raw.dao;

import java.sql.SQLException;
import java.util.List;

import com.ultra.jdbc.pojo.Depart;

public interface DepartDao {

	public List<Depart> getAll() throws SQLException;

	public Depart getById(Integer id) throws SQLException;

	public boolean add(String name) throws SQLException;

	public boolean update(String name, Integer id) throws SQLException;

	public boolean del(Integer id) throws SQLException;
}
