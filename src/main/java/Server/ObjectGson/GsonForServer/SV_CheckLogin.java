package Server.ObjectGson.GsonForServer;

public class SV_CheckLogin {
    private Boolean check = false;
    private int idUser;
    private int authority;

    public SV_CheckLogin(Boolean check, int idUser) {
        this.check = check;
        this.idUser = idUser;
    }

    public SV_CheckLogin(Boolean check, int idUser, int authority) {
        this.check = check;
        this.idUser = idUser;
        this.authority = authority;
    }

    public SV_CheckLogin() {
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

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "SV_CheckLogin{" +
                "check=" + check +
                ", idUser=" + idUser +
                ", authority=" + authority +
                '}';
    }
}
