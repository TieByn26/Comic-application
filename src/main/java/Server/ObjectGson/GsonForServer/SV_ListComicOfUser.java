package Server.ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListComicOfUser {
    private ArrayList<SV_ComicOfUser> listComicOfUser = new ArrayList<>();

    public SV_ListComicOfUser(ArrayList<SV_ComicOfUser> listComicOfUser) {
        this.listComicOfUser = listComicOfUser;
    }

    public SV_ListComicOfUser() {
    }

    public ArrayList<SV_ComicOfUser> getListComicOfUser() {
        return listComicOfUser;
    }

    public void setListComicOfUser(ArrayList<SV_ComicOfUser> listComicOfUser) {
        this.listComicOfUser = listComicOfUser;
    }

    @Override
    public String toString() {
        return "SV_ListComicOfUser{" +
                "listComicOfUser=" + listComicOfUser +
                '}';
    }
}
