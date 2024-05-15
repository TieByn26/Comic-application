module com.example.doan {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.doan to javafx.fxml;
    exports com.example.doan;
}