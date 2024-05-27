module com.example.doan {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens Controller.BaseProject to javafx.fxml;
    exports Controller.BaseProject;
}