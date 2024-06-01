package Server.Controller;

import Connect.StreamSocket;
import DAO.UserDAO;
import Server.ObjectGson.GsonForClient.CL_IdUser;
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
}
