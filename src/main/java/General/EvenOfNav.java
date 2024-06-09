package General;

import ChangeScene.*;
import ObjectGson.GsonForServer.SV_CategoryComics;
import RequestForServer.GetData.GetInformationCategory;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;


public class EvenOfNav {
    private static String pathFollow = "/Controller/BaseProject/ViewFollow.fxml";
    private static String pathNotifications = "/Controller/BaseProject/ViewNotifications.fxml";
    private static String pathHistory = "/Controller/BaseProject/ViewHistory.fxml";
    private static  String pathProfile = "/Controller/BaseProject/ViewProfile.fxml";
    private static  String pathHome = "/Controller/BaseProject/ViewHome.fxml";
    private static String pathUpComics = "/Controller/BaseProject/ViewUpComics.fxml";
    private boolean isHide_listCategory = false;

    public static  void setEventForNavFollow (Label navFollow, int idUser) {
        navFollow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                try {
                    ChangedSceneToFollow.ChangeScene(event, pathFollow, "Theo dõi",idUser);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static void setEventForNavHistory(Label navHistory,int idUser) {
        navHistory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                try {
                    ChangeSceneToHistory.ChangeScene(event, pathHistory, "Lịch sử",idUser);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void setEventForNavNotifications(Label nav_notifications,int idUser){
        nav_notifications.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                ChangeSceneToNotifications.ChangeScene(event, pathNotifications, "Thông báo",idUser);
            }
        });
    }

    public static void setEventForNavHome(Label nav_home, int idUser){
        nav_home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                ChangedSceneToHome.ChangeScene(event, pathHome, "Thông báo",idUser);
            }
        });
    }
    public static void setEventForProfile(ImageView avt,int idUser) {
        avt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ChangeSceneToProfile.ChangeScene(event,pathProfile,"Hồ Sơ Người Dùng",idUser);
            }
        });
    }
    public  void setEventForNavCategory(Label nav_category, TilePane list_category ,ScrollPane scrollCategory, int idUser) {
        nav_category.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // lay tat ca du lieu
                ArrayList<SV_CategoryComics> listCategory = GetInformationCategory.getAllCategoryInformation();
                // upload information category
                for (SV_CategoryComics category : listCategory) {
                    Text newCategory = new Text(category.getCategoryName());
                    newCategory.setWrappingWidth(230);
                    newCategory.setTextAlignment(TextAlignment.CENTER);
                    newCategory.setFont(Font.font("System", FontWeight.BOLD, 20));
                    newCategory.setFill(Color.rgb(0,	103,	107));
                    newCategory.setCursor(Cursor.HAND);
                    // set su kien click cho tung the loai
                    newCategory.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            ChangedSceneToHome.ChangeSceneRequestIdCategory(event,pathHome,"Home",idUser, category.getIdCategory(),category.getCategoryName());
                        }
                    });

                    list_category.getChildren().add(newCategory);
                }
                // hien thi box chua category
                if(isHide_listCategory == false) {
                    scrollCategory.setVisible(true);
                    isHide_listCategory = true;
                }
                else {
                    scrollCategory.setVisible(false);
                    isHide_listCategory = false;
                }
            }
        });
    }

    public static void setEventForNavUpComics(Label nav_UpComics,int idUser) {
        nav_UpComics.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ChangedSceneToUpComics.ChangeScene(event,pathUpComics,"Đăng truyện",idUser);
            }
        });
    }
}
