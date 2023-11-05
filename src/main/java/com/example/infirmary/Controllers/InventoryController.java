package com.example.infirmary.Controllers;

import com.example.infirmary.Main;
import com.example.infirmary.Objects.Medication;
import com.example.infirmary.Utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

	@FXML
	TableView tbl_inventory;

	@FXML
	Button btn_back, btn_addMedication, btn_search;

	@FXML
	TextField txt_name, txt_expiryDate, txt_stock;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tbl_inventory.setPlaceholder(new Label("No Results"));

		btn_back.setOnAction(event -> {
			Util.changeScene("home.fxml");
		});

		btn_addMedication.setOnAction(event -> {
			addItem();
		});

		btn_search.setOnAction(event -> {
			search();
		});

		fillTable(Main.inventory);
	}

	public void search() {
		String name = txt_name.getText().trim();

		if (name.isEmpty()) {
			fillTable(Main.inventory);
			return;
		}

		Medication find = Medication.find(name);
		ArrayList<Medication> meds = new ArrayList<>();

		if (find != null)
			meds.add(find);
		fillTable(meds);
	}

	public void addItem() {
		String name = txt_name.getText().trim();
		String expiryDate = txt_expiryDate.getText().trim();
		String stock = txt_stock.getText().trim();

		if (
				name.isEmpty()
				|| expiryDate.isEmpty()
				|| stock.isEmpty()
		) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Please fill out all fields.");
			alert.show();
			return;
		}

		if (
				name.contains(" ")
						|| expiryDate.contains(" ")
						|| stock.contains(" ")
		) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Fields cannot contain spaces.");
			alert.show();
			return;
		}

		try {
			Integer.parseInt(stock);
		}
		catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Invalid stock amount.");
			alert.show();
			return;
		}

		Medication.addMedication(name, expiryDate, Integer.parseInt(stock));

		fillTable(Main.inventory);
	}

	public void fillTable(ArrayList<Medication> inventory) {
		tbl_inventory.getColumns().clear();
		tbl_inventory.getItems().clear();

		TableColumn<Map, String> c1 = new TableColumn<>("Name");
		c1.setCellValueFactory(new MapValueFactory<>("name"));
		c1.setSortable(false);
		TableColumn<Map, String> c2 = new TableColumn<>("Expiry Date");
		c2.setCellValueFactory(new MapValueFactory<>("expiryDate"));
		c2.setSortable(false);
		TableColumn<Map, String> c3 = new TableColumn<>("Stock");
		c3.setCellValueFactory(new MapValueFactory<>("stock"));
		c3.setSortable(false);
		TableColumn<Map, HBox> c4 = new TableColumn<>("Action");
		c4.setCellValueFactory(new MapValueFactory<>("container"));
		c4.setSortable(false);

		tbl_inventory.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tbl_inventory.getColumns().add(c1);
		tbl_inventory.getColumns().add(c2);
		tbl_inventory.getColumns().add(c3);
		tbl_inventory.getColumns().add(c4);
		tbl_inventory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		ObservableList<Map<String, Object>> items =
				FXCollections.observableArrayList();

		for (int i = 0; i < inventory.size(); i++) {
			final int pos = i;
			Medication m = inventory.get(i);

			HBox box_action = new HBox();
			box_action.setAlignment(Pos.CENTER);

			Button btn_add = new Button("+");
			btn_add.setTranslateX(-15);
			btn_add.setCursor(Cursor.HAND);
			btn_add.setOnAction(event -> {
				m.addStock(1);
				Map<String, Object> item = (Map<String, Object>) tbl_inventory.getItems().get(pos);
				item.put("stock", Integer.toString(m.getStock()));
				tbl_inventory.getItems().set(pos, item);
			});

			Button btn_remove = new Button("-");
			btn_remove.setTranslateX(15);
			btn_remove.setCursor(Cursor.HAND);
			btn_remove.setOnAction(event -> {
				m.addStock(-1);
				if (m.getStock() <= 5) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Low stock!");
					alert.show();
				}
				Map<String, Object> item = (Map<String, Object>) tbl_inventory.getItems().get(pos);
				item.put("stock", Integer.toString(m.getStock()));
				tbl_inventory.getItems().set(pos, item);
			});

			box_action.getChildren().addAll(btn_add, btn_remove);

			Map<String, Object> item = new HashMap<>();

			item.put("name", m.getName());
			item.put("expiryDate", m.getExpiryDate());
			item.put("stock", Integer.toString(m.getStock()));
			item.put("container", box_action);

			items.add(item);
		}

		tbl_inventory.getItems().addAll(items);
	}
}