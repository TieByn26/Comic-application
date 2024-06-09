package Controller.BaseProject;

import ChangeScene.ChangedSceneToComicsInformation;
import General.EvenOfNav;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import RequestForServer.GetData.GetInformationFollow;
import RequestForServer.GetData.GetInformationHistory;
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

public class HistoryController {
    private String pathPaneComics = "/Controller/BaseProject/ViewPaneComics.fxml";
    private String pathComicsInformation = "/Controller/BaseProject/ViewComicsInformation.fxml";

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
    private TilePane TL_listCategory;
    @FXML
    private ScrollPane TL_scroll_ListCategory;
    @FXML
    private TilePane HT_listComics;
    @FXML
    private Label nav_UpComics;
    private int idUser;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();

    public void initialize() throws Exception {
    }

    public void  setEventForNav () {
        //set event click for nav_category
        evenOfNav.setEventForNavCategory(nav_category, TL_listCategory,TL_scroll_ListCategory,idUser);

        //set event click for nav_follow
        EvenOfNav.setEventForNavFollow(nav_follow,idUser);

        //set event click for nav_history
        EvenOfNav.setEventForNavHistory(nav_history,idUser);

        //set event click for nav_notification
        EvenOfNav.setEventForNavNotifications(nav_notfications,idUser);

        //set event click for nav_home
        EvenOfNav.setEventForNavHome(nav_home,idUser);

        //profile
        EvenOfNav.setEventForProfile(home_iconProfile,idUser);

        EvenOfNav.setEventForNavUpComics(nav_UpComics,idUser);
    }

    public void setValueForPaneComics() throws Exception {
        ArrayList<SV_ComicsInformation> listComics = GetInformationHistory.getAllComicsHistoryInformation(idUser);
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

            HT_listComics.getChildren().add(comicPane);
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
