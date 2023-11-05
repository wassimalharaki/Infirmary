package com.example.infirmary.Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Treatment {
	private Patient patient;
	private LocalDateTime date;
	private String medication;
	private String reason;

	private static DateTimeFormatter formatter
			= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

	public Treatment(Patient patient, String medication, String reason) {
		this.patient = patient;
		this.medication = medication;
		this.reason = reason;
		date = LocalDateTime.now();
	}

	public Patient getPatient() {
		return patient;
	}

	public String getDate() {
		return date.format(formatter);
	}

	public String getMedication() {
		return medication;
	}

	public String getReason() {
		return reason;
	}
}
