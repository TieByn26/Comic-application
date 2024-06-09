package ObjectGson.GsonForClient;

public class CL_User {
    private int idUser;
    private String avatar;
    private String fullName;
    private int experience;
    private String level;
    private String story;

    public CL_User() {
    }

    public CL_User(int idUser, int experience, String level) {
        this.idUser = idUser;
        this.experience = experience;
        this.level = level;
    }

    public CL_User(int idUser, String story) {
        this.idUser = idUser;
        this.story = story;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        return "CL_User{" +
                "idUser=" + idUser +
                ", avatar='" + avatar + '\'' +
                ", fullName='" + fullName + '\'' +
                ", experience=" + experience +
                ", level='" + level + '\'' +
                '}';
    }
}

