package Controller.BaseProject;

import General.EvenOfNav;
import ObjectGson.GsonForServer.SV_Notification;
import RequestForServer.GetData.GetInformationNotification;
import RequestForServer.PostData.Notification;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class NotificationController {
    private String pathPaneNotificatioin = "/Controller/BaseProject/ViewPaneNotifications.fxml";
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
    private Text NT_contentNotifications;
    @FXML
    private TilePane NT_ListNotification;
    @FXML
    private ImageView home_iconLogout;
    @FXML
    private Button NT_btnDeleteNotification;

    private int idUser;
    @FXML
    private Label nav_UpComics;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();

    public void initialize() throws Exception {
    }

    public void setEventForNav() {
        //set event click for nav_category
        evenOfNav.setEventForNavCategory(nav_category, TL_listCategory, TL_scroll_ListCategory, idUser);
        //set event click for nav_follow
        EvenOfNav.setEventForNavFollow(nav_follow, idUser);
        //set event click for nav_history
        EvenOfNav.setEventForNavHistory(nav_history, idUser);
        //set event click for nav_notification
        EvenOfNav.setEventForNavNotifications(nav_notfications, idUser);
        //set event click for nav_home
        EvenOfNav.setEventForNavHome(nav_home, idUser);
        //profile
        EvenOfNav.setEventForProfile(home_iconProfile, idUser);
        EvenOfNav.setEventForNavUpComics(nav_UpComics, idUser);
        EvenOfNav.setEventChangeSceneToLogout(home_iconLogout);
    }

    public void uploadDataToScreen() {
        ArrayList<SV_Notification> listNotification = GetInformationNotification.getAllCategoryInformation(idUser);
        for (SV_Notification notification : listNotification) {
            try {
                FXMLLoader newPaneNotification = new FXMLLoader(getClass().getResource(pathPaneNotificatioin));
                Parent rootNotification = newPaneNotification.load();

                Label PNT_status = (Label) rootNotification.lookup("#PNT_status");

                if (notification.getStatus()) {
                    PNT_status.setText("Đọc rùi nà");
                    rootNotification.setOpacity(0.5);
                } else {
                    PNT_status.setText("Đọc điii ");
                }
                rootNotification.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        NT_contentNotifications.setText(notification.getContent());
                        PNT_status.setText("Đọc rùi nà");
                        rootNotification.setOpacity(0.5);

                        //update trang thai da doc
                        Notification.updateStatus(notification.getIdNotification());
                        NT_btnDeleteNotification.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                               int statusDelete = Notification.deleteNotification(notification.getIdNotification());
                                if (statusDelete > 0) {
                                    // xoa thong bao khoi giao dien
                                    NT_ListNotification.getChildren().removeAll(rootNotification);
                                    //set lai content
                                    NT_contentNotifications.setText("Cậu vừa xóa 1 thông báo đó~~~");
                                }
                                else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Lỗi rùi");
                                    alert.setContentText("Xóa thông báo không thành công");
                                    alert.show();
                                }
                            }
                        });
                    }
                });
                NT_ListNotification.getChildren().add(rootNotification);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
