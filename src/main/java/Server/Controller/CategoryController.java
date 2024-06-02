package Server.Controller;

import Connect.StreamSocket;
import DAO.CategoryDAO;
import DAO.ComicsDAO;
import Server.ObjectGson.GsonForClient.CL_IdCategory;
import Server.ObjectGson.GsonForClient.CL_IdComics;
import Server.ObjectGson.GsonForServer.SV_CategoryManager;
import Server.ObjectGson.GsonForServer.SV_CategoryName;
import Server.ObjectGson.GsonForServer.SV_listCategory;
import com.google.gson.Gson;

import java.net.Socket;

public class CategoryController {
    public static void responeIdCategoryByIdComics(Socket socket) throws Exception { // tra ve id the loai
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idComicsJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdComics idComics = gson.fromJson(idComicsJson,CL_IdComics.class);

        SV_CategoryManager idCategory = CategoryDAO.selectIdCategoryByIdComics(idComics.getIdComics());
        new StreamSocket<SV_CategoryManager>().sendDataToCLient(socket,idCategory);
    }

    public static void responeCategoryNameByIdCategory(Socket socket) throws Exception { // tra ve ten the loai theo id the loai
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idCategoryJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdCategory idCategory = gson.fromJson(idCategoryJson,CL_IdCategory.class);

        SV_CategoryName categoryName = CategoryDAO.selectCategoryNameByIdCategory(idCategory.getIdCategory());
        new StreamSocket<SV_CategoryName>().sendDataToCLient(socket,categoryName);
    }
    public static void selectAllCategory(Socket socket) throws Exception { // tra ve thong tin tat ca the loai
        System.out.println(21);
        SV_listCategory listCategory = CategoryDAO.selecAllCategory();
        new StreamSocket<SV_listCategory>().sendDataToCLient(socket,listCategory);
    }
}
