package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForClient.CL_UsernameToNotifi;
import ObjectGson.GsonForServer.SV_Notification;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SendNotification {
    public static String sendNotification(CL_UsernameToNotifi cl_usernameToNotifi, SV_Notification sv_notification){
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        String confirmation = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/send/notification");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();
            //check connect
            Connect.receiveStatus(socket);

            // Gửi dữ liệu kiểm tra lên server
            String jsonUser = gson.toJson(cl_usernameToNotifi);
            fromClient.write(jsonUser);
            fromClient.newLine();
            fromClient.flush();
            //check connect
            Connect.receiveStatus(socket);

            //Gửi dữ liệu lần 2 lên server để gửi
            String jsonContent = gson.toJson(sv_notification);
            fromClient.write(jsonContent);
            fromClient.newLine();
            fromClient.flush();
            // Đọc dữ liệu xác nhận đăng ký từ server
            confirmation = fromServer.readLine();
            System.out.println("Server response: " + confirmation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confirmation;
    }
}
