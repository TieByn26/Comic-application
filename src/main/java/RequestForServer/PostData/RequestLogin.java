package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_LoginInformation;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_CheckLogin;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RequestLogin {
    public static SV_CheckLogin requestLogin(CL_LoginInformation cl_loginInformation){
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_CheckLogin svCheckLogin = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/login/account");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();
            Connect.receiveStatus(socket);

            // Gửi dữ liệu đăng ký lên server
            String jsonRegisterInfo = gson.toJson(cl_loginInformation);
            fromClient.write(jsonRegisterInfo);
            fromClient.newLine();
            fromClient.flush();

            // Đọc dữ liệu xác nhận đăng ký từ server
            String read = fromServer.readLine();
            svCheckLogin = gson.fromJson(read, SV_CheckLogin.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return svCheckLogin;
    }
}
