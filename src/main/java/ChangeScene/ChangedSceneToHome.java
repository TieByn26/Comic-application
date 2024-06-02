package ChangeScene;

import Controller.BaseProject.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangedSceneToHome {
    public static void ChangeScene(MouseEvent event, String pathFileFxml, String title, int idUser) {
        // Tạo một đối tượng FXMLLoader
        FXMLLoader loader = new FXMLLoader(ChangedSceneToHome.class.getResource(pathFileFxml));

        // Tải giao diện mới từ tệp FXML
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HomeController controller = loader.getController();
        if(controller != null) {
            controller.setIdUser(idUser);
            //load thong tin truyen ra giao dien
            try {
                System.out.println(324);
                controller.decideDataWillUploadToPaneComics("byHome");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("HomeController is null");
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
    // change scene to home yeu cau them idCategory de
    public static void ChangeSceneRequestIdCategory(MouseEvent event, String pathFileFxml, String title, int idUser,String idCategory, String categoryName) {
        // Tạo một đối tượng FXMLLoader
        FXMLLoader loader = new FXMLLoader(ChangedSceneToHome.class.getResource(pathFileFxml));

        // Tải giao diện mới từ tệp FXML
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HomeController controller = loader.getController();
        if(controller != null) {
            controller.setIdUser(idUser);
            controller.setIdCategory(idCategory);
            controller.setHome_nameListComics("Danh sách truyện theo thể loại "+categoryName);

            // load thong tin truyen ra giao dien
            try {
                controller.decideDataWillUploadToPaneComics("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("HomeController is null");
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
