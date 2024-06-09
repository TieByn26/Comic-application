package ChangeScene;

import Controller.BaseProject.ProfileController;
import Controller.BaseProject.UploadComicsByUserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangedSceneToUpComics {
    public static void ChangeScene(MouseEvent event, String pathFileFxml,String title,int idUser) {
        // Tạo một đối tượng FXMLLoader
        FXMLLoader loader = new FXMLLoader(ChangedSceneToUpComics.class.getResource(pathFileFxml));

        // Tải giao diện mới từ tệp FXML
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UploadComicsByUserController controller = loader.getController();

        if(controller != null) {
            controller.setIdUSer(idUser);
            //set event for navv
            controller.setEventForNav();

            controller.UpComics();

            controller.upLoadFullNameUSer();

            controller.checkValidData();



            System.out.println("id user upComics: "+controller.getIdUSer());
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
