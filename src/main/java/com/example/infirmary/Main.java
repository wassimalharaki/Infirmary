package com.example.infirmary;

import com.example.infirmary.Objects.Medication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.infirmary.Utils.Util.*;
import static com.example.infirmary.Utils.DBUtils.setupTables;

public class Main extends Application {

	//to store medication
	public static ArrayList<Medication> inventory = new ArrayList<>();

	public static Stage stage;

	static String URL = "jdbc:mysql://localhost:3306/infirmary";
	static String user = "root"; //change accordingly
	static String password = "root"; //change accordingly

	public static Connection connection;
	public static Statement stmt;
	static {
		try {
			connection = DriverManager.getConnection(URL, user, password);
			stmt = connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void start(Stage stage) throws IOException {
		fillInventory();
		stage.setOnCloseRequest(t -> {
			try {
				updateTxtFile();
			} catch (IOException e) {
				System.out.println(e);
			}
			Platform.exit();
			System.exit(0);
		});

		setupTables();
		Main.stage = stage;
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 700, 700);
		stage.setTitle("Infirmary");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		stage.centerOnScreen();
	}

	public static void main(String[] args) {
		launch();
	}
}