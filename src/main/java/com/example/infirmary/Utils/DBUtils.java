package com.example.infirmary.Utils;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;

import static com.example.infirmary.Main.stmt;

public class DBUtils {
	public static void setupTables() {
		try {
			stmt.execute(
				"CREATE TABLE `infirmary`.`treatments` " +
				"(`id` INT(11) NOT NULL AUTO_INCREMENT , " +
				"`patient_id` INT(11) NOT NULL , " +
				"`patient_fname` VARCHAR(50) NOT NULL , " +
				"`patient_lname` VARCHAR(50) NOT NULL , " +
				"`date` VARCHAR(50) NOT NULL , " +
				"`medication` VARCHAR(50) NOT NULL , " +
				"`reason` VARCHAR(100) NOT NULL , " +
				"`info` VARCHAR(50) NOT NULL , " +
				"PRIMARY KEY (`id`))"
			);

			insertTreatment("12345", "Emily", "Johnson", "2023-04-15 12:30:00 PM", "Ibuprofen", "Headache relief", "Grade: 12C");
			insertTreatment("67890", "Benjamin", "Davis", "2023-05-20 12:30:00 PM", "Amoxicillin", "Infection treatment", "Grade: 8A");
			insertTreatment("23456", "Olivia", "Smith", "2023-06-10 12:30:00 PM", "Lisinopril", "Blood pressure control", "Grade: 11B");
			insertTreatment("54321", "Liam", "Anderson", "2023-07-05 12:30:00 PM", "Aspirin", "Heart health", "Grade: 12C");
			insertTreatment("78901", "Ava", "Martinez", "2023-08-15 12:30:00 PM", "Loratadine", "Allergy relief", "Grade: 10B");
			insertTreatment("98765", "William", "Thompson", "2023-09-25 12:30:00 PM", "Metformin", "Diabetes management", "Grade: 9A");
			insertTreatment("11223", "Mia", "Hernandez", "2023-10-30 12:30:00 PM", "Atorvastatin", "Cholesterol control", "Grade: 11C");
			insertTreatment("45678", "James", "Harris", "2023-11-10 12:30:00 PM", "Omeprazole", "Acid reflux relief", "Grade: 12B");
			insertTreatment("56789", "Sophia", "Scott", "2023-12-05 12:30:00 PM", "Sertraline", "Depression treatment", "Grade: 12A");
			insertTreatment("34567", "Charlotte", "Adams", "2024-01-20 12:30:00 PM", "Acetaminophen", "Pain relief", "Grade: 10A");
		}
		catch (SQLSyntaxErrorException sExc) {
			System.out.println("Table already created");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertTreatment(String id, String fname, String lname,
									   String date, String medication,
									   String reason, String info) {
		try {
			stmt.execute(
				"INSERT INTO `treatments` " +
				"(`id`, " +
				"`patient_id`, " +
				"`patient_fname`, " +
				"`patient_lname`, " +
				"`date`, " +
				"`medication`, " +
				"`reason`, " +
				"`info`) " +
				"VALUES " +
				"(NULL, " +
				"'" + id + "', " +
				"'" + fname + "', " +
				"'" + lname + "', " +
				"'" + date + "', " +
				"'" + medication + "', " +
				"'" + reason + "', " +
				"'" + info + "')"
			);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static ResultSet getAllTreatments() {
		try {
			return stmt.executeQuery("SELECT * FROM treatments");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static ResultSet getTreatmentsByID(String id) {
		try {
			return stmt.executeQuery("SELECT * FROM treatments WHERE patient_id = '" + id + "'");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static ResultSet getTreatmentsByName(String name) {
		try {
			return stmt.executeQuery("SELECT * FROM treatments " +
					"WHERE patient_fname = '" + name + "' OR patient_lname = '" + name + "'");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
