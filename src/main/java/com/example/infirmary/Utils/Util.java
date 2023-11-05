package com.example.infirmary.Utils;

import com.example.infirmary.Main;
import com.example.infirmary.Objects.Medication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.*;

public class Util {
	public static void changeScene(String fxmlFile) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
		try {
			Main.stage.setScene(new Scene(fxmlLoader.load(), 700, 700));
			Main.stage.centerOnScreen();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void fillInventory() throws IOException {
		FileReader r = new FileReader(Main.class.getResource("/medication.txt").getPath());
		BufferedReader br = new BufferedReader(r);
		String line;
		while ((line = br.readLine()) != null) {
			String[] medication = line.split(" ");
			Medication.addMedication(medication[0], medication[1], Integer.parseInt(medication[2]));
		}
		r.close();
	}

	public static void updateTxtFile() throws IOException {
		FileWriter w = new FileWriter(Main.class.getResource("/medication.txt").getPath(), false);
		BufferedWriter bw = new BufferedWriter(w);
		bw.write(Main.inventory.get(0).toString());
		for (int i = 1; i < Main.inventory.size(); i++) {
			bw.newLine();
			bw.write(Main.inventory.get(i).toString());
		}
		bw.close();
	}
}
