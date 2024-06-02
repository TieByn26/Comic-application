package General;

import ChangeScene.ChangeSceneGeneral;
import ChangeScene.ChangeSceneToHistory;
import ChangeScene.ChangedSceneToFollow;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;


public class EvenOfNav {
    private static String pathFollow = "/Controller/BaseProject/ViewFollow.fxml";
    private static String pathPaneComics = "/Controller/BaseProject/ViewPaneComics.fxml";
    private static String pathTopUser = "/Controller/BaseProject/ViewPaneTopUser.fxml";
    private static String pathNotifications = "/Controller/BaseProject/ViewNotifications.fxml";
    private static String pathHistory = "/Controller/BaseProject/ViewHistory.fxml";
    private static  String pathProfile = "/Controller/BaseProject/ViewProfile.fxml";
    private static  String pathHome = "/Controller/BaseProject/ViewHome.fxml";
    private static boolean isHide_listCategory = false;

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

    public static void setEventForNavNotifications(Label nav_notifications){
        nav_notifications.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                ChangeSceneGeneral.ChangeScene(event, pathNotifications, "Thông báo");
            }
        });
    }

    public static void setEventForNavHome(Label nav_home){
        nav_home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                ChangeSceneGeneral.ChangeScene(event, pathHome, "Thông báo");
            }
        });
    }

    public static void setEventForNavCategory(Label nav_category, ScrollPane list_category) {
        nav_category.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isHide_listCategory == false) {
                    list_category.setVisible(true);
                    isHide_listCategory = true;
                    System.out.println(23);
                }
                else {
                    System.out.println(54);
                    list_category.setVisible(false);
                    isHide_listCategory = false;
                }
            }
        });
    }
}
