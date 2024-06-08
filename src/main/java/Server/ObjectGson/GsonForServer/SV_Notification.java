package Server.ObjectGson.GsonForServer;

public class SV_Notification {
    private int idNotification;
    private int idUser;
    private String content;
    private boolean status;

    public SV_Notification(int idNotification, String content, boolean status) {
        this.idNotification = idNotification;
        this.content = content;
        this.status = status;
    }

    public SV_Notification() {
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SV_Notification{" +
                "idNotification=" + idNotification +
                ", idUser=" + idUser +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}