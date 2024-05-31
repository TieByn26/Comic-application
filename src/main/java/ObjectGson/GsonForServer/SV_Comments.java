package ObjectGson.GsonForServer;

public class SV_Comments {
    private int idUser;
    private String comment;
    private int like;
    private int dislike;

    public SV_Comments() {
    }

    public SV_Comments(int idUser,  String comment, int like, int dislike) {
        this.idUser = idUser;
        this.comment = comment;
        this.like = like;
        this.dislike = dislike;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "SV_Comments{" +
                "idUser=" + idUser +
                ", comment='" + comment + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                '}';
    }
}
