package Server.ObjectGson.GsonForClient;

public class CL_CheckOtp {
    private int OTP;

    public CL_CheckOtp() {
    }

    public CL_CheckOtp(int OTP) {
        this.OTP = OTP;
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    @Override
    public String toString() {
        return "CL_CheckOtp{" +
                "OTP=" + OTP +
                '}';
    }
}
