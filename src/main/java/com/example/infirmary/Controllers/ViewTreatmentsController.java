package com.example.infirmary.Controllers;

import com.example.infirmary.Utils.DBUtils;
import com.example.infirmary.Utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewTreatmentsController implements Initializable {

	@FXML
	private Button btn_searchId, btn_searchName, btn_back;

	@FXML
	private TableView tbl_treatments;

	@FXML
	private TextField txt_search;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tbl_treatments.setPlaceholder(new Label("No Results"));

		btn_back.setOnAction(event -> {
			Util.changeScene("home.fxml");
		});

		btn_searchId.setOnAction(event -> {
			String id = txt_search.getText().trim();
			if (id.isEmpty()) {
				fillAllTable();
				return;
			}
			try {
				fillTable(DBUtils.getTreatmentsByID(id));
			}
			catch (SQLException e) {
				System.out.println(e);
			}
		});

		btn_searchName.setOnAction(event -> {
			String name = txt_search.getText().trim();
			if (name.isEmpty()) {
				fillAllTable();
				return;
			}

			try {
				fillTable(DBUtils.getTreatmentsByName(name));
			}
			catch (SQLException e) {
				System.out.println(e);
			}
		});

		fillAllTable();
	}

	public void fillAllTable() {
		try {
			fillTable(DBUtils.getAllTreatments());
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void fillTable(ResultSet rs) throws SQLException {
		tbl_treatments.getColumns().clear();
		tbl_treatments.getItems().clear();

		TableColumn<Map, String> c1 = new TableColumn<>("ID");
		c1.setCellValueFactory(new MapValueFactory<>("id"));
		c1.setPrefWidth(50);
		TableColumn<Map, String> c2 = new TableColumn<>("Name");
		c2.setCellValueFactory(new MapValueFactory<>("name"));
		c2.setPrefWidth(100);
		TableColumn<Map, String> c3 = new TableColumn<>("Medication");
		c3.setCellValueFactory(new MapValueFactory<>("medication"));
		c3.setPrefWidth(100);
		TableColumn<Map, String> c4 = new TableColumn<>("Reason");
		c4.setCellValueFactory(new MapValueFactory<>("reason"));
		c4.setPrefWidth(200);
		TableColumn<Map, String> c5 = new TableColumn<>("Extra Info");
		c5.setCellValueFactory(new MapValueFactory<>("info"));
		c5.setPrefWidth(100);
		TableColumn<Map, String> c6 = new TableColumn<>("Date");
		c6.setCellValueFactory(new MapValueFactory<>("date"));
		c6.setPrefWidth(150);

		tbl_treatments.getColumns().add(c1);
		tbl_treatments.getColumns().add(c2);
		tbl_treatments.getColumns().add(c3);
		tbl_treatments.getColumns().add(c4);
		tbl_treatments.getColumns().add(c5);
		tbl_treatments.getColumns().add(c6);

		ObservableList<Map<String, String>> items =
				FXCollections.observableArrayList();

		if (rs == null)
			return;

		while (rs.next()) {
			String name = rs.getString("patient_fname") + " "
					+ rs.getString("patient_lname");
			int id = rs.getInt("patient_id");
			String date = rs.getString("date");
			String medication = rs.getString("medication");
			String reason = rs.getString("reason");
			String info = rs.getString("info");

			HashMap<String, String> item = new HashMap<>();

			item.put("id", Integer.toString(id));
			item.put("name", name);
			item.put("medication", medication);
			item.put("reason", reason);
			item.put("info", info);
			item.put("date", date);

			items.add(item);
		}

		tbl_treatments.getItems().addAll(items);
	}
}