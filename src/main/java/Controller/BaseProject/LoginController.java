package Controller.BaseProject;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        button3.setOnAction(actionEvent -> signInNow());
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
        transition1.setByX(400);
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
        transition1.setByX(-400);
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
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("ViewCapcha.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void signInNow(){

    }
    private void signUpNow(){

    }
}
