package Server.Controller;

import Connect.StreamSocket;
import DAO.ComicUserDAO;
import Server.ObjectGson.GsonForServer.SV_ComicOfUser;
import Server.ObjectGson.GsonForServer.SV_ListComicOfUser;
import com.google.gson.Gson;

import java.net.Socket;

public class ComicUserController {
    public static void listComicOfUser(Socket socket){
        //gui du lieu ve cho Client
        try {
            SV_ListComicOfUser sv_listComicOfUser = ComicUserDAO.getAllComicOfUser();
            new StreamSocket<SV_ListComicOfUser>().sendDataToCLient(socket,sv_listComicOfUser);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleleComicOfUser(Socket socket){
        Gson gson = new Gson();
        //check ket noi
        StreamSocket.checkConnect(socket);
        //doc du lieu lan 2
        String dataFromClient = StreamSocket.readGsonFromClient(socket);
        SV_ComicOfUser sv_comicOfUser = gson.fromJson(dataFromClient, SV_ComicOfUser.class);
        ComicUserDAO.deleteComicOfUser(sv_comicOfUser);

    }
}
