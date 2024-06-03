package Controller.BaseProject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminNotificationController {
    @FXML
    TextField enterUser;
    @FXML
    TextArea enterContent;
    @FXML
    Button sendButton;

    @FXML
    public void initialize(){
        sendButton.setOnAction(actionEvent -> sendMessage());
    }
    public void sendMessage(){

    }
}
