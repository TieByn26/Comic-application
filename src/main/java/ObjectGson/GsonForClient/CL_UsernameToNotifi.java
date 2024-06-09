package ObjectGson.GsonForClient;

public class CL_UsernameToNotifi {
    private String username;

    public CL_UsernameToNotifi(String username) {
        this.username = username;
    }

    public CL_UsernameToNotifi() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CL_UsernameToNotifi{" +
                "username='" + username + '\'' +
                '}';
    }
}
