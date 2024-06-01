package Server.ObjectGson.GsonForClient;

public class CL_Comments {
    private int idUser;
    private String idComic;
    private String comment;


    private String idComment;

    public CL_Comments() {
    }

    public CL_Comments(int idUser, String idComic, String comment) {
        this.idUser = idUser;
        this.idComic = idComic;
        this.comment = comment;
    }

    public CL_Comments(String idComment) {
        this.idComment = idComment;
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
        return "CL_Comments{" +
                "idUser='" + idUser + '\'' +
                ", idComic='" + idComic + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
