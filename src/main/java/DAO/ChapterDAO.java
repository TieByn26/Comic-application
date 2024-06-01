package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_Chapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChapterDAO {
    public static SV_Chapter getAllImagesOfChapter(String idComics, int chapter) {  // ham tra ve tat ca anh cua 1 chapter
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_Chapter chapterInformation = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT  `linkImages` FROM `chapter` WHERE idComics = ? and chapter = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,idComics);
            st.setInt(2,chapter);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
                String listImg = rs.getString(1);

                chapterInformation = new SV_Chapter(listImg);
            }
            else {
                System.out.println("getAllImagesOfChapter is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + chapterInformation);

        return chapterInformation;
    }
}
