package ChangeScene;

import Controller.BaseProject.PreviewComicsController;
import Controller.BaseProject.ProfileController;
import Controller.BaseProject.UploadComicsByUserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangedSceneToPreviewComic {
    public static void ChangeScene(MouseEvent event, String pathFileFxml,String title,String linkImg, String nameComic) {
        // Tạo một đối tượng FXMLLoader
        FXMLLoader loader = new FXMLLoader(ChangeSceneGeneral.class.getResource(pathFileFxml));

        // Tải giao diện mới từ tệp FXML
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PreviewComicsController controller = loader.getController();

        if(controller != null) {
            controller.setNameComic(nameComic);
            controller.setLimkImg(linkImg);
            controller.setInfNameComic();
            controller.uploadImageOfChapter();
        }
        else  {
            System.out.println("PreviewComicsController is null");
        }

        // Lấy đối tượng Stage từ sự kiện (MouseEvent)
        Stage newWindow = new Stage();
        newWindow.setHeight(790);
        newWindow.setWidth(700);
        newWindow.setResizable(false);
        newWindow.centerOnScreen();

        // Đặt giao diện mới làm giao diện chính
        newWindow.setScene(new Scene(root));

        // Đặt tiêu đề cho cửa sổ
        newWindow.setTitle(title);

        // Hiển thị cửa sổ
        newWindow.show();

    }
}
