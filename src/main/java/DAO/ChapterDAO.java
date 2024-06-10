package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_Chapter;
import Server.ObjectGson.GsonForServer.SV_ChapterOfComics;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public static void addNewChapter(SV_Chapter sv_chapter){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "INSERT INTO chapter(idComics,chapter,linkImages) VALUES (?,?,?)";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1,sv_chapter.getIdComic());
                pstm.setInt(2,sv_chapter.getChapter());
                pstm.setString(3,sv_chapter.getLinkImage());

                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi sql: " + sql);
                System.out.println("Co " + ketqua + " dong duoc thay doi");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void deleteChapter(SV_ComicsInformation sv_chapter){
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "DELETE FROM chapter WHERE id = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1, sv_chapter.getIdComic());

                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi sql: " + sql);
                System.out.println("Co " + ketqua + " dong duoc thay doi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
