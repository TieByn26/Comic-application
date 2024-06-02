package Server.Controller;

import Connect.StreamSocket;
import DAO.HistoryDAO;
import Server.ObjectGson.GsonForClient.CL_History;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
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
}
