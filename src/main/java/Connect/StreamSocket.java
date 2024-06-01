package Connect;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class StreamSocket<T> {
    public StreamSocket() {

    }
    //doc du lieu json tu client
    public static String readGsonFromClient(Socket socket){
        String json = "";
        try{
            //doc du lieu json duoc gui tu client
            BufferedReader jsonToClient = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            json = jsonToClient.readLine();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return json;
    }
    //gui du lieu den client
    public Boolean sendDataToCLient(Socket socket, T data){
        Gson gson = new Gson();
        try{
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());

            // chuyen doi data thanh json
            String json = gson.toJson(data);

            toClient.write(json.getBytes(StandardCharsets.UTF_8));
            //dam bao du lieu duoc gui day du
            toClient.flush();
            System.out.println("Dữ liệu server đã gửi: "+ json);
            // dong socket de giai phong tai nguyen
            socket.close();
            System.out.println("kết thúc kết nối đến client!");
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        if (socket.isClosed()){
            return true;
        } else {
            return false;
        }
    }

    //check trang thai ket noi
    public static void checkConnect(Socket socket){
        DataOutputStream fromServer = null;
        try {
            fromServer = new DataOutputStream(socket.getOutputStream());
            fromServer.writeBytes("Check connect: Okey" + "\n");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
