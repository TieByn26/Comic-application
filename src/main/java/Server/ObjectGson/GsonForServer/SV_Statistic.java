package Server.ObjectGson.GsonForServer;

import java.time.LocalDateTime;

public class SV_Statistic {
    private String idCommic;
    private int allView;
    private LocalDateTime lastUpdate;

    public SV_Statistic() {
    }

    public SV_Statistic(String idCommic, int allView, LocalDateTime lastUpdate) {
        this.idCommic = idCommic;
        this.allView = allView;
        this.lastUpdate = lastUpdate;
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "SV_Statistic{" +
                "idCommic='" + idCommic + '\'' +
                ", allView=" + allView +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
