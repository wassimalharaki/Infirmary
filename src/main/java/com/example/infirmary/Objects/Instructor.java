package com.example.infirmary.Objects;

public class Instructor extends Patient {
	private String fieldOfWork;
	public Instructor(int id, String firstName, String lastName, String fieldOfWork) {
		super(id, firstName, lastName);
		this.fieldOfWork = fieldOfWork;
	}

	public String getFieldOfWork() {
		return fieldOfWork;
	}
}
