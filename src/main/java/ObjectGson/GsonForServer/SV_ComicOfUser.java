package ObjectGson.GsonForServer;

public class SV_ComicOfUser {
    private String nameComic;
    private String category;
    private String status;
    private String avatarComic;
    private String ImageChapter;
    private String description;
    private int chapterNumber;
    private String author;

    public SV_ComicOfUser(String nameComic, String category, String status, String avatarComic, String imageChapter, String description, int chapterNumber, String author) {
        this.nameComic = nameComic;
        this.category = category;
        this.status = status;
        this.avatarComic = avatarComic;
        ImageChapter = imageChapter;
        this.description = description;
        this.chapterNumber = chapterNumber;
        this.author = author;
    }

    public SV_ComicOfUser() {
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatarComic() {
        return avatarComic;
    }

    public void setAvatarComic(String avatarComic) {
        this.avatarComic = avatarComic;
    }

    public String getImageChapter() {
        return ImageChapter;
    }

    public void setImageChapter(String imageChapter) {
        ImageChapter = imageChapter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "SV_ComicOfUser{" +
                "nameComic='" + nameComic + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", avatarComic='" + avatarComic + '\'' +
                ", ImageChapter='" + ImageChapter + '\'' +
                ", description='" + description + '\'' +
                ", chapterNumber=" + chapterNumber +
                ", author='" + author + '\'' +
                '}';
    }
}
