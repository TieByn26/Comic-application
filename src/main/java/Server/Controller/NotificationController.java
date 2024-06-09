package Server.Controller;

import Server.ObjectGson.GsonForClient.CL_IdUser;
import Server.ObjectGson.GsonForClient.CL_Notification;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_ListNotification;
import com.google.gson.Gson;
import java.net.Socket;
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
            for (SV_User sv_user : sv_listUser.getListUser()) {
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
    public static void responeAllNotification(Socket socket) throws Exception { // tra ve ten the loai theo id the loai
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);
        String dataQueryJson  = StreamSocket.readGsonFromClient(socket);
        CL_IdUser dataQueryClass = gson.fromJson(dataQueryJson,CL_IdUser.class);

        SV_ListNotification resqoneData = NotificationDAO.getAllNotification(dataQueryClass.getIdUser());
        new StreamSocket<SV_ListNotification>().sendDataToCLient(socket,resqoneData);
    }

    public static void updateStatus(Socket socket) throws Exception { // update trang thai da doc cho thong bao
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        String dataQueryJson  = StreamSocket.readGsonFromClient(socket);
        CL_Notification dataQueryClass = gson.fromJson(dataQueryJson,CL_Notification.class);

        SV_CheckUpdate statusUpdate = NotificationDAO.updateStatus(dataQueryClass.getIdNotification());
        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket, statusUpdate);
    }
    public static void deleteNotification(Socket socket) throws Exception { // xoa thong bao
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        String dataQueryJson  = StreamSocket.readGsonFromClient(socket);
        CL_Notification dataQueryClass = gson.fromJson(dataQueryJson,CL_Notification.class);

        SV_CheckUpdate statusUpdate = NotificationDAO.deletedNotification(dataQueryClass.getIdNotification());
        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket, statusUpdate);
    }

}
