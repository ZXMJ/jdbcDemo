package com.ultra.jdbc.raw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ultra.jdbc.pojo.Depart;
import com.ultra.jdbc.raw.dao.DepartDao;
import com.ultra.jdbc.util.JdbcUtil;

public class DepartDaoSyncImpl implements DepartDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public synchronized List<Depart> getAll() throws SQLException {
		String sql = "select depart_id,depart_name from depart";
		con = JdbcUtil.getConByDriverManager();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		List<Depart> departs = new ArrayList<Depart>();
		Depart depart = null;
		while (rs.next()) {
			depart = new Depart();
			depart.setId(rs.getInt(1));
			depart.setName(rs.getString(2));
			departs.add(depart);
		}
		JdbcUtil.close(con, ps, rs);
		return departs;
	}

	@Override
	public synchronized Depart getById(Integer id) throws SQLException {
		String sql = "select depart_name from depart where depart_id = ?";
		con = JdbcUtil.getConByDriverManager();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		Depart depart = null;
		while (rs.next()) {
			depart = new Depart();
			depart.setId(id);
			depart.setName(rs.getString(1));
		}
		JdbcUtil.close(con, ps, rs);
		return depart;
	}

	@Override
	public synchronized boolean add(String name) throws SQLException {
		// 在方法内部定义变量可以避免本类的高并发引起资源公用引起的线程问题.
		String sql = "insert into depart (depart_name) values (?)";
		con = JdbcUtil.getConByDriverManager();
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		boolean result = ps.executeUpdate() == 1 ? true : false;
		JdbcUtil.close(con, ps, null);
		return result;
	}

	@Override
	public synchronized boolean update(String name, Integer id) throws SQLException {
		String sql = "update depart set depart_name = ? where depart_id = ?";
		con = JdbcUtil.getConByDriverManager();
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, id);
		boolean result = ps.executeUpdate() == 1 ? true : false;
		JdbcUtil.close(con, ps, null);
		return result;
	}

	@Override
	public synchronized boolean del(Integer id) throws SQLException {
		String sql = "delete from  depart where depart_id = ?";
		con = JdbcUtil.getConByDriverManager();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		boolean result = ps.executeUpdate() == 1 ? true : false;
		JdbcUtil.close(con, ps, null);
		return result;
	}

}
