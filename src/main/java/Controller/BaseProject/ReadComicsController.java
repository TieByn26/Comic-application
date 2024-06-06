package Controller.BaseProject;

import General.EvenOfNav;
import ObjectGson.GsonForServer.SV_Chapter;
import ObjectGson.GsonForServer.SV_User;
import ObjectGson.GsonForServer.SV_Comments;
import RequestForServer.GetData.GetInformationChapter;
import RequestForServer.GetData.GetInformationComment;
import RequestForServer.GetData.GetInformationUser;
import RequestForServer.PostData.Comment;
import RequestForServer.PostData.History;
import RequestForServer.PostData.Statistics;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReadComicsController {

    private String pathPaneComments = "/Controller/BaseProject/ViewPaneComment.fxml";

    @FXML
    private Label nav_notfications;

    @FXML
    private Label nav_history;

    @FXML
    private Label nav_follow;

    @FXML
    private ImageView home_iconProfile;
    @FXML
    private ImageView RC_back;
    @FXML
    private ImageView RC_next;

    @FXML
    private Label nav_category;

    @FXML
    private Label nav_home;

    @FXML
    private TilePane RC_listComments;
    @FXML
    private TilePane RC_listImages;

    @FXML
    private TextArea RC_formInputComment;
    @FXML
    private Button RC_btnSubmit;
    @FXML
    private Text RC_chapter;
    @FXML
    private Text RC_nameComics;
    @FXML
    private ScrollPane TL_scroll_ListNotifications;
    @FXML
    private ScrollPane RC_scrollListComment;
    @FXML
    private ScrollPane RC_scrollListImg;
    @FXML
    private ChoiceBox RC_listChapter;
    private String idcomics;
    private int idUser;
    private int idComment;
    private int chapter = 1;
    private int numberOfChapter = 6;
    private String nameComics;
    private int allView;
    @FXML
    private TilePane TL_listCategory;
    @FXML
    private ScrollPane TL_scroll_ListCategory;
    boolean isLiked = false;
    boolean isDislike = false;

    ExecutorService executors;
    ConcurrentHashMap<Integer, Image> imgMap;
    //tao doi tuong EvenOfNav moi de cap nhan lai bien isHide_listCategory moi khi chuyen scene
    EvenOfNav evenOfNav = new EvenOfNav();

    public void initialize() throws Exception {
    }
    public void setEventForNav (){
        //set event click for nav_category
        evenOfNav.setEventForNavCategory(nav_category, TL_listCategory,TL_scroll_ListCategory,idUser);
        //set event click for nav_follow
        EvenOfNav.setEventForNavFollow(nav_follow,idUser);
        //set event click for nav_history
        EvenOfNav.setEventForNavHistory(nav_history,idUser);
        //set event click for nav_notification
        EvenOfNav.setEventForNavNotifications(nav_notfications);
        //set event click for nav_home
        EvenOfNav.setEventForNavHome(nav_home,idUser);
    }

    // upload cac anh cua chapter len giao dien theo co
    public void uploadImageOfChapter() {
        //lam moi lai list tryuen
        RC_listImages.getChildren().clear();
        // set lai vi tri ban dau cho scroll
        RC_scrollListImg.setVvalue(0);

        SV_Chapter chapterInformation = GetInformationChapter.getAllimageOfChapter(idcomics, chapter);
        // Khởi tạo ConcurrentHashMap để lưu trữ các ảnh
        imgMap = new ConcurrentHashMap<>();
        // Khởi tạo ExecutorService với 15 luồng
        executors = Executors.newFixedThreadPool(15);

        //list img sau khi xu li
        String listImg[] = splitString(chapterInformation.getLinkImage());
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
            imageView.setFitHeight(780);
            imageView.setFitWidth(650);
            RC_listImages.getChildren().add(imageView);
        }

        //cap nhat lai lich su truyen
        updateListHistory();
    }

    public void setDataForComment() {
        //lam moi lai list comment
        RC_listComments.getChildren().clear();

        ArrayList<SV_Comments> listComment = GetInformationComment.getAllComment(idcomics + "");

        for (SV_Comments comment : listComment) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathPaneComments));
            Parent paneComment = null;
            try {
                paneComment = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // lay thong tin user cho tung comment
            SV_User userInformation = GetInformationUser.getInforUserForComment(comment.getIdUser());

            ImageView CM_imgLike = (ImageView) paneComment.lookup("#CM_imgLike");
            ImageView CM_imgDislike = (ImageView) paneComment.lookup("#CM_imgDislike");
            ImageView CM_avatar = (ImageView) paneComment.lookup("#CM_avatar");
            Text CM_name = (Text) paneComment.lookup("#CM_name");
            Text CM_NumberOflike = (Text) paneComment.lookup("#CM_NumberOflike");
            Text CM_NumberOfDislike = (Text) paneComment.lookup("#CM_NumberOfDislike");
            ScrollPane CM_scrollComment = (ScrollPane) paneComment.lookup("#CM_scrollComment");
            Text CM_comment = (Text) CM_scrollComment.getContent().lookup("#CM_comment");

            CM_NumberOfDislike.setText(comment.getDislike() + "");
            CM_NumberOflike.setText(comment.getLike() + "");
            CM_comment.setText(comment.getComment());
            Image avatarUser = new Image(userInformation.getAvatar());
            CM_avatar.setImage(avatarUser);
            CM_name.setText(userInformation.getFullName());
            // su kien click like
            CM_imgLike.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isLiked == false) { //nhan nut like lan dau
                        CM_NumberOflike.setText(comment.getLike() + 1 + "");
                        isLiked = true;
                        //goi gam cap nhat lai so like
                        int statusUpdate = Comment.updateNumberOfLike(comment.getIdComment(), Integer.parseInt(CM_NumberOflike.getText()));
                        if (statusUpdate == 0) {
                            CM_NumberOflike.setText(comment.getLike() + "");
                            isLiked = false;

                            showAlert(Alert.AlertType.WARNING, "Thông báo", "like không thành công");
                        }
                    } else {   // nhan nut like lan 2
                        CM_NumberOflike.setText(comment.getLike() + "");
                        isLiked = false;

                        //goi gam cap nhat lai so like
                        int statusUpdate = Comment.updateNumberOfLike(comment.getIdComment(), Integer.parseInt(CM_NumberOflike.getText()));
                        if (statusUpdate == 0) {
                            CM_NumberOflike.setText(comment.getLike() + 1 + ""); // reset lai so luot like
                            isLiked = false;

                            showAlert(Alert.AlertType.WARNING, "Thông báo", "like không thành công");
                        }
                    }
                }
            });
            // su kien click dislike
            CM_imgDislike.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isDislike == false) { // nhan dislike lan 1
                        CM_NumberOfDislike.setText(comment.getDislike() + 1 + "");
                        isDislike = true;
                        //goi gam cap nhat lai so dislike
                        int statusUpdate = Comment.updateNumberOfDislike(comment.getIdComment(), Integer.parseInt(CM_NumberOfDislike.getText()));
                        if (statusUpdate == 0) {
                            CM_NumberOfDislike.setText(comment.getDislike() + ""); // reset lai so luot dislike
                            isDislike = false;
                            showAlert(Alert.AlertType.WARNING, "Thông báo", "dislike không thành công");
                        }

                    } else {  // nhan dislike lan 2
                        CM_NumberOfDislike.setText(comment.getDislike() + "");
                        isDislike = false;
                        //goi gam cap nhat lai so dislike
                        int statusUpdate = Comment.updateNumberOfDislike(comment.getIdComment(), Integer.parseInt(CM_NumberOfDislike.getText()));
                        if (statusUpdate == 0) {
                            CM_NumberOfDislike.setText(comment.getDislike() + 1 + ""); // resset lai luot dislike
                            isDislike = false;
                            showAlert(Alert.AlertType.WARNING, "Thông báo", "dislike không thành công");
                        }
                    }
                }
            });

            //cap nhat lai idComment bang idComment cuoi cung
            idComment = comment.getIdComment();

            // them tung comment vao list comment
            RC_listComments.getChildren().add(paneComment);
        }
        RC_scrollListComment.layout(); // Yêu cầu ScrollPane bố trí lại các phần tử
        RC_scrollListComment.setVvalue(1.0); // Cuộn xuống dưới cùng
    }

    public void eventCreateNewComment() {
        RC_btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int statusUpdate = Comment.createNewComment(idcomics, idUser, RC_formInputComment.getText());
                if (statusUpdate > 0) {
                    setDataForComment();
                    //lam moi o nhap comment
                    RC_formInputComment.setText("");
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Bình luân lỗi");
                }
            }
        });
        RC_formInputComment.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume(); // Ngăn chặn hành động mặc định của phím Enter
                int statusUpdate = Comment.createNewComment(idcomics, idUser, RC_formInputComment.getText());
                if (statusUpdate > 0) {
                    setDataForComment();
                    //lam moi o nhap comment
                    RC_formInputComment.setText("");
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Bình luân lỗi");
                }
            }
        });
    }
    public void setComicsInformation() {
        RC_nameComics.setText(nameComics);
        RC_chapter.setText(chapter + "");

        for(int i = 1; i <= numberOfChapter; i++) {
            RC_listChapter.getItems().add("chapter: " + i);
        }

        //gia tri mac dinh la chapter dang doc
        RC_listChapter.setValue(RC_listChapter.getItems().get(chapter - 1)); // phan tu trong choicebox chay tu vi tri 0

        // Xử lý sự kiện khi lựa chọn thay đổi
        RC_listChapter.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            String chapterStr = newValue.toString();
            String subChapterStr = chapterStr.substring(9);
            //cap nhat lai chapter
            chapter = Integer.parseInt(subChapterStr);
            //cap nhat lai chapter tren giao dien
            RC_chapter.setText(chapter+"");
            //load lai anh cua chapter
            uploadImageOfChapter();
        });

    }
    public void setEventForIconBackAndNext() {
        RC_next.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (chapter + 1  <= numberOfChapter) {
                    chapter++; //cap nhat lai chapter
                    RC_chapter.setText(chapter+"");
                    RC_listChapter.setValue(RC_listChapter.getItems().get(chapter - 1)); // phan tu trong choicebox chay tu vi tri 0
                    uploadImageOfChapter();
                }
                else  {
                    showAlert(Alert.AlertType.ERROR,"Không thể next", "hết chapter!");
                }
            }
        });
        RC_back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (chapter -1 > 0) {
                    chapter--;
                    RC_chapter.setText(chapter+"");
                    RC_listChapter.setValue(RC_listChapter.getItems().get(chapter - 1)); // phan tu trong choicebox chay tu vi tri 0
                    uploadImageOfChapter();

                }
                else  {
                    showAlert(Alert.AlertType.ERROR,"Không thể back", "hết chapter!");
                }
            }
        });
    }
    public void updateView() {
      int statusUpdate = Statistics.updateView(idcomics,allView + 1);
      if(statusUpdate > 0) {
          System.out.println("update view success");
      }
      else {
          System.out.println("update view fail");
      }
    }
public void updateListHistory () {
        if(History.updateChapterOfHistory(idcomics,idUser,chapter) > 0) {
            System.out.println("update history success");
        }
        else {
            System.out.println("update history fail");
        }
}
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String[] splitString(String input) { // tra ve 1 mang img
        if (input == null || input.isEmpty()) {
            return new String[0];
        }
        return input.split(",");
    }

        private void loadImage(int index, String imageUrl) {
            try {
               Image image =  new Image(imageUrl);
               imgMap.put(index,image);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
    }

//    private void loadImage(int index, String imageUrl) {
//        executors.execute(() -> {
//            try {
//                URL url = new URL(imageUrl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestProperty("referer", "https://www.nettruyenmck.com/");
//                try (InputStream inputStream = connection.getInputStream()) {
//                    Image image = new Image(inputStream);
//                    imgMap.put(index, image);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//
//        });
//    }
public String getIdcomics() {
    return idcomics;
}

    public void setIdcomics(String idcomics) {
        this.idcomics = idcomics;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getNameComics() {
        return nameComics;
    }

    public void setNameComics(String nameComics) {
        this.nameComics = nameComics;
    }

    public int getNumberOfChapter() {
        return numberOfChapter;
    }

    public void setNumberOfChapter(int numberOfChapter) {
        this.numberOfChapter = numberOfChapter;
    }

    public int getAllView() {
        return allView;
    }

    public void setAllView(int allView) {
        this.allView = allView;
    }
}