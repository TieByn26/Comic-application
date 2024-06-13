package ObjectGson.GsonForClient;

public class CL_IdUserAndLinkAvatar {
    private int idUser;
    private String linkAvatar;

    public CL_IdUserAndLinkAvatar() {
    }

    public CL_IdUserAndLinkAvatar(int idUser, String linkAvatar) {
        this.idUser = idUser;
        this.linkAvatar = linkAvatar;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    @Override
    public String toString() {
        return "CL_IdUserAndLinkAvatar{" +
                "idUSer=" + idUser +
                ", linkAvatar='" + linkAvatar + '\'' +
                '}';
    }
}
