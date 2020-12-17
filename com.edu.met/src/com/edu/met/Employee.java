package com.edu.met;

public class Employee {
	
	private int id;
	private String name;
	private String designation;
	private String email;
	
	
	public Employee()
	{
		this.id=0;
		name=" ";
		designation="Programmer";
		email=" ";
		System.out.println("Employee instantiated");
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
