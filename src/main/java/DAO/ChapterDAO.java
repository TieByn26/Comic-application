package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_Chapter;
import Server.ObjectGson.GsonForServer.SV_ChapterOfComics;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import Server.ObjectGson.GsonForServer.SV_ListChapter;

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
    public static void addUpdateChapter(SV_Chapter sv_chapter){
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
    public static void updateChapter(SV_Chapter sv_chapter){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "UPDATE chapter SET linkImages = ? WHERE idComics = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1,sv_chapter.getIdComic());
                pstm.setString(2,sv_chapter.getLinkImage());

                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi sql: " + sql);
                System.out.println("Co " + ketqua + " dong duoc thay doi");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static Boolean checkChapter(SV_Chapter sv_chapter){
        Boolean check = true;
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT * FROM chapter WHERE chapter = ? and idComics = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setInt(1, sv_chapter.getChapter());
                pstm.setString(2,sv_chapter.getIdComic());

                ResultSet rs = pstm.executeQuery();
                if (rs.next()){
                    System.out.println(rs.next());
                    System.out.println("co du lieu");
                    check = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
    public static void addNewChapter(SV_Chapter sv_chapter){
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "INSERT INTO chapter(idComics,chapter,linkImages) VALUES (?,?,?)";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                pstm.setString(1,sv_chapter.getIdComic());
                pstm.setInt(2,sv_chapter.getChapter());
                pstm.setString(3, sv_chapter.getLinkImage());

                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi sql: " + sql);
                System.out.println("Co " + ketqua + " dong duoc thay doi");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static SV_ListChapter getAllChapter(){
        SV_ListChapter sv_listChapter = new SV_ListChapter();
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT * FROM chapter";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                ResultSet rs = pstm.executeQuery();

                while (rs.next()){
                    sv_listChapter.getListChapter().add(
                            new SV_Chapter(
                                    rs.getString("idComics"),
                                    rs.getInt("chapter"),
                                    rs.getString("linkImages")
                            )
                    );
                }
                System.out.println("da thuc query: "+sql);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return sv_listChapter;
    }

}
