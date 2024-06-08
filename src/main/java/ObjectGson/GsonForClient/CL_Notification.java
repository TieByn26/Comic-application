package ObjectGson.GsonForClient;

public class CL_Notification {
    private int idNotification;

    public CL_Notification(int idNotification) {
        this.idNotification = idNotification;
    }

    public CL_Notification() {
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    @Override
    public String toString() {
        return "CL_Notification{" +
                "idNotification=" + idNotification +
                '}';
    }
}
