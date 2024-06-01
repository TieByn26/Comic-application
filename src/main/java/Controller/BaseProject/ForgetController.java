package Controller.BaseProject;

import ObjectGson.GsonForClient.CL_ChangePass;
import ObjectGson.GsonForServer.SV_CheckLogin;
import RequestForServer.PostData.RequestForgetPass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ForgetController {
    @FXML
    private Button submitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private PasswordField passwordField2;
    private CL_ChangePass cl_changePass = new CL_ChangePass();

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    public void initialize(){
        submitButton.setOnAction(actionEvent -> changePass());
        cancelButton.setOnAction(this::cancelToLogin);
    }
    private void changePass(){
        try{
            if (passwordField1.getText() == null || passwordField1.getText().trim().isEmpty() ||
                    (passwordField2.getText() == null || passwordField2.getText().trim().isEmpty())){
                alert.setTitle("Information Dialog");
                alert.setContentText("Ô nhập không được để trống");
                alert.showAndWait();
            } else {
                if (passwordField1.getText().equals(passwordField2.getText())) {
                    cl_changePass.setNewPass(passwordField1.getText());
                    cl_changePass.setUsername(OtpController.getUsername());
                    SV_CheckLogin check = RequestForgetPass.ForgetPass(cl_changePass);
                    if (check.getCheck()) {
                        alert.setTitle("Information Dialog");
                        alert.setContentText("Đổi mật khẩu thành công");
                        alert.showAndWait();
                    } else {
                        alert.setTitle("Information Dialog");
                        alert.setContentText("Hệ thống lỗi, đang khắc phục");
                        alert.showAndWait();
                    }
                } else {
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Mật khẩu không khớp");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void cancelToLogin(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ForgetController.class.getResource("ViewLogin.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
