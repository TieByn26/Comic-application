package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_ComicOfUser;
import Server.ObjectGson.GsonForServer.SV_ListComicOfUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComicUserDAO {
    public static SV_ListComicOfUser getAllComicOfUser(){
        ArrayList<SV_ComicOfUser> list = new ArrayList<>();
        SV_ListComicOfUser sv_listComicOfUser = new SV_ListComicOfUser();
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "SELECT * FROM comicsofuser";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                ResultSet rs = pstm.executeQuery();
                while (rs.next()){
                    list.add(new SV_ComicOfUser(
                            rs.getString("nameComic"),
                            rs.getString("category"),
                            rs.getString("status"),
                            rs.getString("avatarComic"),
                            rs.getString("linkImageOfChapter"),
                            rs.getString("description"),
                            rs.getInt("chapter"),
                            rs.getString("author")
                    ));
                }
                System.out.println("Da thuc hien query: "+sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sv_listComicOfUser.setListComicOfUser(list);
        return sv_listComicOfUser;
    }
    public static void deleteComicOfUser(String nameComic){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "DELETE FROM comicsofuser WHERE nameComic = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1, nameComic);

                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi sql: " + sql);
                System.out.println("Co " + ketqua + " dong duoc thay doi");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
