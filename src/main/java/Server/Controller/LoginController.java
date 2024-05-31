package Server.Controller;

import Connect.EmailConnect;
import Connect.StreamSocket;
import DAO.AccountDAO;
import Server.ObjectGson.GsonForClient.CL_ChangePass;
import Server.ObjectGson.GsonForClient.CL_GetOtp;
import Server.ObjectGson.GsonForClient.CL_LoginInformation;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;

public class LoginController {
    public static void checkLogin(Socket socket) throws Exception{
        Gson gson = new Gson();
        //check connect
        StreamSocket.checkConnect(socket);
        //doc du lieu lan 2
        String fromClient = StreamSocket.readGsonFromClient(socket);
        //anh xa du lieu vao model
        CL_LoginInformation cl_loginInformation = gson.fromJson(fromClient, CL_LoginInformation.class);

        if (AccountDAO.checkLogin(cl_loginInformation)) {
            //xac nhan la dung tai khoan mat khau
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes("true");
            toClient.flush();
        }else{
            //xac nhan la sai tai khoan mat khau
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes("false");
            toClient.flush();
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
        String OTP  = ""+((Math.random()+1)*1000);
        String subject = "MÃ KHÔI PHỤC MẬT KHẨU";
        String content = "Đây là mã khôi phục mật khẩu"+ OTP +
                         "Vui lòng không chia sẻ mã này dưới bất kỳ hình thức nào" +
                         "Nếu gặp trở ngại, hãy liên hệ 123456789 để được hỗ trợ" +
                         "Admin.";
        String email = AccountDAO.getEmailForUser(cl_getOtp);
        //gui giu lieu otp ve client de kiem tra

        if (EmailConnect.sendMail(email,subject,content)){
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes(gson.toJson(OTP));
            toClient.flush();
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
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            //gui ve true neu da thuc hien query
            toClient.writeBytes("true");
            toClient.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
