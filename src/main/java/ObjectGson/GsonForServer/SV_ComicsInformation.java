package ObjectGson.GsonForServer;

public class SV_ComicsInformation {
    private String nameComic;
    private String idComic;
    private String author;
    private String status;
    private String description;
    private String avatarComic;
    private int numberOfChapter;
    public SV_ComicsInformation() {
    }
    public SV_ComicsInformation(String nameComic, String idComic, String author, String status, String description, String avatarComic, int numberOfChapter) {
        this.nameComic = nameComic;
        this.idComic = idComic;
        this.author = author;
        this.status = status;
        this.description = description;
        this.avatarComic = avatarComic;
        this.numberOfChapter = numberOfChapter;
    }


    public SV_ComicsInformation(String avatarComic, String nameComic, int numberOfChapter) {
        this.nameComic = nameComic;
        this.avatarComic = avatarComic;
        this.numberOfChapter = numberOfChapter;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getIdComic() {
        return idComic;
    }

    public void setIdComic(String idComic) {
        this.idComic = idComic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarComic() {
        return avatarComic;
    }

    public void setAvatarComic(String avatarComic) {
        this.avatarComic = avatarComic;
    }

    public int getNumberOfChapter() {
        return numberOfChapter;
    }

    public void setNumberOfChapter(int numberOfChapter) {
        this.numberOfChapter = numberOfChapter;
    }

    @Override
    public String toString() {
        return "SV_ComicsInformation{" +
                "nameComic='" + nameComic + '\'' +
                ", idComic='" + idComic + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", avatarComic='" + avatarComic + '\'' +
                ", numberOfChapter=" + numberOfChapter +
                '}';
    }
}
