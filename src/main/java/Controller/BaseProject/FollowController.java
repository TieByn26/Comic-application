package Controller.BaseProject;

import ChangeScene.ChangedSceneToComicsInformation;
import ChangeScene.ChangedSceneToFollow;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import RequestForServer.GetData.GetInformationFollow;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class FollowController {

    private String pathPaneComics = "/Controller/BaseProject/ViewPaneComics.fxml";
    private String pathComicsInformation = "/Controller/BaseProject/ViewComicsInformation.fxml";

    @FXML
    private Label nav_notfications;

    @FXML
    private Label nav_history;

    @FXML
    private Label nav_follow;

    @FXML
    private Label nav_category;

    @FXML
    private Label nav_home;
    @FXML
    private ScrollPane TL_scroll_ListNotifications;
    @FXML
    private TilePane FL_listComics;



    private int idUser;
    public void initialize() throws Exception {
    }
    public void setEventForNav() {
        //set event click for nav_category
        General.EvenOfNav.setEventForNavCategory(nav_category,TL_scroll_ListNotifications);

        //set event click for nav_follow
        General.EvenOfNav.setEventForNavFollow(nav_follow,idUser);

        //set event click for nav_history
        General.EvenOfNav.setEventForNavHistory(nav_history,idUser);

        //set event click for nav_notification
        General.EvenOfNav.setEventForNavNotifications(nav_notfications);

        //set event click for nav_home
        General.EvenOfNav.setEventForNavHome(nav_home);
    }

    public void setValueForPaneComics() throws Exception {
        ArrayList<SV_ComicsInformation> listComics = GetInformationFollow.getAllComicsFollowInformation(idUser);
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

            FL_listComics.getChildren().add(comicPane);
        }
    }
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
