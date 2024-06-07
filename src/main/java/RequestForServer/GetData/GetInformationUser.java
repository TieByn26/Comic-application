package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_IdUser;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GetInformationUser {
    public static SV_User getInforUserForComment(int idUser) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_User infotUser = null;
        CL_Request req = new CL_Request("/get/InforUserForComment");

        // data de server thuc hien query
        CL_IdUser cl_idUser = new CL_IdUser(idUser);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String cl_idUserJson = gson.toJson(cl_idUser);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(cl_idUserJson + "\n");
            sendReqtoServer.flush();


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String inforUserJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_User dataConvertFromServer = gson.fromJson(inforUserJson, SV_User.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                infotUser = dataConvertFromServer;
            }
            else {
                System.out.println("/get/InforUserForComment is null");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return infotUser;
    }

    public static SV_User getAllInforUserByIdUser(int idUser) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_User infotUser = null;
        CL_Request req = new CL_Request("/get/AllInformationUser");

        // data de server thuc hien query
        CL_IdUser cl_idUser = new CL_IdUser(idUser);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String cl_idUserJson = gson.toJson(cl_idUser);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(cl_idUserJson + "\n");
            sendReqtoServer.flush();


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String inforUserJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_User dataConvertFromServer = gson.fromJson(inforUserJson, SV_User.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                infotUser = dataConvertFromServer;
            }
            else {
                System.out.println("get/AllInformationUser is null");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return infotUser;
    }
}
