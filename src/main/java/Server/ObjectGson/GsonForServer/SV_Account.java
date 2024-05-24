package Server.ObjectGson.GsonForServer;

public class SV_Account {
    private int idUser;
    private String username;
    private String password;
    private String email;
    private int codeAccess;
    private int authority;

    public SV_Account(int idUser, String username, String password, String email, int codeAccess, int authority) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.codeAccess = codeAccess;
        this.authority = authority;
    }

    public SV_Account() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodeAccess() {
        return codeAccess;
    }

    public void setCodeAccess(int codeAccess) {
        this.codeAccess = codeAccess;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "SV_Account{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", codeAccess=" + codeAccess +
                ", authority=" + authority +
                '}';
    }
}
