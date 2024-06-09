package Server.ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListUser {
    private ArrayList<SV_User> list = new ArrayList<>();

    public SV_ListUser(ArrayList<SV_User> list) {
        this.list = list;
    }

    public SV_ListUser() {
    }

    public ArrayList<SV_User> getList() {
        return list;
    }

    public void setList(ArrayList<SV_User> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SV_ListUser{" +
                "list=" + list +
                '}';
    }
}
