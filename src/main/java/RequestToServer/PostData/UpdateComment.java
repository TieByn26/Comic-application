package RequestToServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.*;
import ObjectGson.GsonForServer.SV_CheckUpdate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class UpdateComment {
    public static int updateNumberOfLike (int idComment, int numberOflike) { //ham cap nhat lai so luot like
        Gson gson  = new Gson();

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        CL_Request req = new CL_Request("/update/numberOfLike");
        //data lan 1
        CL_IdComment reqIdComment = new CL_IdComment(idComment);
        //data lan 2
        CL_NumberOfLike reqNumberOfLike = new CL_NumberOfLike(numberOflike);

        String reqJson = gson.toJson(req);
        String idCommentJson = gson.toJson(reqIdComment);
        String numberOfLikeJson = gson.toJson(reqNumberOfLike);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(idCommentJson + "\n");
            sendReqtoServer.flush();

            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(numberOfLikeJson + "\n");
            sendReqtoServer.flush();

            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String statusUpdateJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CheckUpdate dataConvertFromServer = gson.fromJson(statusUpdateJson, SV_CheckUpdate.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                statusUpdate = dataConvertFromServer.getStatusUpdate();
            }
            else {
                System.out.println("/get/InforUserForComment is null");
            }

            sendReqtoServer.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return statusUpdate;
    }

    public static int updateNumberOfDislike (int idComment, int numberOfDislike) { //ham cap nhat lai so luot dislike
        Gson gson  = new Gson();

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        CL_Request req = new CL_Request("/update/numberOfDislike");
        //data lan 1
        CL_IdComment reqIdComment = new CL_IdComment(idComment);
        //data lan 2
        CL_NumberOfDislike reqNumberOfDislike = new CL_NumberOfDislike(numberOfDislike);

        String reqJson = gson.toJson(req);
        String idCommentJson = gson.toJson(reqIdComment);
        String numberOfDislikeJson = gson.toJson(reqNumberOfDislike);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(idCommentJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(numberOfDislikeJson + "\n");
            sendReqtoServer.flush();
            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String statusUpdateJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CheckUpdate dataConvertFromServer = gson.fromJson(statusUpdateJson, SV_CheckUpdate.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer.getStatusUpdate() > 0) {
                statusUpdate = dataConvertFromServer.getStatusUpdate();
            }
            else {
                System.out.println("/update/numberOfDislike fail");
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
    public static int createNewComment ( String idComics, int idUser, String comment) {
        Gson gson  = new Gson();

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        CL_Request req = new CL_Request("/update/newComment");
        //data lan 1
        CL_Comments reqComment = new CL_Comments(idUser,idComics,comment);

        String reqJson = gson.toJson(req);
        String reqCommentJson = gson.toJson(reqComment);
        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(reqCommentJson + "\n");
            sendReqtoServer.flush();

            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String statusUpdateJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CheckUpdate dataConvertFromServer = gson.fromJson(statusUpdateJson, SV_CheckUpdate.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer.getStatusUpdate() > 0) {
                statusUpdate = dataConvertFromServer.getStatusUpdate();
            }
            else {
                System.out.println("/update/newComment fail");
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
