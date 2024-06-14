package Server.Controller;

import Connect.StreamSocket;
import DAO.CategoryDAO;
import DAO.ChapterDAO;
import DAO.ComicsDAO;
import Server.ObjectGson.GsonForServer.*;
import com.google.gson.Gson;

import java.net.Socket;

public class ListComicsController {
    public static void getAllComic(Socket socket){
        Gson gson = new Gson();
        //check trang thai
        StreamSocket.checkConnect(socket);
        //gui du lieu ve client
        try {
            new StreamSocket<SV_ListComicsInformations>().sendDataToCLient(socket, ComicsDAO.getAllComicInformation());
            System.out.println("Du lieu da gui thanh cong ");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getAllNumView(Socket socket){
        //check trang thai
        StreamSocket.checkConnect(socket);
        //gui du lieu ve client
        try {
            new StreamSocket<SV_ListStatistic>().sendDataToCLient(socket, ComicsDAO.getAllNumView());
            System.out.println("Du lieu da gui thanh cong ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getAllCategoryManager(Socket socket){
        StreamSocket.checkConnect(socket);
        //gui du lieu ve client
        try {
            new StreamSocket<SV_ListCategoryManager>().sendDataToCLient(socket, CategoryDAO.getAllCategoryManager());
            System.out.println("Du lieu da gui thanh cong ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getAllChapter(Socket socket){
        StreamSocket.checkConnect(socket);
        //gui du lieu ve client
        try {
            new StreamSocket<SV_ListChapter>().sendDataToCLient(socket, ChapterDAO.getAllChapter());
            System.out.println("Du lieu da gui thanh cong ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getAllCategoryComic(Socket socket){
        StreamSocket.checkConnect(socket);
        //gui du lieu ve client
        try {
            new StreamSocket<SV_ListCategoryComic>().sendDataToCLient(socket, CategoryDAO.getAllCategoryComic());
            System.out.println("Du lieu da gui thanh cong ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
