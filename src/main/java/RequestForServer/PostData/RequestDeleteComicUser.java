package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_ComicOfUser;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RequestDeleteComicUser {
    public static void deleleComicUser(SV_ComicOfUser sv_comicOfUser){
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();

        String confirmation = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/delele/comic/of/user");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();
            //check connect
            Connect.receiveStatus(socket);

            // Gửi dữ liệu kiểm tra lên server
            String jsonComicInfor = gson.toJson(sv_comicOfUser);
            fromClient.write(jsonComicInfor);
            fromClient.newLine();
            fromClient.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
