package Server.Controller;

import Connect.StreamSocket;
import DAO.CategoryDAO;
import DAO.ChapterDAO;
import DAO.ComicsDAO;
import DAO.StatisticsDAO;
import Server.ObjectGson.GsonForServer.SV_CategoryManager;
import Server.ObjectGson.GsonForServer.SV_Chapter;
import Server.ObjectGson.GsonForServer.SV_CheckLogin;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;

public class NewComicController {
    public static void addNewComic(Socket socket){
        Gson gson = new Gson();
        //check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 2
        String jsonComicInfor = StreamSocket.readGsonFromClient(socket);
        SV_ComicsInformation sv_comicsInformation = gson.fromJson(jsonComicInfor, SV_ComicsInformation.class);
        //check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 3
        String jsonChapter = StreamSocket.readGsonFromClient(socket);
        SV_Chapter sv_chapter = gson.fromJson(jsonChapter, SV_Chapter.class);
        //check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 4
        String jsonCategory = StreamSocket.readGsonFromClient(socket);
        SV_CategoryManager sv_categoryManager = gson.fromJson(jsonCategory, SV_CategoryManager.class);

        try {
            //thuc hien luu du lieu vao database
            ComicsDAO.addNewComic(sv_comicsInformation);
            ComicsDAO.addStatistics(sv_comicsInformation);
            ChapterDAO.addNewChapter(sv_chapter);
            CategoryDAO.addCategoryForComic(sv_categoryManager);

            //gui thong bao da them truyen cho client
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            String data = "true";
            toClient.writeBytes(data + "\n");
            //dam bao du lieu duoc gui day du
            toClient.flush();
            System.out.println("Dữ liệu server đã gửi: "+ data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void updateComic(){

    }
    public static void deleteComic(Socket socket){
        Gson gson = new Gson();
        //check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 2
        String jsonComicInfor = StreamSocket.readGsonFromClient(socket);
        SV_ComicsInformation sv_comicsInformation = gson.fromJson(jsonComicInfor, SV_ComicsInformation.class);

        try {
            //thuc hien xoa truyen
            ComicsDAO.deleteComic(sv_comicsInformation);
            ChapterDAO.deleteChapter(sv_comicsInformation);
            CategoryDAO.deleteCategory(sv_comicsInformation);
            StatisticsDAO.deleteView(sv_comicsInformation);
            //gui thong bao da them truyen cho client
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            String data = "true";
            toClient.writeBytes(data + "\n");
            //dam bao du lieu duoc gui day du
            toClient.flush();
            System.out.println("Dữ liệu server đã gửi: "+ data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
