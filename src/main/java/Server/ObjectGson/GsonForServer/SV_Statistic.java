package Server.ObjectGson.GsonForServer;

import java.time.LocalDateTime;

public class SV_Statistic {
    private String idCommic;
    private int allView;


    public SV_Statistic() {
    }

    public SV_Statistic(String idCommic, int allView) {
        this.idCommic = idCommic;
        this.allView = allView;

    }

    public SV_Statistic(int allView) {
        this.allView = allView;
    }

    public String getIdCommic() {
        return idCommic;
    }

    public void setIdCommic(String idCommic) {
        this.idCommic = idCommic;
    }

    public int getAllView() {
        return allView;
    }

    public void setAllView(int allView) {
        this.allView = allView;
    }


    @Override
    public String toString() {
        return "SV_Statistic{" +
                "idCommic='" + idCommic + '\'' +
                ", allView=" + allView +
                '}';
    }
}
