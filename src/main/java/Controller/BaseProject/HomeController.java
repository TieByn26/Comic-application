package Controller.BaseProject;


import ChangeScene.ChangedSceneToComicsInformation;
import ChangeScene.ChangedSceneToFollow;
import General.EvenOfNav;
import General.Search;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import ObjectGson.GsonForServer.SV_ListUser;
import ObjectGson.GsonForServer.SV_User;
import RequestForServer.GetData.GetInformationCategory;
import RequestForServer.GetData.GetInformationComics;
import RequestForServer.GetData.GetInformationUser;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private HBox home_listTopComics;

    @FXML
    private VBox home_listTopUser;

    @FXML
    private ScrollPane home_scrollListTopUser;

    @FXML
    private Label nav_notfications;
    @FXML
    private TextField home_inputDataFind;
    @FXML
    private ImageView home_iconFind;

    @FXML
    private Label nav_history;

    @FXML
    private Label nav_follow;

    @FXML
    private ImageView home_iconProfile;
    @FXML
    private ImageView home_iconLogout;
    @FXML
    private Label nav_category;

    @FXML
    private Label nav_home;
    @FXML
    private Label home_nameListComics;

    @FXML
    private TilePane TL_listCategory;
    @FXML
    private ScrollPane TL_scroll_ListCategory;
    private String idCategory;
    @FXML
    private Label nav_UpComics;

    private int idUser = 1; // set mac dinh bang 1 (chua co gia tri tu db)
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();

    public void initialize() throws Exception {

        //call function upload list top comics to screen
        uploadListTopComics();

        //call function upload list top user to screen
        uploadTopUser();
    }

    public void setEventForNav() {
        evenOfNav.setEventForNavCategory(nav_category, TL_listCategory, TL_scroll_ListCategory, idUser);
        EvenOfNav.setEventForNavFollow(nav_follow, idUser);
        EvenOfNav.setEventForNavHistory(nav_history, idUser);
        EvenOfNav.setEventForNavNotifications(nav_notfications, idUser);
        EvenOfNav.setEventForNavHome(nav_home, idUser);
        EvenOfNav.setEventForProfile(home_iconProfile, idUser);
        EvenOfNav.setEventForNavUpComics(nav_UpComics, idUser);
        EvenOfNav.setEventChangeSceneToLogout(home_iconLogout);
    }

    public void eventSearch() {
        Search.setEventForSearch(home_inputDataFind, home_iconFind, idUser);
    }

    public void decideDataWillUploadToPaneComics(String decided) throws Exception { // ham quyet dinh xem se in ra man hinh du lieu cua thang nao (vi se co su kien nhan vao the loai thi se load truyen ra giao dien theo thang idCategory)
        if (decided == "byHome") {
            ArrayList<SV_ComicsInformation> listComics = GetInformationComics.getAllComics();
            setValueForPaneComics(listComics);
        } else {
            ArrayList<SV_ComicsInformation> listComics = GetInformationCategory.getAllComicsByCategory(idCategory);
            setValueForPaneComics(listComics);
        }
    }

    private void uploadListTopComics() throws Exception {
        ArrayList<SV_ComicsInformation> listComics = GetInformationComics.getTopComics();

        for (SV_ComicsInformation comics : listComics) {
            FXMLLoader newComicsLoader = new FXMLLoader(getClass().getResource(pathPaneComics));
            Parent comicPane = newComicsLoader.load();

            // Lay cac bien cua paneComics
            ImageView avtComics = (ImageView) comicPane.lookup("#PC_img");
            Label nameComic = (Label) comicPane.lookup("#PC_nameComics");
            Label chapter = (Label) comicPane.lookup("#PC_chapter");

            // Tai anh trong luong nen va su dung bo nho dem
            loadImageAsync(comics.getAvatarComic(), avtComics);

            // Set du lieu cho bien paneComics
            nameComic.setText(comics.getNameComic());
            chapter.setText(String.valueOf(comics.getNumberOfChapter()));

            // Set su kien click vao cac bo truyen
            comicPane.setOnMouseClicked(event -> {
                ChangedSceneToComicsInformation.ChangeScene(event, pathComicsInformation, "Thông tin truyện", nameComic.getText(), idUser);
            });

            home_listTopComics.getChildren().add(comicPane);
        }
        home_listTopComics.setSpacing(10);
    }

    private void loadImageAsync(String url, ImageView imageView) {
        Task<Image> loadImageTask = new Task<Image>() {
            @Override
            protected Image call() {
                return new Image(url, true);
            }
        };

        loadImageTask.setOnSucceeded(event -> imageView.setImage(loadImageTask.getValue()));
        new Thread(loadImageTask).start();
    }


    private void uploadTopUser() throws Exception {
        SV_ListUser listUser = GetInformationUser.getTop10User();

        for (SV_User user : listUser.getListUser()) {
            FXMLLoader newTopUser = new FXMLLoader(getClass().getResource(pathTopUser));
            Parent rootNewTopUser = newTopUser.load();

            // Lay cac bien trong pane user
            Label nameUser = (Label) rootNewTopUser.lookup("#TU_name");
            Label levelUser = (Label) rootNewTopUser.lookup("#TU_level");
            Label experience = (Label) rootNewTopUser.lookup("#TU_experience");
            ImageView avatarUser = (ImageView) rootNewTopUser.lookup("#TU_avt");

            // Set gia tri cho pane user
            nameUser.setText(user.getFullName());
            levelUser.setText(user.getLevel());
            experience.setText(String.valueOf(user.getExperience()));
            loadImageAsync(user.getAvatar(), avatarUser);

            home_listTopUser.getChildren().add(rootNewTopUser);
        }
        home_listTopUser.setSpacing(10);
    }


    private void setValueForPaneComics(ArrayList<SV_ComicsInformation> listComics) throws Exception {
        for (SV_ComicsInformation comics : listComics) {
            FXMLLoader newComicsLoader = new FXMLLoader(getClass().getResource(pathPaneComics));
            Parent comicPane = newComicsLoader.load();

            // Lay cac bien cua paneComics
            ImageView avtComics = (ImageView) comicPane.lookup("#PC_img");
            Label nameComic = (Label) comicPane.lookup("#PC_nameComics");
            Label chapter = (Label) comicPane.lookup("#PC_chapter");

            // Tai anh trong luong nen va su dung bo nho dem
            loadImageAsync(comics.getAvatarComic(), avtComics);

            // Set du lieu cho bien paneComics
            nameComic.setText(comics.getNameComic());
            chapter.setText(String.valueOf(comics.getNumberOfChapter()));

            // Set su kien click vao cac bo truyen
            comicPane.setOnMouseClicked(event -> {
                ChangedSceneToComicsInformation.ChangeScene(event, pathComicsInformation, "Thông tin truyện", nameComic.getText(), idUser);
            });

            home_listComics.getChildren().add(comicPane);
        }
        // Thiet lap TilePane
        home_listComics.setPadding(new Insets(10));
        home_listComics.setHgap(10);
        home_listComics.setVgap(10);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public void setHome_nameListComics(String newName) {
        home_nameListComics.setText(newName);
    }
}
