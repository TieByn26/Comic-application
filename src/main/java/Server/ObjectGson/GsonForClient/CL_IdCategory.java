package Server.ObjectGson.GsonForClient;

public class CL_IdCategory {
    private String idCategory;

    public CL_IdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public CL_IdCategory() {
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "CL_IdCategory{" +
                "idCategory='" + idCategory + '\'' +
                '}';
    }
}
