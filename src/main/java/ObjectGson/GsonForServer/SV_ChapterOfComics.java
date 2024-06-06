package ObjectGson.GsonForServer;

public class SV_ChapterOfComics {
    private int chapter;

    public SV_ChapterOfComics(int chapter) {
        this.chapter = chapter;
    }

    public SV_ChapterOfComics() {
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "SV_ChapterOfOfComics{" +
                "chapter=" + chapter +
                '}';
    }
}
