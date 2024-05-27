package Controller.BaseProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HomeApp.class.getResource("/Controller/BaseProject/ViewHome.fxml"));

        Scene scene = new Scene(loader.load());
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
