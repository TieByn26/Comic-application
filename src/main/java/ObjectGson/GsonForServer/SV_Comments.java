package ObjectGson.GsonForServer;

public class SV_Comments {
    private String idComment;
    private int idUser;
    private String idComic;
    private String comment;

    public SV_Comments() {
    }

    public SV_Comments(String idComment, int idUser, String idComic, String comment) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.idComic = idComic;
        this.comment = comment;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIdComic() {
        return idComic;
    }

    public void setIdComic(String idComic) {
        this.idComic = idComic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "SV_Comments{" +
                "idComment='" + idComment + '\'' +
                ", idUser=" + idUser +
                ", idComic='" + idComic + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
