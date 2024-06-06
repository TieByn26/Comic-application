package ObjectGson.GsonForClient;

public class CL_IdComicsAndIdUser {
    private String idComics;
    private int idUSer;

    public CL_IdComicsAndIdUser(String idComics, int idUSer) {
        this.idComics = idComics;
        this.idUSer = idUSer;
    }

    public CL_IdComicsAndIdUser() {
    }

    public String getIdComics() {
        return idComics;
    }

    public void setIdComics(String idComics) {
        this.idComics = idComics;
    }

    public int getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(int idUSer) {
        this.idUSer = idUSer;
    }

    @Override
    public String toString() {
        return "CL_idComicsAndIdUser{" +
                "idComics='" + idComics + '\'' +
                ", idUSer=" + idUSer +
                '}';
    }
}
