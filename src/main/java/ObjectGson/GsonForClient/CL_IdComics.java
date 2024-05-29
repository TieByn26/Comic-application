package ObjectGson.GsonForClient;

public class CL_IdComics {
    private String idComics;

    public CL_IdComics(String idComics) {
        this.idComics = idComics;
    }

    public CL_IdComics() {
    }

    public String getIdComics() {
        return idComics;
    }

    public void setIdComics(String idComics) {
        this.idComics = idComics;
    }

    @Override
    public String toString() {
        return "CL_idComics{" +
                "idComics='" + idComics + '\'' +
                '}';
    }
}
