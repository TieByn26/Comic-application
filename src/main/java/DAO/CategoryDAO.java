package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static SV_CategoryName selectCategoryNameByIdCategory(String idCategory) {  // ham tra ve ten the loai theo id
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

    public static SV_listCategory selecAllCategory() {  // ham tra ve thong tin tat ca the loai
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_listCategory listCategory = new SV_listCategory();
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idCategory`, `categoryInformation` FROM `categorycomics` ";
            PreparedStatement st = connection.prepareStatement(querySQL);
            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String idCategory = rs.getString(1);
                String categoryName = rs.getString(2);

                SV_CategoryComics newCategory = new SV_CategoryComics(idCategory,categoryName);

                listCategory.getListCategory().add(newCategory);
            }
            // dong connect database
            DatabaseConnect.closeConnect(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + listCategory);
        return listCategory;
    }
    public static SV_ListComicsInformations selectALlComicsByCategory(String idCategory) {  // ham tra ve thong tin truyen theo 1 the loai nhat dinh
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_ListComicsInformations listComics = new SV_ListComicsInformations();
        ArrayList<String> listIdComics = new ArrayList<>();
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idComics` FROM `managercategorycomics` WHERE idCategory = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setString(1,idCategory);
            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String idComics = rs.getString(1);
                listIdComics.add(idComics);
            }
            // dong connect database
            DatabaseConnect.closeConnect(connection);

            // lay tat ca thong tin cua tung idComics
             for (String id : listIdComics) {
                 SV_ComicsInformation comicsInformation = ComicsDAO.selectComicsInformationByIdComics(id);
                 listComics.getListComicsInfomations().add(comicsInformation);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + listComics);
        return listComics;
    }
    public static String getIdCategory(SV_CategoryComics sv_categoryComics){
        String idCate = null;
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT * FROM categorycomics WHERE categoryInformation = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                pstm.setString(1,sv_categoryComics.getCategoryName());
                ResultSet rs = pstm.executeQuery();
                if (rs.next()){
                    idCate = rs.getString("idCategory");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idCate;
    }

    public static void addCategoryForComic(SV_CategoryManager sv_categoryManager, String idCate){
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "INSERT INTO managercategorycomics(idComics,idCategory) VALUES (?,?)";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                pstm.setString(1,sv_categoryManager.getIdComic());
                pstm.setString(2,idCate);
                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi sql: " + sql);
                System.out.println("Co " + ketqua + " dong duoc thay doi");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateCategory(SV_CategoryManager sv_categoryManager){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "UPDATE managercategorycomics SET idCategory = ? WHERE idComics = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1,sv_categoryManager.getIdComic());
                pstm.setString(2,sv_categoryManager.getIdCategory());

                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi sql: "+sql);
                System.out.println("Co "+ketqua+" dong duoc thay doi");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static SV_ListCategoryManager getAllCategoryManager(){
        SV_ListCategoryManager sv_listCategoryManager = new SV_ListCategoryManager();
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT * FROM managercategorycomics";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                ResultSet rs = pstm.executeQuery();

                while (rs.next()){
                    sv_listCategoryManager.getListCategoryManager().add(
                            new SV_CategoryManager(
                                    rs.getString("idComics"),
                                    rs.getString("idCategory")
                            )
                    );
                }
                System.out.println("da thuc query: "+sql);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return sv_listCategoryManager;
    }
    public static SV_ListCategoryComic getAllCategoryComic(){
        SV_ListCategoryComic sv_listCategoryComic = new SV_ListCategoryComic();
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT * FROM categorycomics";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                ResultSet rs = pstm.executeQuery();

                while (rs.next()){
                    sv_listCategoryComic.getListCategoryComic().add(
                      new SV_CategoryComics(
                              rs.getString("idCategory"),
                              rs.getString("categoryInformation")
                      )
                    );
                }
                System.out.println("da thuc query: "+sql);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return sv_listCategoryComic;
    }
}
