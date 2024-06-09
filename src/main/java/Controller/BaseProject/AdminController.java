package Controller.BaseProject;

import ObjectGson.GsonForServer.SV_ComicsInformation;
import ObjectGson.GsonForServer.SV_ListComicsInformations;
import ObjectGson.GsonForServer.SV_ListStatistic;
import ObjectGson.GsonForServer.SV_Statistic;
import RequestForServer.GetData.GetAllComic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class AdminController {
    @FXML
    Button notifiButton;
    @FXML
    Button listComicButton;
    @FXML
    Button comicUserButton;
    @FXML
    Button newComicButton;
    @FXML
    Button reviewButton;
    @FXML
    Button deleteButton;
    @FXML
    Button acceptButton;
    @FXML
    Button editButton;
    @FXML
    Button reviewButton2;
    @FXML
    Label nameComic;
    @FXML
    Label author;
    @FXML
    Label category;
    @FXML
    Label numView;
    @FXML
    Label numChapter;
    @FXML
    Label status;
    @FXML
    Text description;
    @FXML
    ImageView imageComic;
    @FXML
    TableView<SV_ComicsInformation> tableListComic;
    @FXML
    TableColumn<SV_ComicsInformation, String> colum1;
    @FXML
    TableColumn<SV_ComicsInformation, String> colum2;
    private static SV_ListComicsInformations sv_listComicsInformations;
    private static SV_ListStatistic sv_listStatistic;

    public static SV_ListComicsInformations getSv_listComicsInformations() {
        return sv_listComicsInformations;
    }

    public static void setSv_listComicsInformations(SV_ListComicsInformations sv_listComicsInformations) {
        AdminController.sv_listComicsInformations = sv_listComicsInformations;
    }

    public static SV_ListStatistic getSv_listStatistic() {
        return sv_listStatistic;
    }

    public static void setSv_listStatistic(SV_ListStatistic sv_listStatistic) {
        AdminController.sv_listStatistic = sv_listStatistic;
    }

    @FXML
    public void initialize(){
        // Thiết lập cellValueFactory cho các cột
        colum1.setCellValueFactory(new PropertyValueFactory<>("idComic"));
        colum2.setCellValueFactory(new PropertyValueFactory<>("nameComic"));
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
        setDataOnScreen();
        listComicOnTable();
        notifiButton.setOnAction(actionEvent -> changeNotification());
        listComicButton.setOnAction(actionEvent -> listComicOnTable());
        editButton.setOnAction(actionEvent -> changeEdit());
        comicUserButton.setOnAction(actionEvent -> listComicUserOnTable());
    }

    public void changeNotification() {
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
    public void changeEdit(){
        try {
            FXMLLoader loader = new FXMLLoader(HomeApp.class.getResource("ViewAdminEdit.fxml"));
            Scene scene = new Scene(loader.load(), 740, 590);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleForRow(SV_ComicsInformation rowData){
        // tao hasmap 1 key 1 value
        Map<String, SV_ComicsInformation> comicsInfoMap = new HashMap<>();
        for (SV_ComicsInformation sv_comicsInformation : sv_listComicsInformations.getListComicsInfomations()) {
            comicsInfoMap.put(sv_comicsInformation.getIdComic(), sv_comicsInformation);
        }
        Map<String, SV_Statistic> statisticsMap = new HashMap<>();
        for (SV_Statistic sv_statistic : sv_listStatistic.getListStatistic()) {
            statisticsMap.put(sv_statistic.getIdCommic(), sv_statistic);
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
        }
        SV_Statistic statistic = statisticsMap.get(rowData.getIdComic());
        if (statistic != null) {
            numView.setText(String.valueOf(statistic.getAllView()));
        }
    }

    public void listComicOnTable(){
        colum1.setText("ID Truyện");
        colum2.setText("Tên Truyện");
        ObservableList<SV_ComicsInformation> data = FXCollections.observableArrayList();
        for (SV_ComicsInformation sv_comicsInformation : sv_listComicsInformations.getListComicsInfomations()) {
            data.add(sv_comicsInformation);
        }
        tableListComic.setItems(data);
        editButton.setVisible(true);
        reviewButton2.setVisible(true);
        reviewButton.setVisible(false);
        acceptButton.setVisible(false);
        deleteButton.setVisible(false);
    }

    public void listComicUserOnTable(){
        editButton.setVisible(false);
        reviewButton2.setVisible(false);
        reviewButton.setVisible(true);
        acceptButton.setVisible(true);
        deleteButton.setVisible(true);
        nameComic.setText("Admin Siêu Cấp Đẹp Trai");
        author.setText("Admin");
        category.setText("Phiêu Lưu");
        numView.setText("1.000.000");
        numChapter.setText("100");
        status.setText("Hoàn thành");
        description.setText("Không có mô tả");
    }

    public void setDataOnScreen(){
        nameComic.setText("Admin Siêu Cấp Đẹp Trai");
        author.setText("Admin");
        category.setText("Phiêu Lưu");
        numView.setText("1.000.000");
        numChapter.setText("100");
        status.setText("Hoàn thành");
        description.setText("Không có mô tả");
        sv_listComicsInformations = GetAllComic.getAllComic();
        sv_listStatistic = GetAllComic.getAllView();
    }
}
