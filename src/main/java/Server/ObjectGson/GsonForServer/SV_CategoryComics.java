package Server.ObjectGson.GsonForServer;

public class SV_CategoryComics {
    private String idCategory;
    private String categoryName;
    private String categoryDescript;

    public SV_CategoryComics() {
    }

    public SV_CategoryComics(String idCategory, String categoryName, String categoryDescript) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryDescript = categoryDescript;
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

    public String getCategoryDescript() {
        return categoryDescript;
    }

    public void setCategoryDescript(String categoryDescript) {
        this.categoryDescript = categoryDescript;
    }

    @Override
    public String toString() {
        return "SV_CategoryComics{" +
                "idCategory=" + idCategory +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescript='" + categoryDescript + '\'' +
                '}';
    }
}
