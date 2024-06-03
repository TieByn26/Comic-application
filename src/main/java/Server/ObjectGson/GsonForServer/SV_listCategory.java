package Server.ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_listCategory {
    ArrayList<SV_CategoryComics> listCategory = new ArrayList<>();

    public SV_listCategory(ArrayList<SV_CategoryComics> listCategory) {
        this.listCategory = listCategory;
    }

    public SV_listCategory() {
    }

    public ArrayList<SV_CategoryComics> getListCategory() {
        return listCategory;
    }

    public void setListCategory(ArrayList<SV_CategoryComics> listCategory) {
        this.listCategory = listCategory;
    }
}
