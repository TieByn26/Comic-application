package General;

import ChangeScene.ChangeSceneGeneral;
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
    private static String pathComicsInformation = "/Controller/BaseProject/ViewComicsInformation.fxml";

    private static boolean isHide_listCategory = false;

    public static  void setEventForNavFollow (Label navFollow) {
        navFollow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                ChangeSceneGeneral.ChangeScene(event, pathFollow, "Theo dõi");
            }

        });

    }

    public static void setEventForNavHistory(Label navHistory) {
        navHistory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //call function change scene
                ChangeSceneGeneral.ChangeScene(event, pathHistory, "Lịch sử");
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
