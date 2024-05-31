package Controller.BaseProject;

import RequestToServer.GetData.GetInformationComics;
import ObjectGson.GsonForServer.SV_Comments;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class ReadComicsController {

    private String pathPaneComments = "/Controller/BaseProject/ViewPaneComment.fxml";

    @FXML
    private Label nav_notfications;

    @FXML
    private Label nav_history;

    @FXML
    private Label nav_follow;

    @FXML
    private ImageView home_iconProfile;
    @FXML
    private ImageView RC_back;
    @FXML
    private ImageView RC_next;

    @FXML
    private Label nav_category;

    @FXML
    private Label nav_home;

    @FXML
    private TilePane RC_listComments;
    @FXML
    private TilePane RC_listImages;

    @FXML
    private TextArea RC_formInputComment;
    @FXML
    private Button RC_btnSubmit;
    @FXML
    private Text RC_chapter;
    @FXML
    private Text RC_nameComics;
    @FXML
    private ScrollPane TL_scroll_ListNotifications;

    private int idcomics;
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

    public void setDataForComment()  {
        ArrayList<SV_Comments> listComment = GetInformationComics.getAllComment(idcomics+"");

        for (SV_Comments comment : listComment) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathPaneComments));
            Parent paneComment = null;
            try {
                paneComment = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ImageView CM_imgLike = (ImageView) paneComment.lookup("#CM_imgLike");
            ImageView CM_imgDislike = (ImageView) paneComment.lookup("#CM_imgDislike");
            ImageView CM_avatar = (ImageView) paneComment.lookup("#CM_avatar");
            Text CM_name = (Text) paneComment.lookup("#CM_name");
            Text CM_NumberOflike = (Text) paneComment.lookup("#CM_NumberOflike");
            Text CM_NumberOfDislike = (Text) paneComment.lookup("#CM_NumberOfDislike");
            ScrollPane  CM_scrollComment = (ScrollPane) paneComment.lookup("#CM_scrollComment");
            Text CM_comment = (Text) CM_scrollComment.getContent().lookup("#CM_comment");

            CM_NumberOfDislike.setText(comment.getDislike()+"");
            CM_NumberOflike.setText(comment.getLike()+"");
            CM_comment.setText(comment.getComment());
            RC_listComments.getChildren().add(paneComment);

        }
    }

    public int getIdcomics() {
        return idcomics;
    }

    public void setIdcomics(int idcomics) {
        this.idcomics = idcomics;
    }
}
