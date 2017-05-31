package com.ultra.jdbc.pojo;

public class Depart {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Depart [id=" + id + ", name=" + name + "]";
	}

	public Depart() {
	}

	public Depart(String name) {
		super();
		this.name = name;
	}

	public Depart(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
