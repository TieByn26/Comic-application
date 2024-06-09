package Server.ObjectGson.GsonForServer;

public class SV_CheckUsername {
    private Boolean check = false;
    private int idUser;

    public SV_CheckUsername() {
    }

    public SV_CheckUsername(Boolean check, int idUser) {
        this.check = check;
        this.idUser = idUser;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "SV_CheckUsername{" +
                "check=" + check +
                ", idUser=" + idUser +
                '}';
    }
}
