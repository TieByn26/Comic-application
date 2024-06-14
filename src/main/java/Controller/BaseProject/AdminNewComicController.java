package Controller.BaseProject;

import ChangeScene.ChangedSceneToPreviewComic;
import ObjectGson.GsonForServer.SV_CategoryComics;
import ObjectGson.GsonForServer.SV_CategoryManager;
import ObjectGson.GsonForServer.SV_Chapter;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import RequestForServer.PostData.RequestAddComic;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminNewComicController {
    @FXML
    private TextField textIdComic;
    @FXML
    private TextField textNameComic;
    @FXML
    private TextField textChapter;
    @FXML
    private TextField textAvatar;
    @FXML
    private TextArea textDescription;
    @FXML
    private TextArea textLinkChapter;
    @FXML
    private ChoiceBox<String> choiceStatus;
    @FXML
    private ChoiceBox<String> choiceCategory;
    @FXML
    private Button buttonReviewAvatar;
    @FXML
    private Button buttonAddNew;
    @FXML
    private Button buttonReview;
    @FXML
    private ImageView viewAvatar;
    private SV_ComicsInformation sv_comicsInformation = new SV_ComicsInformation();
    private SV_Chapter sv_chapter = new SV_Chapter();
    private SV_CategoryManager sv_categoryManager = new SV_CategoryManager();
    private SV_CategoryComics sv_categoryComics = new SV_CategoryComics();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    public void initialize(){
        choiceBoxSetValue();
        buttonAddNew.setOnAction(event -> addNewComic());
        buttonReviewAvatar.setOnAction(event -> reviewAvatar());
        buttonReview.setOnMouseClicked(this::reviewComic);
    }

    public void choiceBoxSetValue(){
        choiceStatus.getItems().addAll("Đã hoàn thành","Chưa hoàn thành");
        choiceStatus.setValue("Chưa hoàn thành");
        choiceCategory.getItems().addAll("manhua","manga", "xuyên không","anime","Cổ đại","Shoujo Ai","Ngôn Tình","Martial Arts","Isekai","Action","Seinen","Sports","School Life");
        choiceCategory.setValue("Vui lòng chọn thể loại");
    }
    public void reviewAvatar(){
        Image image = new Image(textAvatar.getText());
        viewAvatar.setImage(image);
    }
    public void reviewComic(MouseEvent event){
        ChangedSceneToPreviewComic.ChangeScene(event,"/Controller/BaseProject/ViewPreviewComics.fxml","ReviewComic",textLinkChapter.getText(),textNameComic.getText());
    }
    public void addNewComic(){
        //thong tin truyen
        sv_comicsInformation.setIdComic(textIdComic.getText());
        sv_comicsInformation.setAvatarComic(textAvatar.getText());
        sv_comicsInformation.setNameComic(textNameComic.getText());
        sv_comicsInformation.setAuthor("Admin");
        sv_comicsInformation.setDescription(textDescription.getText());
        sv_comicsInformation.setStatus(choiceStatus.getValue());
        //thong tin chapter
        sv_chapter.setIdComic(textIdComic.getText());
        sv_chapter.setChapter(Integer.parseInt(textChapter.getText()));
        sv_chapter.setLinkImage(textLinkChapter.getText());
        //thong tin the loai
        sv_categoryManager.setIdComic(textIdComic.getText());
        sv_categoryComics.setCategoryName(choiceCategory.getValue());

        //gửi yêu cầu thêm truyện
        String check = RequestAddComic.addNewComic(sv_comicsInformation, sv_chapter, sv_categoryManager,sv_categoryComics);
        if (check.equals("true")) {
            alert.setTitle("Information Dialog");
            alert.setContentText("Thêm truyện thành công");
            alert.showAndWait();
        } else {
            alert.setTitle("Information Dialog");
            alert.setContentText("Thêm truyện không thành công");
            alert.showAndWait();
        }
    }
}
