package Server.ComicServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class    ComicServer {
    private ServerSocket serverSocket;
    private int Port =8083;

    public ComicServer() throws IOException {
        //tao serversocket
        serverSocket = new ServerSocket(Port);
        System.out.printf("Server open port: "+ Port);

        //lap vo han chap nhan cac yeu cau ket noi tu client
        while (true){
            Socket socket = serverSocket.accept();
            System.out.printf("Accept connect a Client!");

            //thuc thi request o 1 luong rieng biet
            Thread threadCLient = new Thread(new Runnable() {
                @Override
                public void run() {
                    ExecuteClientRequest(socket);
                }
            });
            threadCLient.start();
        }
    }
    public void ExecuteClientRequest(Socket socket){

    }
}
