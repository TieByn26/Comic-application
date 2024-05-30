package Controller.BaseProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OtpController {
    @FXML
    private Button submitButton;
    @FXML
    private Button getcapchaButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;

    @FXML
    public void initialize(){
        getcapchaButton.setOnAction(actionEvent -> getCapcha());
        submitButton.setOnAction(actionEvent -> submit());
        cancelButton.setOnAction(this::cancelToLogin);
    }
    private void getCapcha(){

    }
    private void submit(){

    }
    private void cancelToLogin(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(OtpController.class.getResource("ViewLogin.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
