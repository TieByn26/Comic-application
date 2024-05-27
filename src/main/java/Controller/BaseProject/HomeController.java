package Controller.BaseProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class HomeController {
    private String pathPaneComics = "/Controller/BaseProject/ViewPaneComics.fxml";
    private String pathTopUser = "/Controller/BaseProject/ViewPaneTopUser.fxml";

    @FXML
    private ScrollPane home_scroller;

    @FXML
    private TilePane home_listComics;

    @FXML
    private VBox home_parentListComics;

    @FXML
    private HBox home_listTopComics;

    @FXML
    private VBox home_listTopUser;

    @FXML
    private ScrollPane home_scrollListTopUser;

    public void initialize() throws Exception {
        //call function upload list comics to screen
        uploadListComics();

        //call function upload list top comics to screen
        uploadListTopComics();

        //call function upload list top user to screen
        uploadTopUser();

    }

    private void uploadListComics() throws Exception {
        // Thiết lập TilePane
        home_listComics.setPadding(new Insets(10));
        home_listComics.setHgap(10);
        home_listComics.setVgap(10);

        // Thêm các phần tử vào TilePane
        for (int i = 0; i < 30; i++) {
            FXMLLoader newComicsLoader = new FXMLLoader(getClass().getResource(pathPaneComics));
            Parent comicPane = newComicsLoader.load();
            home_listComics.getChildren().add(comicPane);
        }

        // Thiết lập VBox để căn giữa TilePane
        home_parentListComics.setPadding(new Insets(10));
    }

    private void uploadListTopComics() throws Exception {
        for (int i = 0; i < 30; i++) {
            FXMLLoader newTopComics = new FXMLLoader(getClass().getResource(pathPaneComics));
            Parent rootNewTopComics = newTopComics.load();

            home_listTopComics.getChildren().add(rootNewTopComics);
        }
        home_listTopComics.setSpacing(10);
    }

    private void uploadTopUser() throws Exception {
        for (int i = 0; i < 20; i++) {
            FXMLLoader newTopUser = new FXMLLoader(getClass().getResource(pathTopUser));
            Parent rootNewTopUser = newTopUser.load();

            home_listTopUser.getChildren().add(rootNewTopUser);
        }
        home_listTopUser.setSpacing(10);
    }
}