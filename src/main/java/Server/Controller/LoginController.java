package Server.Controller;

import Connect.EmailConnect;
import Connect.StreamSocket;
import DAO.AccountDAO;
import DAO.UserDAO;
import Server.ObjectGson.GsonForClient.CL_ChangePass;
import Server.ObjectGson.GsonForClient.CL_CheckOtp;
import Server.ObjectGson.GsonForClient.CL_GetOtp;
import Server.ObjectGson.GsonForClient.CL_LoginInformation;
import Server.ObjectGson.GsonForServer.SV_CheckLogin;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;

public class LoginController {
    public static void checkLogin(Socket socket) throws Exception{
        Gson gson = new Gson();
        SV_CheckLogin svCheckLogin = new SV_CheckLogin();
        //check connect
        StreamSocket.checkConnect(socket);
        //doc du lieu lan 2
        String fromClient = StreamSocket.readGsonFromClient(socket);
        //anh xa du lieu vao model
        CL_LoginInformation cl_loginInformation = gson.fromJson(fromClient, CL_LoginInformation.class);

        if (AccountDAO.checkLogin(cl_loginInformation)) {
            //xac nhan la dung tai khoan mat khau
            svCheckLogin.setCheck(true);
            svCheckLogin.setIdUser(UserDAO.getIdByUsernameLogin(cl_loginInformation));
            //gui du lieu xac minh ve cho client
            new StreamSocket<SV_CheckLogin>().sendDataToCLient(socket, svCheckLogin);
        }else{
            //xac nhan la sai tai khoan mat khau
            svCheckLogin.setCheck(false);
            //gui du lieu xac minh ve cho client
            new StreamSocket<SV_CheckLogin>().sendDataToCLient(socket, svCheckLogin);
        }
    }
    public static void sendOtp(Socket socket) throws Exception{
        Gson gson = new Gson();
        //check ket noi
        StreamSocket.checkConnect(socket);
        //doc file du lieu lan 2
        String fromClient = StreamSocket.readGsonFromClient(socket);
        CL_GetOtp cl_getOtp = gson.fromJson(fromClient, CL_GetOtp.class);
        //bat dau gui otp den mail cua user
        int OTP  = (int)((Math.random()+1)*1000);
        String subject = "MÃ KHÔI PHỤC MẬT KHẨU";
        String content = "Đây là mã khôi phục mật khẩu: "+ OTP +"\n"+
                         "Vui lòng không chia sẻ mã này dưới bất kỳ hình thức nào \n" +
                         "Nếu gặp trở ngại, hãy liên hệ 123456789 để được hỗ trợ \n" +
                         "Thân ái! \n " +
                         "Admin.";
        String email = AccountDAO.getEmailForUser(cl_getOtp);
        CL_CheckOtp cl_checkOtp = new CL_CheckOtp(OTP);
        //gui giu lieu otp ve client de kiem tra
        if (EmailConnect.sendMail(email,subject,content)){
            new StreamSocket<CL_CheckOtp>().sendDataToCLient(socket, cl_checkOtp);
        } else {
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes("false");
            toClient.flush();
        }
    }
    public static void changePassword(Socket socket){
        Gson gson = new Gson();
        //check ket noi
        StreamSocket.checkConnect(socket);
        //doc file du lieu lan 2
        String fromClient = StreamSocket.readGsonFromClient(socket);
        CL_ChangePass cl_changePass = gson.fromJson(fromClient, CL_ChangePass.class);
        try {
            AccountDAO.changePassword(cl_changePass);
            SV_CheckLogin sv_checkLogin = new SV_CheckLogin();
            sv_checkLogin.setCheck(true);
            new StreamSocket<SV_CheckLogin>().sendDataToCLient(socket, sv_checkLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
