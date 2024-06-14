package ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListCategoryComic {
    private ArrayList<SV_CategoryComics> listCategoryComic = new ArrayList<>();

    public SV_ListCategoryComic(ArrayList<SV_CategoryComics> listCategoryComic) {
        this.listCategoryComic = listCategoryComic;
    }

    public SV_ListCategoryComic() {
    }

    public ArrayList<SV_CategoryComics> getListCategoryComic() {
        return listCategoryComic;
    }

    public void setListCategoryComic(ArrayList<SV_CategoryComics> listCategoryComic) {
        this.listCategoryComic = listCategoryComic;
    }

    @Override
    public String toString() {
        return "SV_ListCategoryComic{" +
                "listCategoryComic=" + listCategoryComic +
                '}';
    }
}
