package Controller.BaseProject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;

public class ReadComicsController {

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
    }
}
