package Server.Controller;

import Connect.StreamSocket;
import DAO.ComicsDAO;
import DAO.CommentDAO;
import DAO.StatisticsDAO;
import DAO.UserDAO;
import Server.ObjectGson.GsonForClient.*;
import Server.ObjectGson.GsonForServer.*;
import com.google.gson.Gson;

import java.net.Socket;

public class ComicController {
    public static void responeALLComics(Socket socket) throws Exception {  // tra ve thong tin truyen gom: ten truyen, so luong chapter, avatar truyen  (load ra home)
        SV_ListComicsInformations listAllComics = ComicsDAO.selectALLComics();
        new StreamSocket<SV_ListComicsInformations>().sendDataToCLient(socket,listAllComics);
    }

    public static void responeComicInformationByNameComics  (Socket socket)  throws Exception{ //tra ve tat ca thong tin cua truyen
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);
        //doc du lieu can thiet cho cau query
        String nameComicsJson = StreamSocket.readGsonFromClient(socket);

        CL_NameComics nameComics = gson.fromJson(nameComicsJson,CL_NameComics.class);
        System.out.println(nameComics.getNameComics());
        SV_ComicsInformation comicsInformation = ComicsDAO.selectFullComicsInformationByNameComics(nameComics.getNameComics());
        new StreamSocket<SV_ComicsInformation>().sendDataToCLient(socket,comicsInformation);
    }
    public static void upComicsByUser(Socket socket) {
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        String dataQueryJson = StreamSocket.readGsonFromClient(socket);
        CL_UpComicsByUser dataQueryClass = gson.fromJson(dataQueryJson,CL_UpComicsByUser.class);

        SV_CheckUpdate statusUpdate = ComicsDAO.upComicsByUser(dataQueryClass.getNameComic(),dataQueryClass.getCategory(),dataQueryClass.getStatus(),dataQueryClass.getAvatarComics(),dataQueryClass.getLinkImageOfChapter(),dataQueryClass.getDescription(),dataQueryClass.getChapter(),dataQueryClass.getAuthor());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
}
