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

public class DepartDaoImpl implements DepartDao {

	@Override
	public List<Depart> getAll() throws SQLException {
		String sql = "select depart_id,depart_name from depart";
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
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
	public Depart getById(Integer id) throws SQLException {
		String sql = "select depart_name from depart where depart_id = ?";
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
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
	public boolean add(String name) throws SQLException {
		// 在方法内部定义变量可以避免本类的高并发问题.
		String sql = "insert into depart (depart_name) values (?)";
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		boolean result = ps.executeUpdate() == 1 ? true : false;
		JdbcUtil.close(con, ps, null);
		return result;
	}

	@Override
	public boolean update(String name, Integer id) throws SQLException {
		String sql = "update depart set depart_name = ? where depart_id = ?";
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, id);
		boolean result = ps.executeUpdate() == 1 ? true : false;
		JdbcUtil.close(con, ps, null);
		return result;
	}

	@Override
	public boolean del(Integer id) throws SQLException {
		String sql = "delete from  depart where depart_id = ?";
		Connection con = JdbcUtil.getConByDriverManager();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		boolean result = ps.executeUpdate() == 1 ? true : false;
		JdbcUtil.close(con, ps, null);
		return result;
	}

}