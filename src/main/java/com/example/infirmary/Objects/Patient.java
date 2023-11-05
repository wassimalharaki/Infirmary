package com.example.infirmary.Objects;

public class Patient {
	private int id;
	private String firstName;
	private String lastName;

	public Patient(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
