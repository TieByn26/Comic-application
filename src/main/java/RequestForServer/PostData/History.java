package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_History;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_CheckUpdate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class History {
    public static int updateChapterOfHistory(String idComics, int idUser, int chapter) {
        Gson gson  = new Gson();

        Socket socket = Connect.getSocket();

        int statusUpdate = 0;

        CL_Request req = new CL_Request("/update/ChapterHistory");
        CL_History reqHistory = new CL_History(idUser,idComics,chapter);

        String reqJson = gson.toJson(req);
        String reqHistoryJson = gson.toJson(reqHistory);
        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(reqHistoryJson + "\n");
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
                System.out.println("/update/ChapterHistory fail");
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
