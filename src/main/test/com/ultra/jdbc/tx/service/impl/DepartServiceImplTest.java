package com.ultra.jdbc.tx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ultra.jdbc.pojo.Depart;
import com.ultra.jdbc.tx.service.DepartService;

public class DepartServiceImplTest implements Runnable {

	private DepartService departService = new DepartServiceImpl();

	@Test
	public void testAddDeparts() throws Exception {
		List<Depart> departs = new ArrayList<>();
		Depart depart = new Depart("depart--BB");
		departs.add(depart);
		depart = new Depart("depart--CC");
		departs.add(depart);
		departService.addDeparts(departs);
	}

	@Test
	public void testAddDepart() throws Exception {
		Depart depart = new Depart("depart--DD");
		departService.addDepart(depart);
	}

	@Test
	public void testUpdateDeparts() throws Exception {
		List<Depart> departs = new ArrayList<>();
		Depart depart = new Depart(1, "depart--aa");
		departs.add(depart);
		depart = new Depart(2, "depart--bb");
		departs.add(depart);
		departService.updateDeparts(departs);
	}

	@Test
	public void testUpdateDepart() throws Exception {
		Depart depart = new Depart(3, "depart--cc");
		departService.updateDepart(depart);
	}

	@Test
	public void testDeleteDeparts() throws Exception {
		List<Integer> ids = new ArrayList<>();
		ids.add(3);
		ids.add(4);
		departService.deleteDeparts(ids);
	}

	@Test
	public void testDeleteDepart() throws Exception {
		departService.deleteDepart(2);
	}

	@Test
	public void testGetAllDeparts() throws Exception {
		System.out.println(departService.getAllDeparts());
	}

	@Test
	public void testGetDepartById() throws Exception {
		System.out.println(departService.getDepartById(2));
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Depart depart = new Depart();
				System.out.println("run: " + depart.hashCode());
				depart.setName(new Integer(i).toString() + "<-->" + depart.hashCode());
				departService.addDepart(depart);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}

	}

	public static void main(String[] args) {
		DepartServiceImplTest target = new DepartServiceImplTest();
		Thread th1 = new Thread(target);
		Thread th2 = new Thread(target);
		Thread th3 = new Thread(target);
		// Thread th4 = new Thread(target);
		// Thread th5 = new Thread(target);
		// Thread th6 = new Thread(target);
		// Thread th7 = new Thread(target);
		// Thread th8 = new Thread(target);
		// Thread th9 = new Thread(target);
		th1.start();
		th2.start();
		th3.start();
		// th4.start();
		// th5.start();
		// th6.start();
		// th7.start();
		// th8.start();
		// th9.start();
	}
}
