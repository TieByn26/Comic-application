package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_Statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatisticsDAO {
    public static SV_Statistic selectAllView(String idComics) {  //ham tong so views cua truyen
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_Statistic allViews = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `allViews`  FROM `statistics` WHERE idComics = ?";
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
    public static SV_CheckUpdate updateView(String idComics, int numberOfView) {  // ham cap nhat lai so luot view
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        int checkPerform = 0;
        try {
            // tao ra cau query sql
            String querySQL = "UPDATE `statistics` SET `allViews`= ? WHERE idComics = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,numberOfView);
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
}
