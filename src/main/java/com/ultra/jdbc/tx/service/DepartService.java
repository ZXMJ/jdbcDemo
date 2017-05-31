package com.ultra.jdbc.tx.service;

import java.util.List;

import com.ultra.jdbc.pojo.Depart;

public interface DepartService {

	public void addDeparts(List<Depart> departs) throws Exception;

	public void addDepart(Depart depart) throws Exception;

	public void updateDeparts(List<Depart> departs) throws Exception;

	public void updateDepart(Depart depart) throws Exception;

	public void deleteDeparts(List<Integer> ids) throws Exception;

	public void deleteDepart(int id) throws Exception;

	public List<Depart> getAllDeparts() throws Exception;

	public Depart getDepartById(int id) throws Exception;
}
