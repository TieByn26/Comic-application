package Controller.BaseProject;

import ChangeScene.ChangedSceneToPreviewComic;
import General.EvenOfNav;
import ObjectGson.GsonForServer.SV_User;
import RequestForServer.GetData.GetInformationUser;
import RequestForServer.PostData.Comics;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;


public class UploadComicsByUserController {
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
    private TextField UC_author;
    @FXML
    private TextField UC_nameComic;
    @FXML
    private TextField UC_category;
    @FXML
    private TextField UC_chapter;
    @FXML
    private ChoiceBox UC_status;
    @FXML
    private TextField UC_linkAvatarComic;
    @FXML
    private TextArea UC_description;
    @FXML
    private TextArea UC_LinkImageOfChapter;
    @FXML
    private Button UC_btnUpComic;
    @FXML
    private Button UC_btnPreviewAvatar;
    @FXML
    private Button UC_previewComics;
    @FXML
    private ImageView UC_avatarComic;
    @FXML
    private TilePane TL_listCategory;
    @FXML
    private ScrollPane TL_scroll_ListCategory;
    private String pathPreviewComics = "/Controller/BaseProject/ViewPreviewComics.fxml";


    private int idUser;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();

    boolean isCorrect = false;  // neu khong dung thi khong duoc dang truyen

    public void initialize() throws Exception {
        UC_status.setItems(FXCollections.observableArrayList("Đã hoàn thành","Đang tiến hành"));

        //set gia tri mac dinh la dang tien hanh
        UC_status.setValue("Đang tiến hành");

        // Xử lý sự kiện khi người dùng chọn một mục
        UC_status.setOnAction(event -> {
            String selectedOption = (String) UC_status.getValue();
        });

        UC_btnPreviewAvatar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Image avt = new Image(UC_linkAvatarComic.getText());
                    System.out.println(UC_linkAvatarComic.getText());
                    UC_avatarComic.setImage(avt);

                    if (UC_avatarComic == null) {
                        System.out.println("anh null");
                    }
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("link ảnh avatar truyện sai");
                    alert.setTitle("Cảnh báo");
                    alert.show();
                    e.printStackTrace();
                }

            }
        });

        UC_previewComics.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ChangedSceneToPreviewComic.ChangeScene(event,pathPreviewComics,"Xem trước truyện",UC_LinkImageOfChapter.getText(),UC_nameComic.getText());
            }
        });

    }

    public void setEventForNav() {
        //set event click for nav_category
        evenOfNav.setEventForNavCategory(nav_category, TL_listCategory, TL_scroll_ListCategory, idUser);

        //set event click for nav_follow
        EvenOfNav.setEventForNavFollow(nav_follow, idUser);

        //set event click for nav_history
        EvenOfNav.setEventForNavHistory(nav_history, idUser);

        //set event click for nav_notification
        EvenOfNav.setEventForNavNotifications(nav_notfications,idUser);

        //set event click for nav_home
        EvenOfNav.setEventForNavHome(nav_home, idUser);

        //profile
        EvenOfNav.setEventForProfile(home_iconProfile, idUser);

        EvenOfNav.setEventForNavUpComics(nav_UpComics,idUser);

        // khoa khong cho nguoi dung nhap ten tac gia
        UC_author.setEditable(false);
    }
    public void UpComics() {
        UC_btnUpComic.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //kiem tra tinh hop le cua du lieu
                tryCatchData();

                String status = (String) UC_status.getValue();
                if (isCorrect == true) {
                    int statusUpComic = Comics.upComicByUser(UC_nameComic.getText(), UC_category.getText(), status, UC_linkAvatarComic.getText(), UC_LinkImageOfChapter.getText(), UC_description.getText(), Integer.parseInt(UC_chapter.getText()), UC_author.getText());
                    if (statusUpComic > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thành công");
                        alert.setContentText("Đăng truyện thành công. Hãy chờ admin duyệt truyện của bạn");
                        alert.show();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thất ");
                        alert.setContentText("Đăng truyện thất bại");
                        alert.show();
                    }

                }
            }
        });
    }

    public void upLoadFullNameUSer() {
        SV_User dataUser = GetInformationUser.getFullName(idUser);

        //upload data to screen
        UC_author.setText(dataUser.getFullName());
    }

    public void tryCatchData() {
      try {
          if(UC_nameComic.getText().length() > 255 || UC_nameComic.getText().length() < 0  || UC_category.getText().length() > 255 || UC_category.getText().length() < 0  || Integer.parseInt(UC_chapter.getText()) < 0 || UC_LinkImageOfChapter.getText().length() < 20) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setContentText("Dữ liệu đầu vào sai");
              alert.setTitle("Cảnh báo");
              alert.show();
              isCorrect = false;
          }
          else {
              isCorrect = true;
          }
      }
      catch (Exception e) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setContentText("Dữ liệu đầu vào sai");
          alert.setTitle("Cảnh báo");
          alert.show();
          isCorrect = false;

      }
    }

    public void checkValidData() {
        isValidDataTextField(UC_nameComic);
        isValidDataTextField(UC_category);
        isValidDataTextField(UC_chapter);
        isValidDataTextArea(UC_description);
        isValidDataTextArea(UC_LinkImageOfChapter);
    }

    public void isValidDataTextField(TextField input) {
        input.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) { // Khi newValue là false, tức là TextField mất focus
                    if (input.getText().length() < 1 || input.getText().length() > 255) {
                        // Thiết lập CSS trực tiếp cho TextField
                        input.setStyle("-fx-border-color: red;");
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Vui lòng " + input.getPromptText());
                        alert.setTitle("Cảnh báo");
                        alert.show();
                    } else {
                        // Nếu điều kiện thỏa mãn, loại bỏ màu viền đỏ
                        input.setStyle("");
                    }
                }
            }
        });

    }
    public void isValidDataTextArea(TextArea input) {
        input.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) { // Khi newValue là false, tức là TextField mất focus
                    if (input.getText().length() < 1) {
                        // Thiết lập CSS trực tiếp cho TextField
                        input.setStyle("-fx-border-color: red;");
                    } else {
                        // Nếu điều kiện thỏa mãn, loại bỏ màu viền đỏ
                        input.setStyle("");
                    }
                }
            }
        });

    }
    public int getIdUSer() {
        return idUser;
    }

    public void setIdUSer(int idUSer) {
        this.idUser = idUSer;
    }
}
