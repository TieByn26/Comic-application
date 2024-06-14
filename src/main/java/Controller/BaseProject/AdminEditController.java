package Controller.BaseProject;

import ChangeScene.ChangedSceneToPreviewComic;
import ObjectGson.GsonForServer.*;
import RequestForServer.PostData.RequestDeleteComic;
import RequestForServer.PostData.RequestUpdateComic;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class AdminEditController {
    @FXML
    private TextField textIdComic;
    @FXML
    private TextField textCategory;
    @FXML
    private TextField textNameComic;
    @FXML
    private TextField textAvatar;
    @FXML
    private TextArea textDescription;
    @FXML
    private TextArea textLinkChapter;
    @FXML
    private ChoiceBox<String> choiceStatus;
    @FXML
    private ChoiceBox<String> chooseChapter;
    @FXML
    private Button buttonReviewAvatar;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonReview;
    @FXML
    private ImageView viewAvatar;
    private SV_ComicsInformation sv_comicsInformation = AdminController.sv_comicsInformation;
    private SV_Chapter sv_chapter = new SV_Chapter();
    private SV_CategoryManager sv_categoryManager = AdminController.sv_categoryManager;
    private SV_CategoryComics sv_categoryComics = AdminController.sv_categoryComics;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private ArrayList<SV_Chapter> listChapter = AdminController.listChapter;

    @FXML
    public void initialize(){
        setDataOnScreen();
        choiceBoxSetValue();
        listChoiceChapter();
        viewAvatar();
        buttonReviewAvatar.setOnAction(event -> viewAvatar());
        buttonDelete.setOnAction(event -> deleteComic());
        buttonUpdate.setOnAction(event -> updateComic());
        buttonReview.setOnMouseClicked(this::reviewComic);
    }
    public void viewAvatar(){
        Image image = new Image(textAvatar.getText());
        viewAvatar.setImage(image);
    }
    public void choiceBoxSetValue(){
        choiceStatus.getItems().addAll("Đã hoàn thành","Chưa hoàn thành");
        choiceStatus.setValue("Chưa hoàn thành");
    }
    public void setDataOnScreen(){
        textIdComic.setText(sv_comicsInformation.getIdComic());
        textAvatar.setText(sv_comicsInformation.getAvatarComic());
        textCategory.setText(sv_categoryComics.getCategoryName());
        textDescription.setText(sv_comicsInformation.getDescription());
        textNameComic.setText(sv_comicsInformation.getNameComic());
    }
    public void deleteComic(){
        sv_comicsInformation.setIdComic(textIdComic.getText());
        String check = RequestDeleteComic.deleleComic(sv_comicsInformation, sv_chapter);
        if (check.equals("true")) {
            alert.setTitle("Information Dialog");
            alert.setContentText("Xóa truyện thành công");
            alert.showAndWait();
        } else {
            alert.setTitle("Information Dialog");
            alert.setContentText("Xóa truyện không thành công");
            alert.showAndWait();
        }
    }
    public void updateComic(){
        //thong tin truyen
        sv_comicsInformation.setIdComic(textIdComic.getText());
        sv_comicsInformation.setAvatarComic(textAvatar.getText());
        sv_comicsInformation.setNameComic(textNameComic.getText());
        sv_comicsInformation.setDescription(textDescription.getText());
        sv_comicsInformation.setStatus(choiceStatus.getValue());
        //thong tin chapter
        sv_chapter.setIdComic(textIdComic.getText());
        sv_chapter.setChapter(Integer.parseInt(chooseChapter.getValue()));
        sv_chapter.setLinkImage(textLinkChapter.getText());
        //thong tin the loai
        sv_categoryManager.setIdComic(textIdComic.getText());
        sv_categoryManager.setIdCategory(textCategory.getText());

        //gui yeu cau update
        String check = RequestUpdateComic.updateComic(sv_comicsInformation,sv_chapter,sv_categoryManager);
        if (check.equals("true")){
            alert.setTitle("Information Dialog");
            alert.setContentText("Update truyện thành công");
            alert.showAndWait();
        } else {
            alert.setTitle("Information Dialog");
            alert.setContentText("Update truyện không thành công");
            alert.showAndWait();
        }
    }
    public void listChoiceChapter(){
        int id = 1;
        for (SV_Chapter svChapter : listChapter){
            id++;
            chooseChapter.getItems().addAll(svChapter.getChapter()+"");
        }
        chooseChapter.getItems().addAll(id+"");
        chooseChapter.setValue(id+"");
        chooseChapter.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                for (SV_Chapter svChapter : listChapter) {
                    String check = String.valueOf(svChapter.getChapter());
                    if (newValue.equals(check)) {
                        sv_chapter.setChapter(svChapter.getChapter());
                        System.out.println(sv_chapter);
                        textLinkChapter.setText(svChapter.getLinkImage());
                        break;
                    }
                }
            }
        });
    }
    public void reviewComic(MouseEvent event){
        ChangedSceneToPreviewComic.ChangeScene(event,"/Controller/BaseProject/ViewPreviewComics.fxml","ReviewComic",textLinkChapter.getText(),textNameComic.getText());
    }
}
