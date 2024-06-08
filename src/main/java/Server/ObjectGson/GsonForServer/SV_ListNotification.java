package Server.ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListNotification {
    ArrayList<SV_Notification> listNotification = new ArrayList<>();

    public SV_ListNotification(ArrayList<SV_Notification> listNotification) {
        this.listNotification = listNotification;
    }

    public SV_ListNotification() {
    }

    public ArrayList<SV_Notification> getListNotification() {
        return listNotification;
    }

    public void setListNotification(ArrayList<SV_Notification> listNotification) {
        this.listNotification = listNotification;
    }

    @Override
    public String toString() {
        return "SV_ListNotification{" +
                "listNotification=" + listNotification +
                '}';
    }
}
