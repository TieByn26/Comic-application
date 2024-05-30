package Server.ComicServer;

import Server.Controller.ComicController;
import Server.ObjectGson.GsonForClient.CL_Request;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    public static void ExecuteClientRequest(Socket socket){
        try {
            Gson gson = new Gson();
            //doc du lieu
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("ggd12");
            String clientRequest = fromClient.readLine();
            System.out.println(clientRequest);
            //chuyen doi du lieu gson thanh doi tuong java va anh xa vao model
            CL_Request clRequest = gson.fromJson(clientRequest, CL_Request.class);

            //thuc hien request
            switch (clRequest.getRequest()){
                case "/get/allcomic" : {
                    try {
                        ComicController.responeALLComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/comicInformationByNameComics" : {
                    try {

                        ComicController.responeFullComicsInformationByNameComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/viewByIdComics" : {
                    try {

                        ComicController.responeAllViews(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                default:
                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
