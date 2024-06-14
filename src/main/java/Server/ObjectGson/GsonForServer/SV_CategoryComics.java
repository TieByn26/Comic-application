package Server.ObjectGson.GsonForServer;

public class SV_CategoryComics {
    private String idCategory;
    private String categoryName;

    public SV_CategoryComics() {
    }

    public SV_CategoryComics(String idCategory) {
        this.idCategory = idCategory;
    }

    public SV_CategoryComics(String idCategory, String categoryName) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
    }



    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "SV_CategoryComics{" +
                "idCategory=" + idCategory +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
