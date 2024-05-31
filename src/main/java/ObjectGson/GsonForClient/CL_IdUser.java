package ObjectGson.GsonForClient;

public class CL_IdUser {
    private int idUser;

    public CL_IdUser(int idUser) {
        this.idUser = idUser;
    }

    public CL_IdUser() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "CL_IdUser{" +
                "idUser=" + idUser +
                '}';
    }
}
