package Controller.BaseProject;


import ChangeScene.ChangedSceneToComicsInformation;
import ChangeScene.ChangedSceneToFollow;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import RequestForServer.GetData.GetInformationComics;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HomeController {
    private static String pathPaneComics = "/Controller/BaseProject/ViewPaneComics.fxml";
    private static String pathTopUser = "/Controller/BaseProject/ViewPaneTopUser.fxml";
    private static String pathComicsInformation = "/Controller/BaseProject/ViewComicsInformation.fxml";
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

    @FXML
    private Label nav_notfications;

    @FXML
    private Label nav_history;

    @FXML
    private Label nav_follow;

    @FXML
    private ImageView home_iconProfile;

    @FXML
    private Label nav_category;

    @FXML
    private Label nav_home;

    @FXML
    private ScrollPane TL_scroll_ListNotifications;

    private int idUser = 1; // set mac dinh bang 1 (chua co gia tri tu db)



    public void initialize() throws Exception {
        //call function upload list comics to screen
        uploadListComics();

        //call function upload list top comics to screen
        uploadListTopComics();

        //call function upload list top user to screen
        uploadTopUser();

        //set event click for nav_category
        General.EvenOfNav.setEventForNavCategory(nav_category,TL_scroll_ListNotifications);

        //set event click for nav_follow
        General.EvenOfNav.setEventForNavFollow(nav_follow,idUser);

        //set event click for nav_history
        General.EvenOfNav.setEventForNavHistory(nav_history);

        //set event click for nav_notification
        General.EvenOfNav.setEventForNavNotifications(nav_notfications);

        //set event click for nav_home
        General.EvenOfNav.setEventForNavHome(nav_home);
    }

    private void uploadListComics() throws Exception {
        // Thiết lập TilePane
        home_listComics.setPadding(new Insets(10));
        home_listComics.setHgap(10);
        home_listComics.setVgap(10);

        //lay du lieu cac bo truyen tu server
        ArrayList<SV_ComicsInformation> listComics = GetInformationComics.getAllComics();
        //goi ham set thong tin cho cac paneComics
        setValueForPaneComics(listComics);

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

    private void setValueForPaneComics(ArrayList<SV_ComicsInformation> listComics) throws Exception {
        // Thêm các phần tử vào TilePane
        for (SV_ComicsInformation comics : listComics) {
            FXMLLoader newComicsLoader = new FXMLLoader(getClass().getResource(pathPaneComics));
            Parent comicPane = newComicsLoader.load();
            // lay cac bien cua paneComics
            ImageView avtComics = (ImageView) comicPane.lookup("#PC_img");
            Label nameComic = (Label) comicPane.lookup("#PC_nameComics");
            Label chapter = (Label) comicPane.lookup("#PC_chapter");
            // set du lieu cho bien paneComics
            Image imgAvt = new Image(comics.getAvatarComic());  //tao hinh anh de nhet vao avt comics
            avtComics.setImage(imgAvt);
            nameComic.setText(comics.getNameComic());
            chapter.setText(comics.getNumberOfChapter()+"");

            // set su kien click vao cac bo truyen
            comicPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ChangedSceneToComicsInformation.ChangeScene(event,pathComicsInformation,"Thông tin truyện",nameComic.getText(), idUser);
                }
            });

            home_listComics.getChildren().add(comicPane);
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
