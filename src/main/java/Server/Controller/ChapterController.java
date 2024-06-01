package Server.Controller;

import Connect.StreamSocket;
import DAO.ChapterDAO;
import DAO.ComicsDAO;
import Server.ObjectGson.GsonForClient.CL_Chapter;
import Server.ObjectGson.GsonForClient.CL_IdCategory;
import Server.ObjectGson.GsonForServer.SV_CategoryName;
import Server.ObjectGson.GsonForServer.SV_Chapter;
import com.google.gson.Gson;

import java.net.Socket;

public class ChapterController {
    public static void responeAllImagesOfChapter(Socket socket) throws Exception { // tra ve ten the loai theo id the loai
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String chapterInfomationJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_Chapter chapterInfomation = gson.fromJson(chapterInfomationJson,CL_Chapter.class);

        SV_Chapter resqoneData = ChapterDAO.getAllImagesOfChapter(chapterInfomation.getIdComics(),chapterInfomation.getChapter());
        new StreamSocket<SV_Chapter>().sendDataToCLient(socket,resqoneData);
    }
}
