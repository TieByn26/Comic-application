package ObjectGson.GsonForClient;

public class CL_ChangePass {
    private String username;
    private String newPass;

    public CL_ChangePass() {
    }

    public CL_ChangePass(String username, String newPass) {
        this.username = username;
        this.newPass = newPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    @Override
    public String toString() {
        return "CL_ChangePass{" +
                "username='" + username + '\'' +
                ", newPass='" + newPass + '\'' +
                '}';
    }
}
