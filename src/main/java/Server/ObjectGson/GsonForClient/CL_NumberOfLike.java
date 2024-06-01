package Server.ObjectGson.GsonForClient;

public class CL_NumberOfLike {
    private  int like;

    public CL_NumberOfLike(int like) {
        this.like = like;
    }

    public CL_NumberOfLike() {
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "CL_NumberOfLike{" +
                "like=" + like +
                '}';
    }
}
