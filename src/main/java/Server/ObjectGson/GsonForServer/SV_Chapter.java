package Server.ObjectGson.GsonForServer;

public class SV_Chapter {
    private String idComic;
    private int chapter;
    private String linkImage;
    public SV_Chapter() {
    }
    public SV_Chapter(String idComic, int chapter, String linkImage) {
        this.idComic = idComic;
        this.chapter = chapter;
        this.linkImage = linkImage;
    }
    public SV_Chapter(String linkImage) {
        this.linkImage = linkImage;
    }


    public String getIdComic() {
        return idComic;
    }

    public void setIdComic(String idComic) {
        this.idComic = idComic;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public String toString() {
        return "SV_Chapter{" +
                "idComic='" + idComic + '\'' +
                ", chapter=" + chapter +
                ", linkImage='" + linkImage + '\'' +
                '}';
    }
}
