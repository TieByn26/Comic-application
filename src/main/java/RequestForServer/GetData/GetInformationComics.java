package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.*;
import ObjectGson.GsonForServer.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class GetInformationComics {
    //  lay thong tin nhu ten truyen, tong so chapter va avatar truyen de load ra home
    public static ArrayList<SV_ComicsInformation> getAllComics() {
        Gson gson = new Gson();

        Socket socket = Connect.getSocket();
        // tạo ArrayList để lưu dữ liệu từ server
        ArrayList<SV_ComicsInformation> listComicsInformation = new ArrayList<>();

        CL_Request req = new CL_Request("/get/allcomic");

        String reqJson = gson.toJson(req);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            //nhận và đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String comicsInformations = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ListComicsInformations dataConvertFromServer = gson.fromJson(comicsInformations, SV_ListComicsInformations.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_ComicsInformation data : dataConvertFromServer.getListComicsInfomations()) {
                    listComicsInformation.add(data);
                }
            }
            else {
                System.out.println("/get/allcomic");
            }


            sendReqtoServer.close();
            receive.close();
            socket.close();
    }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listComicsInformation;

    }

    public static ArrayList<SV_ComicsInformation> getAllComicsUploadByUser(String userName) {
        Gson gson = new Gson();

        Socket socket = Connect.getSocket();
        // tạo ArrayList để lưu dữ liệu từ server
        ArrayList<SV_ComicsInformation> listComicsInformation = new ArrayList<>();

        CL_Request req = new CL_Request("/get/AllComicsUploadByUser");
        CL_User reqUser = new CL_User(userName);

        String reqJson = gson.toJson(req);
        String reqUserJson = gson.toJson(reqUser);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            Connect.receiveStatus(socket);

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqUserJson + "\n");
            sendReqtoServer.flush();

            //nhận và đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String comicsInformations = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ListComicsInformations dataConvertFromServer = gson.fromJson(comicsInformations, SV_ListComicsInformations.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_ComicsInformation data : dataConvertFromServer.getListComicsInfomations()) {
                    listComicsInformation.add(data);
                }
            }
            else {
                System.out.println("/get/AllComicsUploadByUser");
            }
            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listComicsInformation;
    }


    //  lay thong tin nhu ten truyen, tong so chapter va avatar truyen de load ra home
    public static ArrayList<SV_ComicsInformation> getTopComics() {
        Gson gson = new Gson();

        Socket socket = Connect.getSocket();
        // tạo ArrayList để lưu dữ liệu từ server
        ArrayList<SV_ComicsInformation> listComicsInformation = new ArrayList<>();

        CL_Request req = new CL_Request("/get/TopComics");

        // chuyen req thanh json
        String reqJson = gson.toJson(req);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            //nhận và đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String comicsInformations = receive.readLine();
            System.out.println(comicsInformations);
            // chuyển đổi từ json sang đối tượng
            SV_ListComicsInformations dataConvertFromServer = gson.fromJson(comicsInformations, SV_ListComicsInformations.class);
            System.out.println(dataConvertFromServer);
            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_ComicsInformation data : dataConvertFromServer.getListComicsInfomations()) {
                    listComicsInformation.add(data);
                    System.out.println("top truyen tra ve: " + data);
                }
            }
            else {
                System.out.println("/get/TopComics return null");
            }
            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listComicsInformation;
    }

    //  lay het thong tin truyen de load ra giao dien thong tin truyen
    public static SV_ComicsInformation getFullComicsInformationByNameComics(String nameComics) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_ComicsInformation sv_comicsInformation = null;
        CL_Request req = new CL_Request("/get/comicInformationByNameComics");

        // data de server thuc hien query
        CL_NameComics cl_nameComics = new CL_NameComics(nameComics);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String cl_idComicsJson = gson.toJson(cl_nameComics);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();

            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(cl_idComicsJson + "\n");
            sendReqtoServer.flush();

            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String comicsInformationJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ComicsInformation dataConvertFromServer = gson.fromJson(comicsInformationJson, SV_ComicsInformation.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                sv_comicsInformation = dataConvertFromServer;
            }
            else {
                System.out.println("/get/comicInformationByNameComics is null");
            }
            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sv_comicsInformation;
    }
     // lay so view theo id truyen
    public static SV_Statistic getAllViewByIdComics(String idComics) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_Statistic allView = null;
        CL_Request req = new CL_Request("/get/viewByIdComics");

        // data de server thuc hien query
        CL_IdComics cl_idComics = new CL_IdComics(idComics);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String cl_idComicsJson = gson.toJson(cl_idComics);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(cl_idComicsJson + "\n");
            sendReqtoServer.flush();


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String allViewsJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_Statistic dataConvertFromServer = gson.fromJson(allViewsJson, SV_Statistic.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                allView = dataConvertFromServer;
            }
            else {
                System.out.println("/get/viewByidComics is null");
            }


            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return allView;
    }
    // lay id the loai
    public static SV_CategoryManager getIdCategoryByIdComics(String idComics) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_CategoryManager idCategory = null;
        CL_Request req = new CL_Request("/get/IdCategoryByIdComics");

        // data de server thuc hien query
        CL_IdComics cl_idComics = new CL_IdComics(idComics);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String cl_idComicsJson = gson.toJson(cl_idComics);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(cl_idComicsJson + "\n");
            sendReqtoServer.flush();


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String idCategoryJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CategoryManager dataConvertFromServer = gson.fromJson(idCategoryJson, SV_CategoryManager.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                idCategory = dataConvertFromServer;
            }
            else {
                System.out.println("/get/IdCategoryByIdComics is null");
            }


            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return idCategory;
    }

    // lay ten the loai bang id the loai
    public static SV_CategoryName getCategoryNameByIdCategory(String idCategory) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_CategoryName categoryName = null;
        CL_Request req = new CL_Request("/get/CategoryNameByIdCategory");

        // data de server thuc hien query
        CL_IdCategory cl_idCategory = new CL_IdCategory(idCategory);

        // chuyen req thanh json
        String reqJson = gson.toJson(req);
        String cl_idCategoryJson = gson.toJson(cl_idCategory);

        try {
            BufferedWriter sendReqtoServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(reqJson + "\n");
            sendReqtoServer.flush();
            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            sendReqtoServer.write(cl_idCategoryJson + "\n");
            sendReqtoServer.flush();
            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String categoryNameJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_CategoryName dataConvertFromServer = gson.fromJson(categoryNameJson, SV_CategoryName.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                categoryName = dataConvertFromServer;
            }
            else {
                System.out.println("/get/CategoryNameByIdCategory is null");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }

}
