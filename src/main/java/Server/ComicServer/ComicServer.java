package Server.ComicServer;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ComicServer <T>{
    private ServerSocket serverSocket;
    private int Port = 8083;

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

    // Gửi dữ liệu đến Client
    public boolean sendValueFromClient(Socket socket, T value){
        Gson gson = new Gson();
        try {
            DataOutputStream fromClient = new DataOutputStream(socket.getOutputStream());
            String json = gson.toJson(value);
            fromClient.writeBytes(json);
            fromClient.flush();
            System.out.println("Dữ liệu server đã gửi: "+ json);
            // đóng socket
            socket.close();
            System.out.println("Close connect a Client!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(socket.isClosed()){
            return true;
        }else {
            return false;
        }
    }

}
