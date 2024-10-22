package ChangeScene;

import Controller.BaseProject.FollowController;
import Controller.BaseProject.HistoryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneToHistory {
    public static void ChangeScene(MouseEvent event, String pathFileFxml, String title, int idUser) throws Exception {
        // Tạo một đối tượng FXMLLoader
        FXMLLoader loader = new FXMLLoader(ChangeSceneToHistory.class.getResource(pathFileFxml));

        // Tải giao diện mới từ tệp FXML
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //lay controller
        HistoryController controller = (HistoryController) loader.getController();

        if (controller != null) {
            //set gia tri ten truyen cho bien nameComics trong controller
            controller.setIdUser(idUser);

            // goi cac ham de upload du lieu
            controller.setValueForPaneComics();
            //set su kien cho thanh nav
            controller.setEventForNav();
            controller.eventSearch();
        } else {
            System.out.println("controller ComicsInformation");
        }

        // Lấy đối tượng Stage từ sự kiện (MouseEvent)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(800);
        stage.setWidth(1200);
        // Đặt giao diện mới làm giao diện chính
        stage.setScene(new Scene(root));
        // Đặt tiêu đề cho cửa sổ
        stage.setTitle(title);

        // Hiển thị cửa sổ
        stage.show();

    }

}
