package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import Server.ObjectGson.GsonForServer.SV_ListComicsInformations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FollowDAO {
    public static SV_CheckUpdate addNewFollow(String idComics, int idUser) {  // ham cap nhat lai danh sach cac truyen theo doi cua nguoi dung
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        int checkPerform = 0;
        try {
            // tao ra cau query sql
            String querySQL = "INSERT INTO `follow`(`idUser`, `idComics`) VALUES (?,?)";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,idUser);
            st.setString(2,idComics);
            // thuc thi cau query
            checkPerform = st.executeUpdate();
            statusUpdate = new SV_CheckUpdate(checkPerform);
            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + checkPerform);
        return statusUpdate;
    }
    public static SV_CheckUpdate deleteFollow(String idComics, int idUser) {  // ham cap nhat lai danh sach cac truyen theo doi cua nguoi dung
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        int checkPerform = 0;
        try {
            // tao ra cau query sql
            String querySQL = "DELETE FROM `follow` WHERE idUser = ?  and idComics = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,idUser);
            st.setString(2,idComics);
            // thuc thi cau query
            checkPerform = st.executeUpdate();
            statusUpdate = new SV_CheckUpdate(checkPerform);
            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + checkPerform);
        return statusUpdate;
    }
    public static SV_CheckUpdate checkFollow(String idComics, int idUser) {  // kiem tra truyen co dang duoc theo doi hay khong
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idUser` FROM `follow` WHERE idUser = ? and idComics = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,idUser);
            st.setString(2,idComics);
            // thuc thi cau query
            ResultSet rs = st.executeQuery();
            // neu co du lieu tra ve thi la dang theo doi
            if (rs.next()) {
                statusUpdate = new SV_CheckUpdate(1);
            }
            else {
                statusUpdate = new SV_CheckUpdate(0);
            }
            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + statusUpdate.getStatusUpdate());
        return statusUpdate;
    }
    public static SV_ListComicsInformations selectAllComicsFollow( int idUser) {  // lay tat ca truyen dang theo doi
        Connection connection = DatabaseConnect.getConnect();
        SV_ListComicsInformations listComicsFollow = new SV_ListComicsInformations();
        //bien dung de luu tat ca cac idComics tra ve tu cau query dau tien
        ArrayList<String> listIdComics = new ArrayList<>();
        try {
            // tao ra cau query sql de lay tat ca idComics
            String querySQL = "SELECT `idComics` FROM `follow` WHERE idUser = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,idUser);
            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                listIdComics.add(rs.getString(1));
            }
            // duyet qua tat ca idComics va lay thong tin tung bo truyen
            for (String idComics : listIdComics) {
                //goi ham de lay thong tin tat ca cac idComics
               SV_ComicsInformation newComicsInformation = ComicsDAO.selectComicsInformationByIdComics(idComics);

               listComicsFollow.getListComicsInfomations().add(newComicsInformation);
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + listComicsFollow.getListComicsInfomations());
        return listComicsFollow;
    }
}
