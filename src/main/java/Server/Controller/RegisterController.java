package Server.Controller;

import Connect.StreamSocket;
import DAO.AccountDAO;
import DAO.UserDAO;
import Server.ObjectGson.GsonForClient.CL_RegisterInformation;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class RegisterController {
    public static void resisterNewAcount(Socket socket) throws Exception{
        Gson gson = new Gson();
        //check ket noi
        StreamSocket.checkConnect(socket);
        //doc du lieu lan 2
        BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String fromClient = request.readLine();
        System.out.println("du lieu dang ky : "+fromClient);
        //anh xa du lieu vao model
        CL_RegisterInformation cl_registerInformation = gson.fromJson(fromClient, CL_RegisterInformation.class);

        try {
            //thuc hien luu du lieu len database
            AccountDAO.saveNewAccount(cl_registerInformation);
            int idUser = UserDAO.getIdByUsername(cl_registerInformation);
            UserDAO.saveNewUser(cl_registerInformation, idUser);
            //gui du lieu thong bao dang ky thanh cong
            DataOutputStream response = new DataOutputStream(socket.getOutputStream());
            response.writeBytes("dang ky thanh cong\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
