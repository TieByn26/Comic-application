package Controller.BaseProject;

import ChangeScene.ChangedSceneToHome;
import ObjectGson.GsonForClient.CL_LoginInformation;
import ObjectGson.GsonForClient.CL_RegisterInformation;
import ObjectGson.GsonForServer.SV_CheckLogin;
import RequestForServer.PostData.RequestLogin;
import RequestForServer.PostData.RequestRegister;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class LoginController {
    @FXML
    private Pane banner;
    @FXML
    private Button buttonSignUp;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Pane banner2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button buttonforget;
    @FXML
    private TextField textfield;
    @FXML
    private TextField textField1;
    @FXML
    private PasswordField textpassword1;
    @FXML
    private PasswordField textpassword2;
    @FXML
    private PasswordField textpassword3;
    @FXML
    private Label labelsi;
    @FXML
    private Label labelsu;
    @FXML
    private Label labeltemp;

    @FXML
    public void initialize(){
        buttonSignUp.setOnAction(actionEvent -> changeToSignUp());
        buttonSignIn.setOnAction(actionEvent -> changeToSignIn());
        buttonforget.setOnAction(this::changeToForget);
        button3.setOnAction(this::signInNow);
        button4.setOnAction(actionEvent -> signUpNow());
    }
    private void changeToSignUp(){
        TranslateTransition transition1 = new TranslateTransition();
        buttonSignUp.setVisible(false);
        buttonSignIn.setVisible(true);
        label1.setVisible(false);
        label2.setVisible(true);
        textField1.setVisible(false);
        textpassword1.setVisible(false);
        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(banner);
        transition1.setByX(395);
        transition1.play();

        TranslateTransition transition2 = new TranslateTransition();
        button3.setVisible(false);
        button4.setVisible(true);
        buttonforget.setVisible(false);
        labelsi.setVisible(false);
        labelsu.setVisible(true);
        textField1.setVisible(true);
        textpassword2.setVisible(true);
        textpassword3.setVisible(true);
        transition2.setDuration(Duration.seconds(0.5));
        transition2.setNode(banner2);
        transition2.setByX(-300);
        transition2.play();

        textfield.setText("");
        textField1.setText("");
        textpassword1.setText("");
        textpassword2.setText("");
        textpassword3.setText("");
    }
    private void changeToSignIn(){
        TranslateTransition transition1 = new TranslateTransition();
        buttonSignUp.setVisible(true);
        buttonSignIn.setVisible(false);
        label1.setVisible(true);
        label2.setVisible(false);
        textpassword1.setVisible(true);

        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(banner);
        transition1.setByX(-395);
        transition1.play();

        TranslateTransition transition2 = new TranslateTransition();
        button3.setVisible(true);
        button4.setVisible(false);
        buttonforget.setVisible(true);
        labelsi.setVisible(true);
        labelsu.setVisible(false);
        textField1.setVisible(false);
        textpassword2.setVisible(false);
        textpassword3.setVisible(false);
        transition2.setDuration(Duration.seconds(0.5));
        transition2.setNode(banner2);
        transition2.setByX(300);
        transition2.play();

        textfield.setText("");
        textField1.setText("");
        textpassword1.setText("");
        textpassword2.setText("");
        textpassword3.setText("");
    }
    private void changeToForget(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("ViewOTP.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void signInNow(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            if ( textfield.getText() == null || textfield.getText().trim().isEmpty()
                || textpassword1.getText() == null || textpassword1.getText().trim().isEmpty()) {
                alert.setTitle("Information Dialog");
                alert.setContentText("Ô nhập không được để trống");
                alert.showAndWait();
            } else {
                CL_LoginInformation cl_loginInformation = new CL_LoginInformation(textfield.getText(),textpassword1.getText());
                SV_CheckLogin svCheckLogin = RequestLogin.requestLogin(cl_loginInformation);
                if (svCheckLogin.getCheck()) {
                    ChangedSceneToHome.ChangeScene(event,"/Controller/BaseProject/ViewHome.fxml","Home",svCheckLogin.getIdUser());
                } else {
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Sai tài khoản hoặc mật khẩu");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void signUpNow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            if (textField1.getText() == null || textField1.getText().trim().isEmpty() ||
                    textfield.getText() == null || textfield.getText().trim().isEmpty() ||
                    textpassword2.getText() == null || textpassword2.getText().trim().isEmpty() ||
                    textpassword3.getText() == null || textpassword3.getText().trim().isEmpty()) {
                alert.setTitle("Information Dialog");
                alert.setContentText("Ô nhập không được để trống");
                alert.showAndWait();
            } else {
                String regexForMail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
                String regexForText = "^[a-zA-Z0-9]+$";

                if (!textField1.getText().matches(regexForMail)) {
                    textField1.setText("");
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Yêu cầu nhập đúng định dạng mail");
                    alert.showAndWait();
                } else if (!textfield.getText().matches(regexForText)) {
                    textfield.setText("");
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Không được có khoảng trắng hay kí tự đặc biệt");
                    alert.showAndWait();
                } else if (!textpassword2.getText().matches(regexForText) || !textpassword3.getText().matches(regexForText)) {
                    textpassword2.setText("");
                    alert.setTitle("Information Dialog");
                    alert.setContentText("không được có khoảng trắng hay kí tự đặc biệt");
                    alert.showAndWait();
                } else {
                    CL_RegisterInformation cl_registerInformation = new CL_RegisterInformation();
                    cl_registerInformation.setUsername(textfield.getText());
                    cl_registerInformation.setEmail(textField1.getText());
                    if (textpassword2.getText().equals(textpassword3.getText())) {
                        cl_registerInformation.setPassword(textpassword2.getText());
                        String check = RequestRegister.requestRegister(cl_registerInformation);
                        if (check.equals("dang ky thanh cong")) {
                            alert.setTitle("Information Dialog");
                            alert.setContentText("Đăng Ký Thành Công");
                            alert.showAndWait();
                        } else {
                            alert.setTitle("Information Dialog");
                            alert.setContentText("Lỗi hệ thống, đang khắc phục");
                            alert.showAndWait();
                        }
                    } else {
                        alert.setTitle("Information Dialog");
                        alert.setContentText("Mật khẩu không khớp");
                        alert.showAndWait();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
