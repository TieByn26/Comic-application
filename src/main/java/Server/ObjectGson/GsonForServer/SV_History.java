package Server.ObjectGson.GsonForServer;

public class SV_History {
    private int idUser;
    private String idComic;
    private int chapter;

    public SV_History() {
    }

    public SV_History(int idUser, String idComic, int chapter) {
        this.idUser = idUser;
        this.idComic = idComic;
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "SV_History{" +
                "idUser=" + idUser +
                ", idComic='" + idComic + '\'' +
                ", chapter=" + chapter +
                '}';
    }
}
