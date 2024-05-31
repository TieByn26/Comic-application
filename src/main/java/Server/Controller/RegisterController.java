package Server.Controller;

import Connect.StreamSocket;
import DAO.AccountDAO;
import DAO.UserDAO;
import Server.ObjectGson.GsonForClient.CL_RegisterInformation;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;

public class RegisterController {
    public static void resisterNewAcount(Socket socket) throws Exception{
        Gson gson = new Gson();
        //kiem tra ket noi
        StreamSocket.checkConnect(socket);
        //doc du lieu lan 2
        String fromClient = StreamSocket.readGsonFromClient(socket);
        //anh xa du lieu vao model
        CL_RegisterInformation cl_registerInformation = gson.fromJson(fromClient, CL_RegisterInformation.class);

        try {
            //thuc hien luu du lieu len database
            AccountDAO.saveNewAccount(cl_registerInformation);
            UserDAO.saveNewUser(cl_registerInformation);
            //gui du lieu thong bao dang ky thanh cong
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes("Dang ky thanh cong!");
            toClient.flush();

        }catch (Exception e){
            //gui du lieu thong bao dang ky that bai
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes("Dang ky khong thanh cong!");
            toClient.flush();
        }
    }
}
