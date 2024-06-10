package Server.Controller;

import Connect.StreamSocket;
import DAO.ComicUserDAO;
import Server.ObjectGson.GsonForServer.SV_ListComicOfUser;
import com.google.gson.Gson;

import java.net.Socket;

public class ComicUserController {
    public static void listComicOfUser(Socket socket){
        Gson gson = new Gson();
        //check status
        StreamSocket.checkConnect(socket);
        //gui du lieu ve cho Client
        try {
            SV_ListComicOfUser sv_listComicOfUser = ComicUserDAO.getAllComicOfUser();
            new StreamSocket<SV_ListComicOfUser>().sendDataToCLient(socket,sv_listComicOfUser);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
