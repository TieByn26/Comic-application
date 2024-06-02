package ObjectGson.GsonForClient;

public class CL_Statistics {
    private String idComics;
    private int allView;

    public CL_Statistics() {
    }

    public CL_Statistics(String idComics, int allView) {
        this.idComics = idComics;
        this.allView = allView;
    }

    public String getIdComics() {
        return idComics;
    }

    public void setIdComics(String idComics) {
        this.idComics = idComics;
    }

    public int getAllView() {
        return allView;
    }

    public void setAllView(int allView) {
        this.allView = allView;
    }
}
