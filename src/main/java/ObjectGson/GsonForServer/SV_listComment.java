package ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_listComment {
    private ArrayList<SV_Comments> listComment;

    public SV_listComment(ArrayList<SV_Comments> listComment) {
        this.listComment = listComment;
    }

    public ArrayList<SV_Comments> getListComment() {
        return listComment;
    }

    public void setListComment(ArrayList<SV_Comments> listComment) {
        this.listComment = listComment;
    }

    @Override
    public String toString() {
        return "SV_listComment{" +
                "listComment=" + listComment +
                '}';
    }
}
