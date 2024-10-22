package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_IdUser;
import ObjectGson.GsonForClient.CL_IdUserAndLinkAvatar;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForClient.CL_User;
import ObjectGson.GsonForServer.SV_CheckUpdate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class User {
    public static int updateExperienceAndLevelUser(int idUser) {
        Gson gson  = new Gson();

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        CL_Request req = new CL_Request("/update/ExperienceAndLevelUser");
        CL_IdUser reqUser = new CL_IdUser(idUser);

        String reqJson = gson.toJson(req);
        String reqUserJson = gson.toJson(reqUser);
        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(reqUserJson + "\n");
            sendReqtoServer.flush();

            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String statusUpdateJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CheckUpdate dataConvertFromServer = gson.fromJson(statusUpdateJson, SV_CheckUpdate.class);

            if(dataConvertFromServer.getStatusUpdate() > 0) {
                statusUpdate = dataConvertFromServer.getStatusUpdate();
            }
            else {
                System.out.println("/update/ExperienceAndLevelUser is fail");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return statusUpdate;
    }

    public static int updateStoryUser(int idUser,String story) {
        Gson gson  = new Gson();

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        CL_Request req = new CL_Request("/update/StoryUser");
        CL_User reqUser = new CL_User(idUser,story);

        String reqJson = gson.toJson(req);
        String reqUserJson = gson.toJson(reqUser);
        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(reqUserJson + "\n");
            sendReqtoServer.flush();

            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String statusUpdateJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CheckUpdate dataConvertFromServer = gson.fromJson(statusUpdateJson, SV_CheckUpdate.class);

            if(dataConvertFromServer.getStatusUpdate() > 0) {
                statusUpdate = dataConvertFromServer.getStatusUpdate();
            }
            else {
                System.out.println("/update/StoryUser is fail");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return statusUpdate;
    }
    public static int updateAvatarUser(int idUser,String linkavatar) {
        Gson gson  = new Gson();

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        CL_Request req = new CL_Request("/get/AvatarUser");
        CL_IdUserAndLinkAvatar reqUser = new CL_IdUserAndLinkAvatar(idUser,linkavatar);
        System.out.println(idUser);
        String reqJson = gson.toJson(req);
        String reqUserJson = gson.toJson(reqUser);
        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(reqUserJson + "\n");
            sendReqtoServer.flush();

            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String statusUpdateJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CheckUpdate dataConvertFromServer = gson.fromJson(statusUpdateJson, SV_CheckUpdate.class);

            if(dataConvertFromServer.getStatusUpdate() > 0) {
                statusUpdate = dataConvertFromServer.getStatusUpdate();
            }
            else {
                System.out.println("/get/AvatarUser is fail");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return statusUpdate;
    }
}
