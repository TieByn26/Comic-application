package Server.ObjectGson.GsonForServer;

public class SV_User {
    private int idUser;
    private String fullName;
    private String avatar;
    private int experience;
    private String level;
    private String story;

    public SV_User() {
    }

    public SV_User(String fullName, String avatar, int experience, String level, String story) {
        this.fullName = fullName;
        this.avatar = avatar;
        this.experience = experience;
        this.level = level;
        this.story = story;
    }

    public SV_User(int idUser, String fullName, String avatar, int experience, String level) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.avatar = avatar;
        this.experience = experience;
        this.level = level;
    }

    public SV_User(String fullName) {
        this.fullName = fullName;
    }

    public SV_User(String fullName, String avatar) {
        this.fullName = fullName;
        this.avatar = avatar;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "SV_User{" +
                "idUser=" + idUser +
                ", fullName='" + fullName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", experience=" + experience +
                ", level='" + level + '\'' +
                '}';
    }
}
