package Server.Controller;

import Connect.StreamSocket;
import DAO.ComicsDAO;
import Server.ObjectGson.GsonForClient.CL_IdCategory;
import Server.ObjectGson.GsonForClient.CL_IdComics;
import Server.ObjectGson.GsonForClient.CL_NameComics;
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

    public static void responeAllViews(Socket socket) throws Exception { // tra ve tat ca luot xem
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idComicsJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdComics idComics = gson.fromJson(idComicsJson,CL_IdComics.class);

        SV_Statistic svStatistic = ComicsDAO.selectAllView(idComics.getIdComics());
        new StreamSocket<SV_Statistic>().sendDataToCLient(socket,svStatistic);
    }

    public static void responeIdCategoryByIdComics(Socket socket) throws Exception { // tra ve id the loai
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idComicsJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdComics idComics = gson.fromJson(idComicsJson,CL_IdComics.class);

        SV_CategoryManager idCategory = ComicsDAO.selectIdCategoryByIdComics(idComics.getIdComics());
        new StreamSocket<SV_CategoryManager>().sendDataToCLient(socket,idCategory);
    }

    public static void responeCategoryNameByIdCategory(Socket socket) throws Exception { // tra ve ten the loai theo id the loai
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idCategoryJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdCategory idCategory = gson.fromJson(idCategoryJson,CL_IdCategory.class);

        SV_CategoryName categoryName = ComicsDAO.selectCategoryNameByIdCategory(idCategory.getIdCategory());
        new StreamSocket<SV_CategoryName>().sendDataToCLient(socket,categoryName);
    }
}
