package Controller.BaseProject;

import General.EvenOfNav;
import ObjectGson.GsonForServer.SV_CheckUpdate;
import ObjectGson.GsonForServer.SV_User;
import RequestForServer.GetData.GetInformationUser;
import RequestForServer.PostData.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;


public class ProfileController {

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
    private Text PF_idUSer;

    @FXML
    private Text PF_fullName;

    @FXML
    private Text PF_level;

    @FXML
    private Text PF_experience;

    @FXML
    private ImageView PF_avatarUser;
    @FXML
    private TextArea PF_story;
    @FXML
    private Button PF_btnEdit;
    @FXML
    private Button PF_btnUpdate;
    @FXML
    private TilePane PF_listComics;


    private int idUser;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();
    @FXML
    private Label nav_UpComics;

    public void initialize() throws Exception {

    }

    public void setEventForStory() {
        PF_btnUpdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int statusUpdate = User.updateStoryUser(idUser,PF_story.getText());

                if (statusUpdate > 0) {
                    Alert notification = new Alert(Alert.AlertType.INFORMATION);
                    notification.setTitle("Thông báo");
                    notification.setHeaderText("Cập nhật tiểu sử");
                    notification.setContentText("Cập nhật thành công");
                    // Hiển thị Alert
                    notification.showAndWait();
                }
                else {
                    Alert notification = new Alert(Alert.AlertType.INFORMATION);
                    notification.setTitle("Thông báo");
                    notification.setHeaderText("Cập nhật tiểu sử");
                    notification.setContentText("Cập nhật thành công");
                    // Hiển thị Alert
                    notification.showAndWait();
                }
                //khong cho nguoi dung sua tieu su
                PF_story.setEditable(false);
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

        PF_btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PF_story.setEditable(true);
            }
        });
    }

    public void uploadDataUser() {
        SV_User dataUser = GetInformationUser.getAllInforUserByIdUser(idUser);

        PF_story.setText(dataUser.getStory());
        PF_fullName.setText(dataUser.getFullName());
        PF_experience.setText(dataUser.getExperience() + "");
        PF_level.setText(dataUser.getLevel());
        PF_idUSer.setText(idUser+"");

        //khong cho nguoi dung nhap tieu su
        PF_story.setEditable(false);

        Image avt = new Image(dataUser.getAvatar());
        PF_avatarUser.setImage(avt);
    }

    public int getIdUSer() {
        return idUser;
    }

    public void setIdUSer(int idUSer) {
        this.idUser = idUSer;
    }
}
