package ObjectGson.GsonForClient;

public class CL_IdComment {
    private  int idComment;

    public CL_IdComment(int idComment) {
        this.idComment = idComment;
    }

    public CL_IdComment() {
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    @Override
    public String toString() {
        return "CL_IdComment{" +
                "idComment='" + idComment + '\'' +
                '}';
    }
}
