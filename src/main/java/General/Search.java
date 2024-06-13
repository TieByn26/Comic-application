package General;

import ChangeScene.ChangedSceneToComicsInformation;
import RequestForServer.GetData.GetInformationComics;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Search {
    private static String pathComicInformation = "/Controller/BaseProject/ViewComicsInformation.fxml";

    public static  void setEventForSearch(TextField input, ImageView icon,int idUser) {
        icon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ChangedSceneToComicsInformation.ChangeScene(event,pathComicInformation,"Thông tin truyện",input.getText(),idUser);
            }
        });
    }
}
