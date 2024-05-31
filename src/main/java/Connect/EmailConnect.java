package Connect;

import org.apache.commons.mail.SimpleEmail;

public class EmailConnect {
    private static String username = "dxuantienn@gmail.com";
    private static String appPassword = "pspofvstxlfpouin";
    private static SimpleEmail email;
    public static Boolean sendMail(String emailTo ,String Subject, String Messenge){
        email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthentication(username,appPassword);
        try {
            email.setStartTLSEnabled(true);
            email.setFrom(username);
            email.setSubject(Subject);
            email.setMsg(Messenge);
            email.addTo(emailTo);
            email.send();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
