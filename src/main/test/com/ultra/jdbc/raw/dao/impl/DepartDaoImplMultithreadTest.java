package com.ultra.jdbc.raw.dao.impl;

import java.sql.SQLException;

import com.ultra.jdbc.raw.dao.DepartDao;

public class DepartDaoImplMultithreadTest implements Runnable {

	int num = 2;
	private DepartDao departDao = new DepartDaoImpl();

	@Override
	public void run() {
		for (; num < 41; num++) {
			try {
				System.out.println("getAll: " + departDao.getAll());
				System.out.println("getById: " + departDao.getById(num));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DepartDaoImplMultithreadTest target = new DepartDaoImplMultithreadTest();
		Thread th1 = new Thread(target);
		Thread th2 = new Thread(target);
		th1.start();
		th2.start();
	}
}
