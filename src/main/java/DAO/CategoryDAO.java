package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_CategoryManager;
import Server.ObjectGson.GsonForServer.SV_CategoryName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryDAO {
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
