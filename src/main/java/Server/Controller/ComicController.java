package Server.Controller;

import Connect.StreamSocket;
import DAO.ComicsDAO;
import Server.ComicServer.ComicServer;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import Server.ObjectGson.GsonForServer.SV_ListComicsInformations;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ComicController {
    public static void getAllComics(Socket socket) throws Exception {  // ham lay thong tin cho giao dien home
        //goi toi database de lay du lieu
        SV_ListComicsInformations listAllComics = ComicsDAO.selectALL();
        // goi ham de tra du lieu ve server
        new StreamSocket<SV_ListComicsInformations>().sendDataToCLient(socket,listAllComics);
    }

    public static void getIdCategoryComics(Socket socket) {

    }
}
