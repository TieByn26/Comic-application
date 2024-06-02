package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HistoryDAO {
    public static SV_CheckUpdate updateHistory(String idComics, int idUser, int chapter) {  // kiem tra xem truyen co trong ds lich su chua, neu co thi cap nhat chapter con khogn thi them truyen vao ds lich su
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        try {
            // tao ra cau query sql
            String querySQL = "INSERT INTO historyreadcomic (idUser, idComics, chapter)\n" +
                    "VALUES (?, ?, ?)\n" +
                    "ON DUPLICATE KEY UPDATE chapter = VALUES(chapter)";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1, idUser);
            st.setString(2, idComics);
            st.setInt(3, chapter);
            // thuc thi cau query
            int checkPerform = st.executeUpdate();

            statusUpdate = new SV_CheckUpdate(checkPerform);

            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + statusUpdate.getStatusUpdate());
        return statusUpdate;
    }
}
