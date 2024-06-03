package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import Server.ObjectGson.GsonForServer.SV_ListComicsInformations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    public static SV_ListComicsInformations selectAllComicsHistory(int idUser) {  // lay tat ca truyen trong ds lich su
        Connection connection = DatabaseConnect.getConnect();
        SV_ListComicsInformations listComicsFollow = new SV_ListComicsInformations();
        //bien dung de luu tat ca cac idComics tra ve tu cau query dau tien
        ArrayList<String> listIdComics = new ArrayList<>();
        try {
            // tao ra cau query sql de lay tat ca idComics
            String querySQL = "SELECT  `idComics` FROM `historyreadcomic` WHERE idUser = ?";
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
