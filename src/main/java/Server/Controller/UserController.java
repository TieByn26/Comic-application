package Server.Controller;

import Connect.StreamSocket;
import DAO.UserDAO;
import Server.ObjectGson.GsonForClient.CL_IdUser;
import Server.ObjectGson.GsonForClient.CL_UpComicsByUser;
import Server.ObjectGson.GsonForClient.CL_User;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_ListUser;
import Server.ObjectGson.GsonForServer.SV_User;
import com.google.gson.Gson;

import java.net.Socket;

public class UserController {

    public static void responeInforUserForComment(Socket socket) throws Exception { // tra ve ten va avatar user
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idUSerJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdUser idUSer = gson.fromJson(idUSerJson,CL_IdUser.class);

        SV_User inforUser = UserDAO.getInforUserForComment(idUSer.getIdUser());
        new StreamSocket<SV_User>().sendDataToCLient(socket,inforUser);
    }
    public static void updateExperienceAndLevelUser(Socket socket) throws Exception {
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String dataQueryJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdUser dataQueryClass = gson.fromJson(dataQueryJson,CL_IdUser.class);

        SV_CheckUpdate statusUpdate = UserDAO.updateExperienceAndLevelUser(dataQueryClass.getIdUser());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }

    public static void responeUserinformaitonByIdUser(Socket socket) {
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        String dataQueryJson = StreamSocket.readGsonFromClient(socket);
        CL_IdUser dataQueryClass = gson.fromJson(dataQueryJson,CL_IdUser.class);

        SV_User inforUser = UserDAO.getInformationUsereByIdUser(dataQueryClass.getIdUser());

        new StreamSocket<SV_User>().sendDataToCLient(socket,inforUser);
    }

    public static void updateStoryUserByIdUser(Socket socket) {
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        String dataQueryJson = StreamSocket.readGsonFromClient(socket);
        CL_User dataQueryClass = gson.fromJson(dataQueryJson,CL_User.class);

        SV_CheckUpdate statusUpdate = UserDAO.updateStoryByIdUser(dataQueryClass.getIdUser(),dataQueryClass.getStory());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }

    public static void responeFullnameUserByIdUser(Socket socket) {
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        String dataQueryJson = StreamSocket.readGsonFromClient(socket);
        CL_IdUser dataQueryClass = gson.fromJson(dataQueryJson,CL_IdUser.class);

        SV_User fullname = UserDAO.selectFullNameByIdUser(dataQueryClass.getIdUser());

        new StreamSocket<SV_User>().sendDataToCLient(socket,fullname);
    }

    public static void responeListTopUser(Socket socket) {
        StreamSocket.checkConnect(socket);

        SV_ListUser listTopUser = UserDAO.selectTop10User();

        new StreamSocket<SV_ListUser>().sendDataToCLient(socket,listTopUser);
    }
}
