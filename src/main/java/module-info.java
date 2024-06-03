module com.example.doan {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.google.gson;

    opens Controller.BaseProject to javafx.fxml;
    opens ObjectGson.GsonForServer to com.google.gson, javafx.base;
    opens ObjectGson.GsonForClient to com.google.gson;
    exports Controller.BaseProject;
}
