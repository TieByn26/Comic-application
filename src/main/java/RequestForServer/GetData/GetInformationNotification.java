package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_IdUser;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_CategoryComics;
import ObjectGson.GsonForServer.SV_ListNotification;
import ObjectGson.GsonForServer.SV_Notification;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GetInformationNotification {
    public static ArrayList<SV_Notification> getAllCategoryInformation(int idUser) {
        // tạo một đối tượng gson đẻ truyền thông tin giữa server và client
        Gson gson = new Gson();

        //tạo 1 kết nối đến server
        Socket socket = Connect.getSocket();
        CL_Request req = new CL_Request("/get/AllNotificaitons");
        CL_IdUser reqIdUser = new CL_IdUser(idUser);
        // tạo ArrayList để lưu dữ liệu từ server
        ArrayList<SV_Notification> listNotification = new ArrayList<>();
        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String reqIdUserJson = gson.toJson(reqIdUser);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            Connect.receiveStatus(socket);

            sendReqtoServer.write(reqIdUserJson + "\n");
            sendReqtoServer.flush();

            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String categoryInformationJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ListNotification dataConvertFromServer = gson.fromJson(categoryInformationJson, SV_ListNotification.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_Notification data : dataConvertFromServer.getListNotification()) {
                    listNotification.add(data);
                }
            }
            else {
                System.out.println("/get/AllNotificaitons is null");
            }
            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listNotification;
    }
}
