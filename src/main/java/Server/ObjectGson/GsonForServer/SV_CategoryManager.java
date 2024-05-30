package Server.ObjectGson.GsonForServer;

public class SV_CategoryManager {
    private String idComic;
    private String idCategory;
    public SV_CategoryManager() {
    }
    public SV_CategoryManager(String idComic, String idCategory) {
        this.idComic = idComic;
        this.idCategory = idCategory;
    }

    public SV_CategoryManager(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getIdComic() {
        return idComic;
    }

    public void setIdComic(String idComic) {
        this.idComic = idComic;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "SV_CategoryManager{" +
                "idComic='" + idComic + '\'' +
                ", idCategory='" + idCategory + '\'' +
                '}';
    }
}
