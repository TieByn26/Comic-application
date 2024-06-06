package Server.Controller;

import Connect.StreamSocket;
import DAO.ChapterDAO;
import DAO.HistoryDAO;
import Server.ObjectGson.GsonForClient.CL_History;
import Server.ObjectGson.GsonForClient.CL_IdUser;
import Server.ObjectGson.GsonForClient.CL_IdComicsAndIdUser;
import Server.ObjectGson.GsonForServer.SV_Chapter;
import Server.ObjectGson.GsonForServer.SV_ChapterOfComics;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_ListComicsInformations;
import com.google.gson.Gson;

import java.net.Socket;

public class HistoryController {
    public static void updateChapterHistory(Socket socket) { // cap nhat lich su truyen
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String historyInformation = StreamSocket.readGsonFromClient(socket);

        CL_History historyClass = gson.fromJson(historyInformation,CL_History.class);

        SV_CheckUpdate statusUpdate = HistoryDAO.updateHistory(historyClass.getIdComics(), historyClass.getIdUser(),historyClass.getChapter());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
    public static void responeComicsHistoryInformationByIdUser(Socket socket) { // lay thong tin tat ca truyen trong ds lich su
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String idUserJson = StreamSocket.readGsonFromClient(socket);

        CL_IdUser idUserClass = gson.fromJson(idUserJson,CL_IdUser.class);

        SV_ListComicsInformations listComicsInformations = HistoryDAO.selectAllComicsHistory(idUserClass.getIdUser());

        new StreamSocket<SV_ListComicsInformations>().sendDataToCLient(socket,listComicsInformations);
    }
    public static void responelastChapter(Socket socket) { // tra ve chapter ma nguoi dung doc cuoi cung
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String dataQueryJson = StreamSocket.readGsonFromClient(socket);

        CL_IdComicsAndIdUser dataQuery = gson.fromJson(dataQueryJson,CL_IdComicsAndIdUser.class);
        System.out.println("thong tin client gui: "  + dataQuery);
        SV_ChapterOfComics chapterInformation = HistoryDAO.selectChapterByIdComicsAndIdUser(dataQuery.getIdUSer(),dataQuery.getIdComics());

        new StreamSocket<SV_ChapterOfComics>().sendDataToCLient(socket,chapterInformation);
    }

}
