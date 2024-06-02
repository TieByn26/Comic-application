package ObjectGson.GsonForClient;

public class CL_Follow {
    private String idComics;
    private int idUSer;

    public CL_Follow(String idComics, int idUSer) {
        this.idComics = idComics;
        this.idUSer = idUSer;
    }

    public CL_Follow() {
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
}
