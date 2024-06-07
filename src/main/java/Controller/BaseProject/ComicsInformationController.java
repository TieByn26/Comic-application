package Controller.BaseProject;

import ChangeScene.ChangedSceneToReadComics;
import General.EvenOfNav;
import RequestForServer.GetData.GetInformationComics;
import ObjectGson.GsonForServer.*;
import RequestForServer.GetData.GetInformationHistory;
import RequestForServer.PostData.Follow;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
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
    private Label nav_UpComics;
    @FXML
    private TilePane TL_listCategory;
    @FXML
    private ScrollPane TL_scroll_ListCategory;
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
    private Button CI_Follow;
    @FXML
    private Button CI_readContinue;
    private String nameComics; // bien de controller goi den tu phuong thuc changedScene de luu gia tri ten truyen
    private String idComics;
    private int idUser;
    boolean isFollow = false;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();
    public void initialize() throws Exception {
        CI_btnReadBegin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ChangedSceneToReadComics.ChangeScene(event,pathViewReadComics,"Đọc truyện", idComics, idUser,nameComics,Integer.parseInt(CI_numberOfChapter.getText()),Integer.parseInt(CI_views.getText()),1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        CI_readLastChapter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ChangedSceneToReadComics.ChangeScene(event,pathViewReadComics,"Đọc truyện", idComics, idUser,nameComics,Integer.parseInt(CI_numberOfChapter.getText()),Integer.parseInt(CI_views.getText()),Integer.parseInt(CI_numberOfChapter.getText()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        CI_readContinue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    //lay chapter nguoi dung doc lan cuoi cung
                    SV_ChapterOfComics lastReadChapter = GetInformationHistory.getLastReadChapter(idUser,idComics);
                    System.out.println("chapter cuoi cung: "+lastReadChapter.getChapter());
                    ChangedSceneToReadComics.ChangeScene(event,pathViewReadComics,"Đọc truyện", idComics, idUser,nameComics,Integer.parseInt(CI_numberOfChapter.getText()),Integer.parseInt(CI_views.getText()),lastReadChapter.getChapter());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // su kien nhan nut theo doi
        CI_Follow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              if(isFollow  == false) {
                  CI_Follow.setStyle("-fx-background-color: #FF6699;-fx-cursor: hand");
                  isFollow = true;
                  if(Follow.addNewFollow(idComics, idUser) == 0) {
                      CI_Follow.setStyle("-fx-background-color: #FFCC66; -fx-cursor: hand");
                      isFollow = false;
                      showAlert(Alert.AlertType.ERROR,"loi he thong","không thể theo dõi");
                  }
              }
              else {
                      CI_Follow.setStyle("-fx-background-color: #FFCC66; -fx-cursor: hand");
                      isFollow = false;
                      if(Follow.deleteFollow(idComics, idUser) == 0) {
                          CI_Follow.setStyle("-fx-background-color: #FF6699;-fx-cursor: hand");
                          showAlert(Alert.AlertType.ERROR,"loi he thong","không thể hủy theo dõi");
                          isFollow = true;
                  }
              }
            }
        });
    }

    public void setEventForNav () {
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

    public void loadComicsInformation () {
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
    public void checkStatusFollow () { // kiem tra xem truyen da duoc theo doi hay chua
        // xem truyen da duoc theo doi hay chua
        if (Follow.checkStatusFollow(idComics, idUser) == 0) {
            isFollow = false;
        }
        else {
            CI_Follow.setStyle("-fx-background-color: #FF6699;-fx-cursor: hand");
            isFollow = true;
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public String getNameComics() {
        return nameComics;
    }

    public void setNameComics(String nameComics) {
        this.nameComics = nameComics;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
