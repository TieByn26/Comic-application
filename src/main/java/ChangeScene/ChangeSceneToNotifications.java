package ChangeScene;

import Controller.BaseProject.NotificationController;
import Controller.BaseProject.ProfileController;
import Controller.BaseProject.UploadComicsByUserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneToNotifications {
    public static void ChangeScene(MouseEvent event, String pathFileFxml,String title,int idUser) {
        // Tạo một đối tượng FXMLLoader
        FXMLLoader loader = new FXMLLoader(ChangeSceneGeneral.class.getResource(pathFileFxml));

        // Tải giao diện mới từ tệp FXML
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NotificationController controller = loader.getController();

        if(controller != null) {
            controller.setIdUser(idUser);
            //set event for navv
            controller.setEventForNav();

            controller.uploadDataToScreen();
            System.out.println("id user upComics: "+controller.getIdUser());
        }
        else  {
            System.out.println("profile controller is null");
        }

        // Lấy đối tượng Stage từ sự kiện (MouseEvent)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.setResizable(false);
        stage.centerOnScreen();
        // Đặt giao diện mới làm giao diện chính
        stage.setScene(new Scene(root));

        // Đặt tiêu đề cho cửa sổ
        stage.setTitle(title);

        // Hiển thị cửa sổ
        stage.show();

    }
}
