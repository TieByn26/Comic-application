package com.example.doan;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Tạo VBox để chứa các phần tử
        VBox vbox = new VBox();
        vbox.setSpacing(10);  // Khoảng cách dọc giữa các phần tử
        vbox.setPadding(new Insets(10));

        // Thêm các phần tử vào VBox
        for (int i = 0; i < 20; i++) {
            Rectangle rect = new Rectangle(300, 250, Color.BLUE);  // Ví dụ phần tử là hình chữ nhật
            vbox.getChildren().add(rect);
        }

        // Tạo ScrollPane chứa VBox
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);  // Đảm bảo ScrollPane vừa chiều rộng
        scrollPane.setFitToHeight(true); // Đảm bảo ScrollPane vừa chiều cao
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);  // Thanh cuộn dọc khi cần
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Không có thanh cuộn ngang

        // Tạo Scene và đặt nó vào Stage
        Scene scene = new Scene(scrollPane, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vertical ScrollPane Example");
        primaryStage.show();
    }
}
