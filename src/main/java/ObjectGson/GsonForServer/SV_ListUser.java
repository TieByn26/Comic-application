package ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListUser {
    private ArrayList<SV_User> listUser = new ArrayList<>();

    public SV_ListUser(ArrayList<SV_User> listUser) {
        this.listUser = listUser;
    }

    public SV_ListUser() {
    }

    public ArrayList<SV_User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<SV_User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public String toString() {
        return "SV_ListUser{" +
                "listUser=" + listUser +
                '}';
    }
}
