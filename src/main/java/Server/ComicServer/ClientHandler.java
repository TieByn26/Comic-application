package Server.ComicServer;

import Server.ObjectGson.GsonForClient.CL_Request;
import com.google.gson.Gson;
import com.sun.net.httpserver.Request;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    public static void ExecuteClientRequest(Socket socket){
        try {
            Gson gson = new Gson();
            //doc du lieu
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientRequest = fromClient.readLine();
            System.out.println(clientRequest);
            //chuyen doi du lieu gson thanh doi tuong java va anh xa vao model
            CL_Request clRequest = gson.fromJson(clientRequest, CL_Request.class);

            //thuc hien request
            switch (clRequest.getRequest()){

                default:
                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
