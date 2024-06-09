package Server.ComicServer;

import Connect.StreamSocket;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComicServer {
    private ServerSocket serverSocket;
    private int port = 5525;
    private ExecutorService executorService;

    public ComicServer() throws IOException {
        // Ttao server socket
        serverSocket = new ServerSocket(port);
        System.out.println("Server open port: " + port);

        // tao 1 ho voi 1000 luong ket noi
        executorService = Executors.newFixedThreadPool(1000);

        // lap vo han de chap nhan ket noi
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Accept connect a Client!");

            //thuc thi request o luong rieng biet
            executorService.execute(() -> ClientHandler.ExecuteClientRequest(socket));
        }
    }

    public static void main(String[] args) throws IOException {
        new ComicServer();
    }
}
