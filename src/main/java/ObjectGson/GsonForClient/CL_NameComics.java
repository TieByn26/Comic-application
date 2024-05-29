package ObjectGson.GsonForClient;

public class CL_NameComics {
    private String nameComics;

    public CL_NameComics(String nameComics) {
        this.nameComics = nameComics;
    }

    public CL_NameComics() {
    }

    public String getNameComics() {
        return nameComics;
    }

    public void setNameComics(String nameComics) {
        this.nameComics = nameComics;
    }

    @Override
    public String toString() {
        return "CL_NameComics{" +
                "nameComics='" + nameComics + '\'' +
                '}';
    }
}
