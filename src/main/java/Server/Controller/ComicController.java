package Server.Controller;

import DAO.ComicsDAO;
import Server.ComicServer.ComicServer;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import Server.ObjectGson.GsonForServer.SV_ListComicsInformations;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ComicController {
    public static void getAllComics(Socket socket) throws Exception {
        Gson gson = new Gson();
        SV_ListComicsInformations listAllComics = ComicsDAO.selectALL();
        new ComicServer<SV_ListComicsInformations>().sendValueFromClient(socket,listAllComics);
    }
}
