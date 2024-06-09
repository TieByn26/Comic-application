package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Chapter;
import ObjectGson.GsonForClient.CL_IdCategory;
import ObjectGson.GsonForClient.CL_IdComicsAndIdUser;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_CategoryName;
import ObjectGson.GsonForServer.SV_Chapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GetInformationChapter {
    public static SV_Chapter getAllimageOfChapter(String idComics, int chapter) { // tra ve tat ca cac anh cua chapter
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_Chapter listImages = null;
        CL_Request req = new CL_Request("/get/AllImagesOfChapter");

        // data de server thuc hien query
        CL_Chapter reqChapterClass = new CL_Chapter(idComics,chapter);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String reqChapterJson = gson.toJson(reqChapterClass);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(reqChapterJson + "\n");
            sendReqtoServer.flush();


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String allImagesOfChapterJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_Chapter dataConvertFromServer = gson.fromJson(allImagesOfChapterJson, SV_Chapter.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                listImages = dataConvertFromServer;
            }
            else {
                System.out.println("/get/AllImagesOfChapter is null");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listImages;
    }
}
