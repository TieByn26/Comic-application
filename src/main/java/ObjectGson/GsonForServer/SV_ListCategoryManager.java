package ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListCategoryManager {
    private ArrayList<SV_CategoryManager> listCategoryManager = new ArrayList<>();

    public SV_ListCategoryManager(ArrayList<SV_CategoryManager> listCategoryManager) {
        this.listCategoryManager = listCategoryManager;
    }

    public SV_ListCategoryManager() {
    }

    public ArrayList<SV_CategoryManager> getListCategoryManager() {
        return listCategoryManager;
    }

    public void setListCategoryManager(ArrayList<SV_CategoryManager> listCategoryManager) {
        this.listCategoryManager = listCategoryManager;
    }

    @Override
    public String toString() {
        return "SV_ListCategoryManager{" +
                "listCategoryManager=" + listCategoryManager +
                '}';
    }
}
