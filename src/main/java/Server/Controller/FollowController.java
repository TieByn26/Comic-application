package Server.Controller;

import Connect.StreamSocket;
import DAO.FollowDAO;
import DAO.StatisticsDAO;
import Server.ObjectGson.GsonForClient.CL_Follow;
import Server.ObjectGson.GsonForClient.CL_Statistics;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import com.google.gson.Gson;

import java.net.Socket;

public class FollowController {
    public static void addNewFollow(Socket socket) { // cap nhat lai danh sach theo doi
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String followInformation = StreamSocket.readGsonFromClient(socket);

        CL_Follow followClass = gson.fromJson(followInformation,CL_Follow.class);

        SV_CheckUpdate statusUpdate = FollowDAO.addNewFollow(followClass.getIdComics(),followClass.getIdUSer());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
    public static void deletedFollow(Socket socket) { // cap nhat lai danh sach theo doi
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String followInformation = StreamSocket.readGsonFromClient(socket);

        CL_Follow followClass = gson.fromJson(followInformation,CL_Follow.class);

        SV_CheckUpdate statusUpdate = FollowDAO.deleteFollow(followClass.getIdComics(),followClass.getIdUSer());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
    public static void checkFollow(Socket socket) { // xem truyen co dang duoc theo doi hay khong
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String followInformation = StreamSocket.readGsonFromClient(socket);

        CL_Follow followClass = gson.fromJson(followInformation,CL_Follow.class);

        SV_CheckUpdate statusUpdate = FollowDAO.checkFollow(followClass.getIdComics(),followClass.getIdUSer());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
}
