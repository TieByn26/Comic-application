package Server.Controller;

import Connect.StreamSocket;
import DAO.CategoryDAO;
import DAO.ChapterDAO;
import DAO.ComicsDAO;
import DAO.StatisticsDAO;
import Server.ObjectGson.GsonForServer.*;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;

public class NewComicController {
    public static void addNewComic(Socket socket){
        Gson gson = new Gson();
//        check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 2
        String jsonComicInfor = StreamSocket.readGsonFromClient(socket);
        SV_ComicsInformation sv_comicsInformation = gson.fromJson(jsonComicInfor, SV_ComicsInformation.class);
        System.out.println("DU LIEU "+sv_comicsInformation);
        //check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 3
        String jsonChapter = StreamSocket.readGsonFromClient(socket);
        SV_Chapter sv_chapter = gson.fromJson(jsonChapter, SV_Chapter.class);
        System.out.println("DU LIEU "+sv_chapter);
        //check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 4
        String jsonCategory = StreamSocket.readGsonFromClient(socket);
        SV_CategoryManager sv_categoryManager = gson.fromJson(jsonCategory, SV_CategoryManager.class);
        System.out.println("DU LIEU "+sv_categoryManager);
        //check status
        StreamSocket.checkConnect(socket);
        //lay du lieu lan 5
        String jsonCateComic = StreamSocket.readGsonFromClient(socket);
        SV_CategoryComics sv_categoryComics = gson.fromJson(jsonCateComic, SV_CategoryComics.class);
        System.out.println("DU LIEU "+sv_categoryComics);

        try {
            //thuc hien luu du lieu vao database
            Boolean check = ComicsDAO.checkNamecomic(sv_comicsInformation);

            if (check) {
                ComicsDAO.addNewComic(sv_comicsInformation);
                ComicsDAO.addStatistics(sv_comicsInformation);
                ChapterDAO.addNewChapter(sv_chapter);
                String idCate = CategoryDAO.getIdCategory(sv_categoryComics);
                System.out.println("Id the loai dc lay ra: "+idCate);
                CategoryDAO.addCategoryForComic(sv_categoryManager,idCate);
            } else {
                if (ChapterDAO.checkChapter(sv_chapter)) {
                    ChapterDAO.addUpdateChapter(sv_chapter);
                    int numberChapter = ComicsDAO.getNumChapter(sv_comicsInformation);
                    ComicsDAO.updateChapterNumber2(sv_comicsInformation, numberChapter);
                }else {
                    ChapterDAO.updateChapter(sv_chapter);
                }
            }

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
    public static void updateComic(Socket socket){
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
            ComicsDAO.updateComics(sv_comicsInformation);
            CategoryDAO.updateCategory(sv_categoryManager);
            if (ChapterDAO.checkChapter(sv_chapter)) {
                ChapterDAO.addUpdateChapter(sv_chapter);
                int numberChapter = ComicsDAO.getNumChapter(sv_comicsInformation);
                ComicsDAO.updateChapterNumber2(sv_comicsInformation, numberChapter);
            } else {
                ChapterDAO.updateChapter(sv_chapter);
            }

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
    public static void deleteComic(Socket socket){
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
        try {
            //thuc hien xoa truyen
            ComicsDAO.deleteComic(sv_comicsInformation, sv_chapter);
            ComicsDAO.updateChapterNumber(sv_comicsInformation);
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
