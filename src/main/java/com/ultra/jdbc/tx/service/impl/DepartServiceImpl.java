package com.ultra.jdbc.tx.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.ultra.jdbc.pojo.Depart;
import com.ultra.jdbc.tx.dao.DepartDao;
import com.ultra.jdbc.tx.dao.impl.DepartDaoImpl;
import com.ultra.jdbc.tx.service.DepartService;
import com.ultra.jdbc.util.JdbcC3P0PoolUtil;
import com.ultra.jdbc.util.SqlUtil;

public class DepartServiceImpl implements DepartService {

	private DepartDao departDao = new DepartDaoImpl();

	@Override
	public void addDeparts(List<Depart> departs) throws Exception {
		if (departs == null || departs.size() == 0)
			return;
		Connection con = null;
		Object[][] args = fillArgs(departs);
		try {
			con = getCon();
			con.setAutoCommit(false);
			departDao.addBatch(con, SqlUtil.DEPART_INSERT_SQL, args);
			// int i = 10 / 0;
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new Exception(e);
		}

	}

	private Connection getCon() throws SQLException {
		return JdbcC3P0PoolUtil.getCon();
	}

	@Override
	public void addDepart(Depart depart) throws Exception {
		if (depart == null) {
			return;
		}
		System.out.println("departService: " + depart.hashCode());
		Connection con = null;
		try {
			con = getCon();
			con.setAutoCommit(false);
			departDao.add(con, SqlUtil.DEPART_INSERT_SQL, depart.getName());
			// int i = 10 / 0;
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new Exception(e);
		}
	}

	@Override
	public void updateDeparts(List<Depart> departs) throws Exception {
		if (departs == null || departs.size() == 0)
			return;
		Object[][] args = new Object[departs.size()][];
		for (int i = 0; i < departs.size(); i++) {
			args[i] = new Object[] { departs.get(i).getName(), departs.get(i).getId() };
		}
		Connection con = null;
		try {
			con = getCon();
			con.setAutoCommit(false);
			departDao.updateBatch(con, SqlUtil.DEPART_UPDATE_SQL, args);
			// int i = 10 / 0;
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception(e);
		}
	}

	@Override
	public void updateDepart(Depart depart) throws Exception {
		if (depart == null) {
			return;
		}
		Connection con = null;
		try {
			con = getCon();
			con.setAutoCommit(false);
			departDao.update(con, SqlUtil.DEPART_UPDATE_SQL, depart.getName(), depart.getId());
			// int i = 10 / 0;
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception(e);
		}
	}

	@Override
	public void deleteDeparts(List<Integer> ids) throws Exception {
		if (ids == null || ids.size() == 0)
			return;
		Connection con = null;
		Object[][] args = new Object[ids.size()][];
		for (int i = 0; i < ids.size(); i++) {
			args[i] = new Object[] { ids.get(i) };
		}
		try {
			con = getCon();
			con.setAutoCommit(false);
			departDao.updateBatch(con, SqlUtil.DEPART_DELETE_SQL, args);
			// int i = 10 / 0;
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception(e);
		}
	}

	@Override
	public void deleteDepart(int id) throws Exception {
		Connection con = null;
		try {
			con = getCon();
			con.setAutoCommit(false);
			departDao.update(con, SqlUtil.DEPART_DELETE_SQL, id);
			// int i = 10 / 0;
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception(e);
		}
	}

	@Override
	public List<Depart> getAllDeparts() throws Exception {
		Connection con = null;
		con = getCon();
		return departDao.getAll(con, SqlUtil.DEPART_SELECT_ALL_SQL);
	}

	@Override
	public Depart getDepartById(int id) throws Exception {
		Connection con = null;
		con = getCon();
		return departDao.getById(con, SqlUtil.DEPART_SELECT_SQL, id);
	}

	private Object[][] fillArgs(List<Depart> departs)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object[][] args;
		args = new Object[departs.size()][];
		for (int i = 0; i < departs.size(); i++) {
			args[i] = BeanUtils.getArrayProperty(departs.get(i), "name");
		}
		return args;
	}

}
