package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_Chapter;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RequestDeleteComic {
    public static String deleleComic(SV_ComicsInformation sv_comicsInformation, SV_Chapter sv_chapter){
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();

        String confirmation = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/delele/comic");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();
            //check connect
            Connect.receiveStatus(socket);

            // Gửi dữ liệu kiểm tra lên server
            String jsonComicInfor = gson.toJson(sv_comicsInformation);
            fromClient.write(jsonComicInfor);
            fromClient.newLine();
            fromClient.flush();
            Connect.receiveStatus(socket);

            //gui du lieu lan 3
            String jsonChapter = gson.toJson(sv_chapter);
            fromClient.write(jsonChapter);
            fromClient.newLine();
            fromClient.flush();

            // Đọc dữ liệu xác nhận đăng ký từ server
            confirmation = fromServer.readLine();
            System.out.println("Server response: " + confirmation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confirmation;
    }
}
