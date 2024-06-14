package ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListChapter {
    private ArrayList<SV_Chapter> listChapter = new ArrayList<>();

    public SV_ListChapter(ArrayList<SV_Chapter> listChapter) {
        this.listChapter = listChapter;
    }

    public SV_ListChapter() {
    }

    public ArrayList<SV_Chapter> getListChapter() {
        return listChapter;
    }

    public void setListChapter(ArrayList<SV_Chapter> listChapter) {
        this.listChapter = listChapter;
    }

    @Override
    public String toString() {
        return "SV_ListChapter{" +
                "listChapter=" + listChapter +
                '}';
    }
}
