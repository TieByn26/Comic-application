package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_CategoryComics;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import ObjectGson.GsonForServer.SV_ListComicsInformations;
import ObjectGson.GsonForServer.SV_listCategory;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GetInformationCategory {
    public static ArrayList<SV_CategoryComics> getAllCategoryInformation() {
        // tạo một đối tượng gson đẻ truyền thông tin giữa server và client
        Gson gson = new Gson();

        //tạo 1 kết nối đến server
        Socket socket = Connect.getSocket();
        CL_Request req = new CL_Request("/get/AllCategory");
        // tạo ArrayList để lưu dữ liệu từ server
        ArrayList<SV_CategoryComics> listComicsInformation = new ArrayList<>();

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            //nhận và đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String categoryInformationJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_listCategory dataConvertFromServer = gson.fromJson(categoryInformationJson, SV_listCategory.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_CategoryComics data : dataConvertFromServer.getListCategory()) {
                    listComicsInformation.add(data);
                }
            }
            else {
                System.out.println("/get/AllCategory is null");
            }
            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listComicsInformation;
    }
}
