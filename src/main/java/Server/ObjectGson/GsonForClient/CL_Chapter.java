package Server.ObjectGson.GsonForClient;

public class CL_Chapter {
   private String idComics;
   private int chapter;
   private String listImg;

    public CL_Chapter(String idComics, int chapter) {
        this.idComics = idComics;
        this.chapter = chapter;
    }

    public CL_Chapter(String idComics, int chapter, String listImg) {
        this.idComics = idComics;
        this.chapter = chapter;
        this.listImg = listImg;
    }

    public CL_Chapter() {
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

    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    @Override
    public String toString() {
        return "CL_Chapter{" +
                "idComics='" + idComics + '\'' +
                ", chapter=" + chapter +
                ", listImg='" + listImg + '\'' +
                '}';
    }
}
