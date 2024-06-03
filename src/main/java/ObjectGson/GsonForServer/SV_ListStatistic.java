package ObjectGson.GsonForServer;

import java.util.ArrayList;

public class SV_ListStatistic {
    private ArrayList<SV_Statistic> listStatistic = new ArrayList<>();

    public SV_ListStatistic(ArrayList<SV_Statistic> listStatistic) {
        this.listStatistic = listStatistic;
    }

    public SV_ListStatistic() {
    }

    public ArrayList<SV_Statistic> getListStatistic() {
        return listStatistic;
    }

    public void setListStatistic(ArrayList<SV_Statistic> listStatistic) {
        this.listStatistic = listStatistic;
    }

    @Override
    public String toString() {
        return "SV_ListStatistic{" +
                "listStatistic=" + listStatistic +
                '}';
    }
}
