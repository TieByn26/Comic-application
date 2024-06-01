package ObjectGson.GsonForClient;

public class CL_LoginInformation {
    private String username;
    private String password;

    public CL_LoginInformation() {
    }

    public CL_LoginInformation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CL_LoginInformation{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
