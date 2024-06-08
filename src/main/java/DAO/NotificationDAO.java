package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_ListNotification;
import Server.ObjectGson.GsonForServer.SV_Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NotificationDAO {
    public static SV_ListNotification getAllNotification(int idUser) {  // lay tat ca thong bao theo iduser
         SV_ListNotification listNotification = new SV_ListNotification();
        try{
            Connection connection = DatabaseConnect.getConnect();

            String query = "SELECT  `idNotification`, `content`, `status` FROM `notifications` WHERE idUser = ?";

            PreparedStatement st = connection.prepareStatement(query);

            st.setInt(1,idUser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idNotification = rs.getInt(1);
                String content = rs.getString(2);
                boolean status = rs.getBoolean(3);

                SV_Notification newNotification = new SV_Notification(idNotification,content,status);
                listNotification.getListNotification().add(newNotification);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return listNotification;
    }

    public static SV_CheckUpdate updateStatus(int idNotificaiton) {  // cap nhat trang thai da doc thong bao
        SV_CheckUpdate statusUpdate = null;
        try{
            Connection connection = DatabaseConnect.getConnect();

            String query = "UPDATE `notifications` SET `status`='1' WHERE idUser = ?";

            PreparedStatement st = connection.prepareStatement(query);

            st.setInt(1,idNotificaiton);

            int checkPerForm = st.executeUpdate();

            statusUpdate = new SV_CheckUpdate(checkPerForm);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return statusUpdate;
    }

    public static SV_CheckUpdate deletedNotification(int idNotification) {  // xoa thong bao
        SV_CheckUpdate statusUpdate = null;
        try{
            Connection connection = DatabaseConnect.getConnect();

            String query = "DELETE FROM `notifications` WHERE idNotification = ? ";

            PreparedStatement st = connection.prepareStatement(query);

            st.setInt(1, idNotification);

            int checkPerForm = st.executeUpdate();

            statusUpdate = new SV_CheckUpdate(checkPerForm);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return statusUpdate;
    }
}
