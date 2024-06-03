package Server.Controller;

import Connect.StreamSocket;
import DAO.StatisticsDAO;
import Server.ObjectGson.GsonForClient.CL_Chapter;
import Server.ObjectGson.GsonForClient.CL_Comments;
import Server.ObjectGson.GsonForClient.CL_IdComics;
import Server.ObjectGson.GsonForClient.CL_Statistics;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_Statistic;
import com.google.gson.Gson;

import java.net.Socket;

public class StatisticsController {
    public static void responeAllViews(Socket socket) throws Exception { // tra ve tat ca luot xem
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idComicsJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdComics idComics = gson.fromJson(idComicsJson,CL_IdComics.class);

        SV_Statistic svStatistic = StatisticsDAO.selectAllView(idComics.getIdComics());
        new StreamSocket<SV_Statistic>().sendDataToCLient(socket,svStatistic);
    }
    public static void updateView(Socket socket) { // cap nhat lai so luot dislike
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String statisticsInformation = StreamSocket.readGsonFromClient(socket);

        CL_Statistics statisticsClass = gson.fromJson(statisticsInformation,CL_Statistics.class);

        SV_CheckUpdate statusUpdate = StatisticsDAO.updateView(statisticsClass.getIdComics(),statisticsClass.getAllView());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
}
