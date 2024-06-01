package RequestToServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_IdComics;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_Comments;
import ObjectGson.GsonForServer.SV_listComment;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GetInformationComment {
    public static ArrayList<SV_Comments> getAllComment(String idComics) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        ArrayList<SV_Comments> allComment = new ArrayList<>();
        CL_Request req = new CL_Request("/get/AllCommentByIdComics");

        // data de server thuc hien query
        CL_IdComics cl_idComics = new CL_IdComics(idComics);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String cl_idComicsJson = gson.toJson(cl_idComics);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            BufferedWriter sendReqtoServer = new BufferedWriter(outputStreamWriter);

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(cl_idComicsJson + "\n");
            sendReqtoServer.flush();


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String listCommentJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_listComment dataConvertFromServer = gson.fromJson(listCommentJson, SV_listComment.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_Comments comment : dataConvertFromServer.getListComment()) {
                    allComment.add(comment);
                }
            }
            else {
                System.out.println("/get/AllCommentByIdComics  is null");
            }

            sendReqtoServer.close();
            outputStreamWriter.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return allComment;
    }

}
