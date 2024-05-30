package ConnectServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connect {
    private static String ipServer = "";
    private static int port = 5525;

    public static Socket getSocket() {
        Socket socket;
        try {
            socket = new Socket(ipServer, port);
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to the server", e);
        }
        return socket;
    }

    // đọc dữ liệu xác nhận gửi từ Server
    public static void receiveStatus(Socket socket){
        BufferedReader fromServer = null;
        try {
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(fromServer.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
