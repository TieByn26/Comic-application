package Server.ObjectGson.GsonForClient;

public class CL_History {
    private int idUser;
    private String idComics;
    private int chapter;

    public CL_History(int idUser, String idComics, int chapter) {
        this.idUser = idUser;
        this.idComics = idComics;
        this.chapter = chapter;
    }

    public CL_History() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIdComics() {
        return idComics;
    }

    public void setIdComics(String idComics) {
        this.idComics = idComics;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }
}

