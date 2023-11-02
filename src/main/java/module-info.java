module com.example.infirmary {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.example.infirmary to javafx.fxml;
	exports com.example.infirmary;
}