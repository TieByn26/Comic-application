package Controller.BaseProject;

import ChangeScene.ChangedSceneToReadComics;
import RequestToServer.GetData.GetInformationComics;
import ObjectGson.GsonForServer.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ComicsInformationController {

    private String pathViewReadComics = "/Controller/BaseProject/ViewReadComics.fxml";

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
    @FXML
    private Text CI_nameComics;
    @FXML
    private Text CI_author;
    @FXML
    private Text CI_status;
    @FXML
    private Text CI_category;
    @FXML
    private Text CI_views;
    @FXML
    private Text CI_numberOfChapter;
    @FXML
    private Text CI_descriptions;
    @FXML
    private ImageView CI_avatarComics;
    @FXML
    private Button CI_readLastChapter;
    @FXML
    private Button CI_btnReadBegin;
    @FXML
    private Button CI_readContinue;
    private String nameComics; // bien de controller goi den tu phuong thuc changedScene de luu gia tri ten truyen
    private String idComics;
    private int idUSer;
    public void initialize() throws Exception {
        //set event click for nav_category
        General.EvenOfNav.setEventForNavCategory(nav_category,TL_scroll_ListNotifications);

        //set event click for nav_follow
        General.EvenOfNav.setEventForNavFollow(nav_follow);

        //set event click for nav_history
        General.EvenOfNav.setEventForNavHistory(nav_history);

        //set event click for nav_notification
        General.EvenOfNav.setEventForNavNotifications(nav_notfications);

        //set event click for nav_home
        General.EvenOfNav.setEventForNavHome(nav_home);

        CI_btnReadBegin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ChangedSceneToReadComics.ChangeScene(event,pathViewReadComics,"Đọc truyện", idComics,idUSer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void loadComicsInformation () {
        System.out.println("dasfa");
        // lay du lieu tu server
        SV_ComicsInformation sv_comicsInformation =  GetInformationComics.getFullComicsInformationByNameComics(nameComics);
        // lay so luong view
        SV_Statistic svStatistic = GetInformationComics.getAllViewByIdComics(sv_comicsInformation.getIdComic());
        //lay idCategory
        SV_CategoryManager svCategoryManager = GetInformationComics.getIdCategoryByIdComics(sv_comicsInformation.getIdComic());
        //lay ten the loai bang id the loai
        SV_CategoryName svCategoryComics = GetInformationComics.getCategoryNameByIdCategory(svCategoryManager.getIdCategory());

        //set du lieu cho cac bien
        CI_nameComics.setText(nameComics);
        CI_author.setText(sv_comicsInformation.getAuthor());
        idComics = sv_comicsInformation.getIdComic();
        CI_descriptions.setText(sv_comicsInformation.getDescription());
        CI_status.setText(sv_comicsInformation.getStatus());
        CI_numberOfChapter.setText(sv_comicsInformation.getNumberOfChapter()+"");
        Image avtComics = new Image(sv_comicsInformation.getAvatarComic());
        CI_avatarComics.setImage(avtComics);
        CI_views.setText(svStatistic.getAllView()+"");
        CI_category.setText(svCategoryComics.getCategoryName());
    }

    public String getNameComics() {
        return nameComics;
    }

    public void setNameComics(String nameComics) {
        this.nameComics = nameComics;
    }

    public int getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(int idUSer) {
        this.idUSer = idUSer;
    }
}
