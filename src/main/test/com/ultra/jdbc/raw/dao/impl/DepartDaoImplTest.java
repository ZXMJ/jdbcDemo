package com.ultra.jdbc.raw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.ultra.jdbc.pojo.Depart;
import com.ultra.jdbc.raw.dao.DepartDao;

public class DepartDaoImplTest {

	private static DepartDao departDao = new DepartDaoImpl();

	@Test
	public void testGetAll() {
		try {
			List<Depart> departs = departDao.getAll();
			System.out.println(departs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetById() throws SQLException {
		Depart depart = departDao.getById(1);
		System.out.println(depart);
	}

	@Test
	public void testAdd() throws SQLException {
		boolean result = departDao.add("depart--CC");
		System.out.println(result);
	}

	@Test
	public void testUpdate() throws SQLException {
		boolean result = departDao.update("BB", 2);
		System.out.println(result);
	}

	@Test
	public void testDel() throws SQLException {
		boolean result = departDao.del(2);
		System.out.println(result);
	}

	public static void main(String[] args) {
		Thread1 target = new Thread1(departDao, 2);
		Thread th1 = new Thread(target);
		Thread th2 = new Thread(target);
		th1.start();
		th2.start();
	}
}

class Thread1 implements Runnable {

	DepartDao departDao;
	Integer i;

	public Thread1(DepartDao departDao, int i) {
		this.departDao = departDao;
		this.i = i;
	}

	public void run() {
		synchronized (this) {
			for (; i < 20; i++) {
				try {
					boolean depart = departDao.add("depart--" + i);
					System.out.println(depart);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}