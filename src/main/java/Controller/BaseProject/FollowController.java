package Controller.BaseProject;

import ChangeScene.ChangedSceneToComicsInformation;
import ChangeScene.ChangedSceneToFollow;
import General.EvenOfNav;
import General.Search;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import RequestForServer.GetData.GetInformationFollow;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private ImageView home_iconLogout;

    @FXML
    private TextField home_inputDataFind;
    @FXML
    private ImageView home_iconFind;


    @FXML
    private Label nav_follow;

    @FXML
    private Label nav_category;
    @FXML
    private ImageView home_iconProfile;
    @FXML
    private Label nav_UpComics;
    @FXML
    private Label nav_home;
    @FXML
    private TilePane TL_listCategory;
    @FXML
    private ScrollPane TL_scroll_ListCategory;
    @FXML
    private TilePane FL_listComics;
    private int idUser;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();
    public void initialize() throws Exception {
    }
    public void setEventForNav() {
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

        EvenOfNav.setEventChangeSceneToLogout(home_iconLogout);
    }
    public void eventSearch() {
        Search.setEventForSearch(home_inputDataFind,home_iconFind,idUser);
    }

    public void setValueForPaneComics() throws Exception {
        ArrayList<SV_ComicsInformation> listComics = GetInformationFollow.getAllComicsFollowInformation(idUser);
        // Thêm các phần tử vào TilePane
        for (SV_ComicsInformation comics : listComics) {
            FXMLLoader newComicsLoader = new FXMLLoader(getClass().getResource(pathPaneComics));
            Parent comicPane = newComicsLoader.load();

            // Lấy các biến của paneComics
            ImageView avtComics = (ImageView) comicPane.lookup("#PC_img");
            Label nameComic = (Label) comicPane.lookup("#PC_nameComics");
            Label chapter = (Label) comicPane.lookup("#PC_chapter");

            // Tải ảnh không đồng bộ
            loadImageAsync(comics.getAvatarComic(), avtComics);

            // Set dữ liệu cho biến paneComics
            nameComic.setText(comics.getNameComic());
            chapter.setText(comics.getNumberOfChapter() + "");

            // Set sự kiện click vào các bộ truyện
            comicPane.setOnMouseClicked(event -> {
                ChangedSceneToComicsInformation.ChangeScene(event, pathComicsInformation, "Thông tin truyện", nameComic.getText(), idUser);
            });

            FL_listComics.getChildren().add(comicPane);
        }
    }

    private void loadImageAsync(String url, ImageView imageView) {
        Task<Image> loadImageTask = new Task<Image>() {
            @Override
            protected Image call() {
                return new Image(url, true); // true to load in the background
            }
        };

        loadImageTask.setOnSucceeded(event -> imageView.setImage(loadImageTask.getValue()));
        new Thread(loadImageTask).start();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
