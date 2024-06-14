package Controller.BaseProject;

import ChangeScene.ChangedSceneToPreviewComic;
import ObjectGson.GsonForClient.CL_UsernameToNotifi;
import ObjectGson.GsonForServer.*;
import RequestForServer.GetData.GetAllComic;
import RequestForServer.GetData.GetRequestOfUser;
import RequestForServer.PostData.RequestAddComic;
import RequestForServer.PostData.RequestDeleteComicUser;
import RequestForServer.PostData.SendNotification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminController {
    @FXML
    private TextField textIdComic;
    @FXML
    private Button notifiButton;
    @FXML
    private Button listComicButton;
    @FXML
    private Button comicUserButton;
    @FXML
    private Button newComicButton;
    @FXML
    private Button reviewButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button acceptButton;
    @FXML
    private Button editButton;
    @FXML
    private Button reviewButton2;
    @FXML
    private Label nameComic;
    @FXML
    private Label author;
    @FXML
    private Label category;
    @FXML
    private  Label numView;
    @FXML
    private Label numChapter;
    @FXML
    private Label status;
    @FXML
    private Text description;
    @FXML
    private ImageView imageComic;
    @FXML
    private TableView<SV_ComicsInformation> tableListComic;
    @FXML
    private TableView<SV_ComicOfUser> tableListUserComic;
    @FXML
    private TableColumn<SV_ComicOfUser, String> columnUser1;
    @FXML
    private TableColumn<SV_ComicOfUser, String> columnUser2;
    @FXML
    private TableColumn<SV_ComicsInformation, String> colum1;
    @FXML
    private TableColumn<SV_ComicsInformation, String> colum2;
    //du lieu chinh
    private SV_ListComicsInformations sv_listComicsInformations;
    private SV_ListStatistic sv_listStatistic;
    private SV_ListComicOfUser sv_listComicOfUser;
    private SV_ListChapter sv_listChapter;
    private SV_ListCategoryManager sv_listCategoryManager;
    private SV_ListCategoryComic sv_listCategoryComic;
    //du lieu phu
    public static SV_ComicsInformation sv_comicsInformation;
    public static SV_Chapter sv_chapter;
    public static SV_Statistic sv_statistic;
    public static SV_CategoryManager sv_categoryManager;
    public static SV_CategoryComics sv_categoryComics;
    //du lieu phu
    public static ArrayList<SV_Chapter> listChapter = new ArrayList<>();
    private SV_ComicOfUser sv_comicOfUser;

    @FXML
    public void initialize(){
        // Thiết lập cellValueFactory cho các cột
        colum1.setCellValueFactory(new PropertyValueFactory<>("idComic"));
        colum2.setCellValueFactory(new PropertyValueFactory<>("nameComic"));
        columnUser1.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnUser2.setCellValueFactory(new PropertyValueFactory<>("nameComic"));
        //lay toan bo du lieu ve
        setSizeTable();
        setDataOnScreen();
        listComicOnTable();
        //thiet lap su kien cho cac nut
        notifiButton.setOnAction(actionEvent -> changeNotification());
        listComicButton.setOnAction(actionEvent -> listComicOnTable());
        editButton.setOnAction(actionEvent -> changeEdit());
        comicUserButton.setOnAction(actionEvent -> listComicUserOnTable());
        newComicButton.setOnAction(event -> changeAddNew());
        deleteButton.setOnAction(this::deleteComicOfUser);
        acceptButton.setOnAction(event -> acceptComicOfUser());
        reviewButton.setOnMouseClicked(this::reviewComic1);
    }
    public void setSizeTable(){ // thay doi kich co , thuc hien lang nghe su kien
        // Tạo một RowFactory tùy chỉnh để thay đổi chiều cao của các hàng
        tableListComic.setRowFactory(tv -> {
            TableRow<SV_ComicsInformation> row = new TableRow<>();
            row.setPrefHeight(50);
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    SV_ComicsInformation rowData = row.getItem();
                    handleForRow(rowData);
                }
            });
            return row;
        });
        // Tạo một RowFactory tùy chỉnh để thay đổi chiều cao của các hàng
        tableListUserComic.setRowFactory(tv -> {
            TableRow<SV_ComicOfUser> row = new TableRow<>();
            row.setPrefHeight(50);
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    SV_ComicOfUser rowData = row.getItem();
                    handleForRowUser(rowData);
                }
            });
            return row;
        });
    }
    public void changeAddNew(){ // doi sang man hinh them truyen
        try {
            FXMLLoader loader = new FXMLLoader(HomeApp.class.getResource("ViewAdminNewComic.fxml"));
            Scene scene = new Scene(loader.load(), 1000, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeNotification() { // doi sang man hinh thong bao
        try {
            FXMLLoader loader = new FXMLLoader(HomeApp.class.getResource("ViewAdminNotification.fxml"));
            Scene scene = new Scene(loader.load(), 450, 420);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void changeEdit(){//doi sang man hinh edit truyen
        try {
            FXMLLoader loader = new FXMLLoader(HomeApp.class.getResource("ViewAdminEdit.fxml"));
            Scene scene = new Scene(loader.load(), 1000, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleForRowUser(SV_ComicOfUser rowData){ // xu ly nhan vao table
        Map<String, SV_ComicOfUser> comicOfUserMap = new HashMap<>();
        for (SV_ComicOfUser sv_comicOfUser: sv_listComicOfUser.getListComicOfUser()){
            comicOfUserMap.put(sv_comicOfUser.getNameComic(), sv_comicOfUser);
        }
        sv_comicOfUser = comicOfUserMap.get(rowData.getNameComic());
        if (sv_comicOfUser != null){
            Image image = new Image(sv_comicOfUser.getAvatarComic());
            imageComic.setImage(image);
            nameComic.setText(sv_comicOfUser.getNameComic());
            author.setText(sv_comicOfUser.getAuthor());
            category.setText(sv_comicOfUser.getCategory());
            numView.setText(0+"");
            numChapter.setText(1+"");
            status.setText(sv_comicOfUser.getStatus());
            description.setText(sv_comicOfUser.getDescription());
        }
    }
    public void handleForRow(SV_ComicsInformation rowData){ // xu ly nhan vao table
        // tao hasmap 1 key 1 value
        Map<String, SV_ComicsInformation> comicsInfoMap = new HashMap<>();
        for (SV_ComicsInformation sv_comicsInformation : sv_listComicsInformations.getListComicsInfomations()) {
            comicsInfoMap.put(sv_comicsInformation.getIdComic(), sv_comicsInformation);
        }
        Map<String, SV_Statistic> statisticsMap = new HashMap<>();
        for (SV_Statistic sv_statistic : sv_listStatistic.getListStatistic()) {
            statisticsMap.put(sv_statistic.getIdCommic(), sv_statistic);
        }
        Map<String, SV_CategoryManager> categoryManagerMap = new HashMap<>();
        for (SV_CategoryManager sv_categoryManager : sv_listCategoryManager.getListCategoryManager()){
            categoryManagerMap.put(sv_categoryManager.getIdComic(),sv_categoryManager);
        }
        Map<String, SV_CategoryComics> categoryComicsMap = new HashMap<>();
        for (SV_CategoryComics sv_categoryComics : sv_listCategoryComic.getListCategoryComic()){
            categoryComicsMap.put(sv_categoryComics.getIdCategory(),sv_categoryComics);
        }
        // tim kiem theo key value
        SV_ComicsInformation comicInfo = comicsInfoMap.get(rowData.getIdComic());
        if (comicInfo != null) {
            Image image = new Image(comicInfo.getAvatarComic());
            imageComic.setImage(image);
            nameComic.setText(comicInfo.getNameComic());
            author.setText(comicInfo.getAuthor());
            numChapter.setText(String.valueOf(comicInfo.getNumberOfChapter()));
            status.setText(comicInfo.getStatus());
            description.setText(comicInfo.getDescription());
            sv_comicsInformation = comicInfo;
        }
        SV_Statistic statistic = statisticsMap.get(rowData.getIdComic());
        if (statistic != null) {
            numView.setText(String.valueOf(statistic.getAllView()));
            sv_statistic = statistic;
        }
        SV_CategoryManager svCategoryManager = categoryManagerMap.get(rowData.getIdComic());
        if (svCategoryManager != null) {
            SV_CategoryComics svCategoryComics = categoryComicsMap.get(svCategoryManager.getIdCategory());
            if (svCategoryComics != null) {
                category.setText(String.valueOf(svCategoryComics.getCategoryName()));
                sv_categoryManager = svCategoryManager;
                sv_categoryComics = svCategoryComics;
            }
        }
        //them du lieu vao array list
        listChapter.clear();
       for (SV_Chapter svChapter : sv_listChapter.getListChapter()){
           if (svChapter.getIdComic().equals(sv_comicsInformation.getIdComic())) {
               listChapter.add(svChapter);
           }
       }
    }

    public void listComicOnTable(){ //dua comic len table
        //lay du lieu truyen
        sv_listComicsInformations = GetAllComic.getAllComic();
        sv_listStatistic = GetAllComic.getAllView();
        sv_listChapter = GetAllComic.getAllChapter();
        sv_listCategoryManager = GetAllComic.getAllIdCategory();
        sv_listCategoryComic = GetAllComic.getAllNameCategory();
        //set ten cot
        colum1.setText("ID Truyện");
        colum2.setText("Tên Truyện");
        //dua truyen len table
        ObservableList<SV_ComicsInformation> data = FXCollections.observableArrayList();
        for (SV_ComicsInformation sv_comicsInformation : sv_listComicsInformations.getListComicsInfomations()) {
            data.add(sv_comicsInformation);
        }
        tableListComic.setItems(data);
        //an hien button
        editButton.setVisible(true);
        reviewButton2.setVisible(false);
        reviewButton.setVisible(false);
        acceptButton.setVisible(false);
        deleteButton.setVisible(false);
        tableListComic.setVisible(true);
        tableListUserComic.setVisible(false);
        textIdComic.setVisible(false);

    }

    public void listComicUserOnTable(){ //phuong thuc dua comic len man hinh
        sv_listComicOfUser = GetRequestOfUser.getRequestOfUser();
        editButton.setVisible(false);
        reviewButton2.setVisible(false);
        reviewButton.setVisible(true);
        acceptButton.setVisible(true);
        deleteButton.setVisible(true);
        tableListComic.setVisible(false);
        tableListUserComic.setVisible(true);
        textIdComic.setVisible(true);

        ObservableList<SV_ComicOfUser> data = FXCollections.observableArrayList();
        for (SV_ComicOfUser sv_comicOfUser : sv_listComicOfUser.getListComicOfUser()) {
            data.add(sv_comicOfUser);
        }
        tableListUserComic.setItems(data);
    }

    public void setDataOnScreen(){ // dua du lieu tam thoi
        nameComic.setText("Admin Siêu Cấp Đẹp Trai");
        author.setText("Admin");
        category.setText("Phiêu Lưu");
        numView.setText("1.000.000");
        numChapter.setText("100");
        status.setText("Hoàn thành");
        description.setText("Không có mô tả");
        Image image = new Image(getClass().getResource("/image/imgLogin.jpg").toExternalForm());
        imageComic.setImage(image);
    }
    public void deleteComicOfUser(ActionEvent event){
        //thong bao
        CL_UsernameToNotifi cl_usernameToNotifi = new CL_UsernameToNotifi();
        SV_Notification sv_notification = new SV_Notification();
        cl_usernameToNotifi.setUsername(sv_comicOfUser.getAuthor());
        sv_notification.setContent("Truyện "+sv_comicOfUser.getNameComic()+" không đạt yêu cầu nên tụi mình hủy rồi bạn nhé!");
        SendNotification.sendNotification(cl_usernameToNotifi,sv_notification);
        setDataOnScreen();
        listComicOnTable();
        RequestDeleteComicUser.deleleComicUser(sv_comicOfUser);

    }
    public void acceptComicOfUser(){
        //tao doi tuong
        SV_ComicsInformation sv_comicsInformation1 = new SV_ComicsInformation();
        SV_Chapter svChapter = new SV_Chapter();
        SV_CategoryManager svCategoryManager = new SV_CategoryManager();
        SV_CategoryComics svCategoryComics = new SV_CategoryComics();
        //set du lieu
        sv_comicsInformation1.setNameComic(sv_comicOfUser.getNameComic());
        sv_comicsInformation1.setIdComic(textIdComic.getText());
        sv_comicsInformation1.setAvatarComic(sv_comicOfUser.getAvatarComic());
        sv_comicsInformation1.setAuthor(sv_comicOfUser.getAuthor());
        sv_comicsInformation1.setStatus(sv_comicOfUser.getStatus());
        sv_comicsInformation1.setDescription(sv_comicOfUser.getDescription());
        sv_comicsInformation1.setNumberOfChapter(0);

        svChapter.setIdComic(textIdComic.getText());
        svChapter.setChapter(sv_comicOfUser.getChapterNumber());
        svChapter.setLinkImage(sv_comicOfUser.getImageChapter());

        svCategoryManager.setIdComic(textIdComic.getText());

        svCategoryComics.setCategoryName(sv_comicOfUser.getCategory());

        //thong bao
        CL_UsernameToNotifi cl_usernameToNotifi = new CL_UsernameToNotifi();
        SV_Notification sv_notification = new SV_Notification();
        cl_usernameToNotifi.setUsername(sv_comicOfUser.getAuthor());
        sv_notification.setContent("Truyện "+sv_comicOfUser.getNameComic()+" của bạn được duyệt rồi nhé!");
        SendNotification.sendNotification(cl_usernameToNotifi,sv_notification);
        //dang truyen
        RequestAddComic.addNewComic(sv_comicsInformation1,svChapter,svCategoryManager,svCategoryComics);
        //xoa khoi danh sach cho
        RequestDeleteComicUser.deleleComicUser(sv_comicOfUser);
    }
    public void reviewComic1(MouseEvent event){
        ChangedSceneToPreviewComic.ChangeScene(event,"/Controller/BaseProject/ViewPreviewComics.fxml","ReviewComic",sv_comicOfUser.getImageChapter(),sv_comicOfUser.getNameComic());
    }
    public void reviewComic2(MouseEvent event){
//        ChangedSceneToPreviewComic.ChangeScene();
    }
}
