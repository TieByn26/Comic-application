package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_IdUser;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import ObjectGson.GsonForServer.SV_ListComicsInformations;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GetInformationHistory {
    //  lay thong tin nhu ten truyen, tong so chapter va avatar truyen cua tat ca truyen trong lich su
    public static ArrayList<SV_ComicsInformation> getAllComicsHistoryInformation(int idUser) {
        // tạo một đối tượng gson đẻ truyền thông tin giữa server và client
        Gson gson = new Gson();

        //tạo 1 kết nối đến server
        Socket socket = Connect.getSocket();
        // tạo ArrayList để lưu dữ liệu từ server
        ArrayList<SV_ComicsInformation> listComicsInformation = new ArrayList<>();
        //tao request yêu cầu server thực hiện cái gì
        CL_Request req = new CL_Request("/get/AllComicsHistory");
        CL_IdUser reqIdUser = new CL_IdUser(idUser);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String idUserJson = gson.toJson(reqIdUser);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            Connect.receiveStatus(socket);

            sendReqtoServer.write(idUserJson + "\n");
            sendReqtoServer.flush();

            //nhận và đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String comicsInformations = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ListComicsInformations dataConvertFromServer = gson.fromJson(comicsInformations, SV_ListComicsInformations.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_ComicsInformation data : dataConvertFromServer.getListComicsInfomations()) {
                    listComicsInformation.add(data);
                }
            }
            else {
                System.out.println("/get/AllComicsHistory is null");
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
