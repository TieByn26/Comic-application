package ObjectGson.GsonForServer;

public class SV_CategoryName {
    private String categoryName;

    public SV_CategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "SV_CategoryName{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
