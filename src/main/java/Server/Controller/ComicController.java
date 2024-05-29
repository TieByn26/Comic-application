package Server.Controller;

import Connect.StreamSocket;
import DAO.ComicsDAO;
import Server.ObjectGson.GsonForClient.CL_NameComics;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import Server.ObjectGson.GsonForServer.SV_ListComicsInformations;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ComicController {
    public static void responeALLComics(Socket socket) throws Exception {  // lay thong tin truyen gom: ten truyen, so luong chapter, avatar truyen  (load ra home)
        SV_ListComicsInformations listAllComics = ComicsDAO.selectALLComics();
        new StreamSocket<SV_ListComicsInformations>().sendDataToCLient(socket,listAllComics);
    }

    public static void responeFullComicsInformationByNameComics(Socket socket)  throws Exception{ // //ham lay tat ca thong tin cua truyen
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);
        //doc du lieu lan 2 tu client
        String nameComicsJson = StreamSocket.readGsonFromClient(socket);

        CL_NameComics nameComics = gson.fromJson(nameComicsJson,CL_NameComics.class);
        System.out.println(nameComics.getNameComics());
        SV_ComicsInformation comicsInformation = ComicsDAO.selectFullComicsInformationByNameComics(nameComics.getNameComics());
        new StreamSocket<SV_ComicsInformation>().sendDataToCLient(socket,comicsInformation);
    }
}
