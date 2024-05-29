package Server.ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListComicsInformations {
    ArrayList<SV_ComicsInformation> listComicsInfomations = new ArrayList<>();

    public SV_ListComicsInformations(ArrayList<SV_ComicsInformation> listComicsInfomations) {
        this.listComicsInfomations = listComicsInfomations;
    }

    public SV_ListComicsInformations() {
    }

    public ArrayList<SV_ComicsInformation> getListComicsInfomations() {
        return listComicsInfomations;
    }

    public void setListComicsInfomations(ArrayList<SV_ComicsInformation> listComicsInfomations) {
        this.listComicsInfomations = listComicsInfomations;
    }

    @Override
    public String toString() {
        return "SV_ListComicsInformations{" +
                "listComicsInfomations=" + listComicsInfomations +
                '}';
    }
}
