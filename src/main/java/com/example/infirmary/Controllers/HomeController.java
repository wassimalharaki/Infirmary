package com.example.infirmary.Controllers;

import com.example.infirmary.Utils.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

	@FXML
	private Button btn_inventory;

	@FXML
	private Button btn_newTreatment;

	@FXML
	private Button btn_viewTreatments;
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btn_inventory.setOnAction(event -> {
			Util.changeScene("inventory.fxml");
		});

		btn_newTreatment.setOnAction(event -> {
			Util.changeScene("addtreatment.fxml");
		});

		btn_viewTreatments.setOnAction(event -> {
			Util.changeScene("viewtreatments.fxml");
		});
	}
}
