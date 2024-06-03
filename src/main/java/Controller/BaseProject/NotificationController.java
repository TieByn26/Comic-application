package Controller.BaseProject;

import General.EvenOfNav;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class NotificationController {

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
    private int idUser;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();
    public void initialize() throws Exception {
        //set event click for nav_category
        evenOfNav.setEventForNavCategory(nav_category, TL_listCategory,TL_scroll_ListCategory,idUser);


        //set event click for nav_follow
        EvenOfNav.setEventForNavFollow(nav_follow,idUser);

        //set event click for nav_history
        EvenOfNav.setEventForNavHistory(nav_history,idUser);

        //set event click for nav_notification
        EvenOfNav.setEventForNavNotifications(nav_notfications);

        //set event click for nav_home
        EvenOfNav.setEventForNavHome(nav_home,idUser);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
