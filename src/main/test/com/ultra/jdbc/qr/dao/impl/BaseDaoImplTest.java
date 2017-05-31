package com.ultra.jdbc.qr.dao.impl;

import org.junit.Test;

import com.ultra.jdbc.qr.dao.DepartDao;

public class BaseDaoImplTest {

	private DepartDao departDao = new DepartDaoImpl();

	@Test
	public void testGetAll() throws Exception {
		String sql = "select depart_id id,depart_name name from depart";
		System.out.println(departDao.getAll(sql, null));
	}

	@Test
	public void testGetById() throws Exception {
		String sql = "select depart_id id,depart_name name from depart where depart_id =?";
		System.out.println(departDao.getById(sql, 1));
	}

	@Test
	public void testAdd() throws Exception {
		String sql = "insert into depart  (depart_name) values (?)";
		System.out.println(departDao.add(sql, "depart--BB"));
	}

	@Test
	public void testUpdate() throws Exception {
		String sql = "update depart set depart_name = ? where depart_id > ?";
		System.out.println(departDao.update(sql, "depart--CC", 1));
	}

	@Test
	public void testDel() throws Exception {
		String sql = "delete from depart where depart_id > ?";
		System.out.println(departDao.update(sql, 1));
	}

}
