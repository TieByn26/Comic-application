package ObjectGson.GsonForServer;

public class SV_Notification {
    private int idUser;
    private int idNotification;
    private String content;
    private Boolean status = false;

    public SV_Notification(int idUser, int idNotification, String content, Boolean status) {
        this.idUser = idUser;
        this.idNotification = idNotification;
        this.content = content;
        this.status = status;
    }

    public SV_Notification() {
    }

    public SV_Notification(int idNotification, String content, Boolean status) {
        this.idNotification = idNotification;
        this.content = content;
        this.status = status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SV_Notification{" +
                "idUser=" + idUser +
                ", idNotification=" + idNotification +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}
