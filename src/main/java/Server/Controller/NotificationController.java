package Server.Controller;

import Connect.StreamSocket;
import DAO.NotificationDAO;
import Server.ObjectGson.GsonForClient.CL_UsernameToNotifi;
import Server.ObjectGson.GsonForServer.SV_CheckUsername;
import Server.ObjectGson.GsonForServer.SV_ListUser;
import Server.ObjectGson.GsonForServer.SV_Notification;
import Server.ObjectGson.GsonForServer.SV_User;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class NotificationController {
    public static void sendNotification(Socket socket){
        Gson gson = new Gson();
        //check trang thai
        StreamSocket.checkConnect(socket);
        //doc file du lieu lan 2
        String json = StreamSocket.readGsonFromClient(socket);
        System.out.println("username da gui: "+json);
        CL_UsernameToNotifi cl_usernameToNotifi = gson.fromJson(json, CL_UsernameToNotifi.class);

        if (cl_usernameToNotifi.getUsername().equals("@all")) {
            System.out.println("Thuc hien gui cho tat ca ");
            //check trang thai
            StreamSocket.checkConnect(socket);
            //doc file du lieu lan 3
            String json2 = StreamSocket.readGsonFromClient(socket);
            //noi dung
            System.out.println("Noi dung: "+json2);
            SV_Notification sv_notification = gson.fromJson(json2, SV_Notification.class);
            //thuc hien gui cho toan bo user
            SV_ListUser sv_listUser = NotificationDAO.getAllUser();
            for (SV_User sv_user : sv_listUser.getList()) {
                NotificationDAO.sendNotiToUser(sv_user.getIdUser(), sv_notification.getContent(), sv_notification.getStatus());
            }
            try {
                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                // chuyen doi data thanh json
                String data = "true";
                toClient.writeBytes(data + "\n");
                //dam bao du lieu duoc gui day du
                toClient.flush();
                System.out.println("Dữ liệu server đã gửi: "+ data);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("thuc hien gui cho 1 user");
            SV_CheckUsername sv_checkUsername = NotificationDAO.checkUsername(cl_usernameToNotifi);
            if (sv_checkUsername.getCheck()){
                //check trang thai
                StreamSocket.checkConnect(socket);
                //doc file du lieu lan 3
                String json2 = StreamSocket.readGsonFromClient(socket);
                //noi dung
                System.out.println("Noi dung: "+json2);
                SV_Notification sv_notification = gson.fromJson(json2, SV_Notification.class);
                NotificationDAO.sendNotiToUser(sv_checkUsername.getIdUser(), sv_notification.getContent(), sv_notification.getStatus());
                try {
                    DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                    // chuyen doi data thanh json
                    String data = "true";
                    toClient.writeBytes(data + "\n");
                    //dam bao du lieu duoc gui day du
                    toClient.flush();
                    System.out.println("Dữ liệu server đã gửi: "+ data);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                //check trang thai
                StreamSocket.checkConnect(socket);
                //doc file du lieu lan 3
                String json2 = StreamSocket.readGsonFromClient(socket);
                try {
                    DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                    // chuyen doi data thanh json
                    String data = "false";
                    toClient.writeBytes(data + "\n");
                    //dam bao du lieu duoc gui day du
                    toClient.flush();
                    System.out.println("Dữ liệu server đã gửi: "+ data);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
