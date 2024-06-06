package Server.Controller;

import Connect.StreamSocket;
import DAO.ComicsDAO;
import Server.ObjectGson.GsonForClient.CL_NewComic;
import Server.ObjectGson.GsonForServer.SV_CheckLogin;
import com.google.gson.Gson;

import java.net.Socket;

public class NewComicController {
    public static void addNewComic(Socket socket){
        Gson gson = new Gson();
        //check ket noi
        StreamSocket.checkConnect(socket);
        //doc file du lieu lan 2
        String fromClient = StreamSocket.readGsonFromClient(socket);
        //anh xa vao model
        CL_NewComic cl_newComic = gson.fromJson(fromClient, CL_NewComic.class);
        //thuc hien them moi truyen
        ComicsDAO.addNewComic(cl_newComic);
        try {
            //gui thong bao true ve client
            SV_CheckLogin sv_checkLogin = new SV_CheckLogin();
            sv_checkLogin.setCheck(true);
            new StreamSocket<SV_CheckLogin>().sendDataToCLient(socket, sv_checkLogin);
            System.out.println("Da gui thong bao true ve client");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
