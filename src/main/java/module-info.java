module com.example.infirmary {
	requires javafx.controls;
	requires javafx.fxml;

	requires java.sql;
	requires java.desktop;

	opens com.example.infirmary to javafx.fxml;
	exports com.example.infirmary;
	exports com.example.infirmary.Controllers;
	opens com.example.infirmary.Controllers to javafx.fxml;
	exports com.example.infirmary.Utils;
	opens com.example.infirmary.Utils to javafx.fxml;
	exports com.example.infirmary.Objects;
	opens com.example.infirmary.Objects to javafx.fxml;
}