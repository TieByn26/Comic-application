package Controller.BaseProject;

import ObjectGson.GsonForServer.SV_Chapter;
import RequestForServer.GetData.GetInformationChapter;
import RequestForServer.PostData.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PreviewComicsController {

    private ExecutorService executors;
    private ConcurrentHashMap<Integer, Image> imgMap;
    private String limkImg;
    private String nameComic;

    @FXML
    private Text PV_nameComic;
    @FXML
    private TilePane PV_listImage;

    public void uploadImageOfChapter() {
        // Khởi tạo ConcurrentHashMap để lưu trữ các ảnh
        imgMap = new ConcurrentHashMap<>();
        // Khởi tạo ExecutorService với 15 luồng
        executors = Executors.newFixedThreadPool(15);

        //list img sau khi xu li
        String listImg[] = splitString(limkImg);
        int index = 0;  // vi tri cua anh
        //load list anh
        for (String img : listImg) {
            int currentIndex = index;
            executors.execute(() -> loadImage(currentIndex, img));
            index++;
            System.out.println(img);
        }
        // Chờ tất cả các tác vụ hoàn thành
        executors.shutdown();
        try {
            executors.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < index; i++) {
            ImageView imageView = new ImageView();
            imageView.setImage(imgMap.get(i));
            imageView.setFitHeight(680);
            imageView.setFitWidth(650);
            PV_listImage.getChildren().add(imageView);
        }
    }

    public static String[] splitString(String input) { // tra ve 1 mang img
        if (input == null || input.isEmpty()) {
            return new String[0];
        }
        return input.split(",");
    }

    private void loadImage(int index, String imageUrl) {
        try {
            Image image = new Image(imageUrl);
            imgMap.put(index, image);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setInfNameComic () {
        PV_nameComic.setText(nameComic);
    }

    public String getLimkImg() {
        return limkImg;
    }

    public void setLimkImg(String limkImg) {
        this.limkImg = limkImg;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }
}
