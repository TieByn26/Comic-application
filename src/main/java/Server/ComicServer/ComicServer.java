package Server.ComicServer;

import Connect.StreamSocket;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ComicServer{
    private ServerSocket serverSocket;
    private int Port = 5525;



    public ComicServer() throws IOException {
        //tao serversocket
        serverSocket = new ServerSocket(Port);
        System.out.println("Server open port: "+ Port);
        //lap vo han chap nhan cac yeu cau ket noi tu client
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Accept connect a Client!");

            //thuc thi request o 1 luong rieng biet
            Thread threadCLient = new Thread(new Runnable() {
                @Override
                public void run() {
                    ClientHandler.ExecuteClientRequest(socket);
                }
            });
            threadCLient.start();
        }
    }

    public static void main(String[] args) throws IOException {
        new ComicServer();
    }

}
