package Controller.BaseProject;

import ObjectGson.GsonForClient.CL_UsernameToNotifi;
import ObjectGson.GsonForServer.SV_Notification;
import RequestForServer.PostData.SendNotification;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminNotificationController {
    @FXML
    private TextField enterUser;
    @FXML
    private TextArea enterContent;
    @FXML
    private Button sendButton;
    private SV_Notification sv_notification = new SV_Notification();
    private CL_UsernameToNotifi cl_usernameToNotifi = new CL_UsernameToNotifi();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public void initialize(){
        sendButton.setOnAction(actionEvent -> sendMessage());
    }
    public void sendMessage(){
        cl_usernameToNotifi.setUsername(enterUser.getText());
        sv_notification.setContent(enterContent.getText());
        String check = SendNotification.sendNotification(cl_usernameToNotifi,sv_notification);
        System.out.println(check);
        if (check.trim().compareToIgnoreCase("false") == 0) {
            alert.setTitle("Information Dialog");
            alert.setContentText("Không có username này");
            alert.showAndWait();
        } else {
            alert.setTitle("Information Dialog");
            alert.setContentText("Đã gửi");
            alert.showAndWait();
        }
    }
}
