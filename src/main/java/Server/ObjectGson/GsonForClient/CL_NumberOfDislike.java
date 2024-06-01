package Server.ObjectGson.GsonForClient;

public class CL_NumberOfDislike {
    private int dislike;

    public CL_NumberOfDislike(int dislike) {
        this.dislike = dislike;
    }

    public CL_NumberOfDislike() {
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
    @Override
    public String toString() {
        return "CL_NumberOfDislike{" +
                "dislike=" + dislike +
                '}';
    }
}
