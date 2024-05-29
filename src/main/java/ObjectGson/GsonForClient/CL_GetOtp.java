package ObjectGson.GsonForClient;

public class CL_GetOtp {
    private String username;
    private String email;

    public CL_GetOtp() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CL_GetOtp{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
