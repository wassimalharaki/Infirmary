package com.example.infirmary.Controllers;

import com.example.infirmary.Objects.Instructor;
import com.example.infirmary.Objects.Patient;
import com.example.infirmary.Objects.Student;
import com.example.infirmary.Objects.Treatment;
import com.example.infirmary.Utils.DBUtils;
import com.example.infirmary.Utils.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTreatmentController implements Initializable {

	@FXML
	private HBox hbox_student, hbox_instructor;

	@FXML
	private RadioButton rbtn_instructor, rbtn_student;

	@FXML
	private Button btn_add, btn_back;

	@FXML
	private TextArea txt_reason;

	@FXML
	private TextField txt_fieldOfWork, txt_fname, txt_grade,
			txt_id, txt_lname, txt_medication, txt_section;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btn_back.setOnAction(event -> {
			Util.changeScene("home.fxml");
		});

		rbtn_student.setOnAction(event -> {
			hbox_student.setVisible(true);
			hbox_instructor.setVisible(false);
		});

		rbtn_instructor.setOnAction(event -> {
			hbox_student.setVisible(false);
			hbox_instructor.setVisible(true);
		});

		btn_add.setOnAction(event -> {
			addTreatment();
		});
	}

	public void addTreatment() {
		String fname = txt_fname.getText().trim();
		String lname = txt_lname.getText().trim();
		String id = txt_id.getText().trim();

		String medication = txt_medication.getText().trim();
		String reason = txt_reason.getText().trim();

		String grade = txt_grade.getText().trim();
		String section = txt_section.getText().trim();
		String fieldOfWork = txt_fieldOfWork.getText().trim();

		//validations
		if (
				fname.isEmpty()
				|| lname.isEmpty()
				|| id.isEmpty()
				|| medication.isEmpty()
				|| reason.isEmpty()
		) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Please fill out all fields.");
			alert.show();
			return;
		}

		try {
			Integer.parseInt(id);
		}
		catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Invalid ID.");
			alert.show();
			return;
		}

		if (rbtn_student.isSelected()) {
			if (grade.isEmpty() || section.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Please fill out all fields.");
				alert.show();
				return;
			}

			try {
				Integer.parseInt(grade);
			}
			catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Invalid grade.");
				alert.show();
				return;
			}

			if (section.length() != 1 || !Character.isLetter(section.charAt(0))) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Invalid section.");
				alert.show();
				return;
			}
		}
		else if (fieldOfWork.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Please fill out all fields.");
			alert.show();
			return;
		}
		//end validations

		Patient p;
		String info;
		if (rbtn_student.isSelected()) {
			p = new Student(Integer.parseInt(id), fname, lname, Integer.parseInt(grade), section.charAt(0));
			info = "Grade: " + grade + section;
		}
		else {
			p = new Instructor(Integer.parseInt(id), fname, lname, fieldOfWork);
			info = "Field Of Work: " + fieldOfWork;
		}

		Treatment t = new Treatment(p, medication, reason);
		DBUtils.insertTreatment(id, fname, lname, t.getDate(), medication, reason, info);

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("SUCCESS");
		alert.show();
	}
}
