package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.*;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ComicsDAO {
    public static SV_ListComicsInformations selectALLComics() {  // lay thong tin truyen gom: ten truyen, so luong chapter, avatar truyen  (load ra home)
        Connection connection = DatabaseConnect.getConnect();

        SV_ListComicsInformations listComics = new SV_ListComicsInformations();
        try {
            String querySQL = "SELECT `nameComics`, `avatarComcis`, `numberOfChapter` FROM `comicsinformation`";
            PreparedStatement st = connection.prepareStatement(querySQL);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                String avtComics = rs.getString(2);
                int numberOfChapter = rs.getInt(3);

                SV_ComicsInformation newInf = new SV_ComicsInformation(name, avtComics, numberOfChapter);

                //nhet cac doi tuong vao 1 arraylist cua doi tuong SV_ListComicsInformations
                listComics.getListComicsInfomations().add(newInf);
            }

            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listComics;
    }

    public static SV_ComicsInformation selectFullComicsInformationByNameComics(String nameComics) {  //ham lay tat ca thong tin cua truyen
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_ComicsInformation fullComicsInformation = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idComics`, `author`, `status`, `description`, `avatarComcis`, `numberOfChapter` FROM `comicsinformation` WHERE nameComics = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,nameComics);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
              String idComics =  rs.getString(1);
              String author = rs.getString(2);
              String status = rs.getString(3);
              String description = rs.getString(4);
              String avatarComics = rs.getString(5);
              int numberOfChapter = rs.getInt(6);

              // khoi tao doi tuong de luu tru du lieu nhan duoc tu cau query
                fullComicsInformation = new SV_ComicsInformation(idComics,author,status, description,avatarComics,numberOfChapter);
            }
            else {
                System.out.println("selectFullComicsInformationByNameComics is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return fullComicsInformation;

    }

    public static SV_Statistic selectAllView(String idComics) {  //ham tong so views cua truyen
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_Statistic allViews = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `allViews`  FROM `statistics` WHERE idComcis = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,idComics);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
             int allViewFromDatabase = rs.getInt(1);
             allViews = new SV_Statistic(allViewFromDatabase);
             }
            else {
                System.out.println("select allView is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(allViews);

        return allViews;
    }

    public static SV_CategoryManager selectIdCategoryByIdComics(String idComics) {  // ham tra ve id the loai
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_CategoryManager idCategory = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idCategory` FROM `managercategorycomics` WHERE idComics = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,idComics);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
                String idCategoryDatabase = rs.getString(1);
                idCategory = new SV_CategoryManager(idCategoryDatabase);
            }
            else {
                System.out.println("select idcategory is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("database tra ve: " + idCategory);

        return idCategory;
    }

    public static SV_CategoryName selectCategoryNameByIdCategory(String idCategory) {  // ham tra ve id the loai
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_CategoryName categoryName = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT  `categoryInformation` FROM `categorycomics` WHERE idCategory =  ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,idCategory);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
                String categoryInfotmation = rs.getString(1);
                categoryName = new SV_CategoryName(categoryInfotmation);
            }
            else {
                System.out.println("selectCategoryNameByIdCategory is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + categoryName);

        return categoryName;
    }

}
