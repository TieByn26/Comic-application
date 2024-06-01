package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_Comments;
import Server.ObjectGson.GsonForServer.SV_User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static int getInforUser(SV_User sv_user){
        int ketqua = 0;
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "INSERT INTO account ";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                return ketqua;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    // ham tra ve thong tin fullName, avatar cua user, dung cho comment
    public static SV_User getInforUserForComment(int idUser) {
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_User inforUser = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `fullName`, `avatar` FROM `user` WHERE idUser = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setInt(1,idUser);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
                String fullName = rs.getString(1);
                String avatar = rs.getString(2);

                inforUser = new SV_User(fullName,avatar);
            }
            else {
                System.out.println("selectAllCommentByIdComics is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + inforUser);

        return inforUser;
    }
}
