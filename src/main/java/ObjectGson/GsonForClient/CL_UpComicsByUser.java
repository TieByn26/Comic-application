package ObjectGson.GsonForClient;

public class CL_UpComicsByUser {
    private String nameComic;
    private String author;
    private int chapter;
    private String linkImageOfChapter;
    private String description;
    private String status;
    private String category;
    private String avatarComics;

    public CL_UpComicsByUser() {
    }

    public CL_UpComicsByUser(String nameComic, String author, int chapter, String linkImageOfChapter, String description, String status, String category, String avatarComics) {
        this.nameComic = nameComic;
        this.author = author;
        this.chapter = chapter;
        this.linkImageOfChapter = linkImageOfChapter;
        this.description = description;
        this.status = status;
        this.category = category;
        this.avatarComics = avatarComics;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getLinkImageOfChapter() {
        return linkImageOfChapter;
    }

    public void setLinkImageOfChapter(String linkImageOfChapter) {
        this.linkImageOfChapter = linkImageOfChapter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvatarComics() {
        return avatarComics;
    }

    public void setAvatarComics(String avatarComics) {
        this.avatarComics = avatarComics;
    }

    @Override
    public String toString() {
        return "CL_UpComicsByUser{" +
                "nameComic='" + nameComic + '\'' +
                ", author='" + author + '\'' +
                ", chapter=" + chapter +
                ", linkImageOfChapter='" + linkImageOfChapter + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", avatarComics='" + avatarComics + '\'' +
                '}';
    }
}
