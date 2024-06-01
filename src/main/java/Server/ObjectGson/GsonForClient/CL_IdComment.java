package Server.ObjectGson.GsonForClient;

public class CL_IdComment {
    private  String idComment;

    public CL_IdComment(String idComment) {
        this.idComment = idComment;
    }

    public CL_IdComment() {
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    @Override
    public String toString() {
        return "CL_IdComment{" +
                "idComment='" + idComment + '\'' +
                '}';
    }
}
