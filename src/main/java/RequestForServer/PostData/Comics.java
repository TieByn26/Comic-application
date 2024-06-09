package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForClient.CL_UpComicsByUser;
import ObjectGson.GsonForServer.SV_CheckUpdate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Comics {
    public static int upComicByUser(String nameComic,String category, String status, String avatarComic, String linkImageOfChapter, String description, int chapter,String author) {
        Gson gson = new Gson();

        CL_Request req = new CL_Request("/update/ComicByUser");
        CL_UpComicsByUser dataQuery = new CL_UpComicsByUser(nameComic,author,chapter,linkImageOfChapter,description,status,category,avatarComic);

        String reqJson = gson.toJson(req);
        String dataQueryJson = gson.toJson(dataQuery);

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        try{
            BufferedWriter sendReqtoServer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            Connect.receiveStatus(socket);

            sendReqtoServer.write(dataQueryJson + "\n");
            sendReqtoServer.flush();

            BufferedReader dataFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String statusUpdateJson = dataFromServer.readLine();

            SV_CheckUpdate statusUpdateClass = gson.fromJson(statusUpdateJson,SV_CheckUpdate.class);

            if (statusUpdateClass != null) {
                statusUpdate = statusUpdateClass.getStatusUpdate();
            }
            else {
                System.out.println("up comic is fail");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return statusUpdate;
    }
}
