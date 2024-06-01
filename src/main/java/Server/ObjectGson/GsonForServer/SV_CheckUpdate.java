package Server.ObjectGson.GsonForServer;

public class SV_CheckUpdate {
 private int statusUpdate;

    public SV_CheckUpdate(int statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    public SV_CheckUpdate() {
    }

    public int getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(int statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    @Override
    public String toString() {
        return "SV_CheckUpdate{" +
                "statusUpdate=" + statusUpdate +
                '}';
    }
}
