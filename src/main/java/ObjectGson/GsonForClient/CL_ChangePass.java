package Server.ObjectGson.GsonForClient;

public class CL_ChangePass {
    private String username;
    private String newPass;

    public CL_ChangePass() {
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
                "newPass='" + newPass + '\'' +
                '}';
    }
}
