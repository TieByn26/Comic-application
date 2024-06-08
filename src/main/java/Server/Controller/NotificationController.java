package Server.Controller;

import Connect.StreamSocket;
import DAO.NotificationDAO;
import Server.ObjectGson.GsonForClient.CL_IdUser;
import Server.ObjectGson.GsonForClient.CL_Notification;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_ListNotification;
import com.google.gson.Gson;

import java.net.Socket;

public class NotificationController {
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
