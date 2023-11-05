package com.example.infirmary.Objects;

public class Student extends Patient {
	private int grade;
	private char section;

	public Student(int id, String firstName, String lastName, int grade, char section) {
		super(id, firstName, lastName);
		this.grade = grade;
		this.section = section;
	}

	public int getGrade() {
		return grade;
	}

	public char getSection() {
		return section;
	}
}
