package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForClient.CL_UsernameToNotifi;
import Server.ObjectGson.GsonForServer.SV_CheckUsername;
import Server.ObjectGson.GsonForServer.SV_ListUser;
import Server.ObjectGson.GsonForServer.SV_Notification;
import Server.ObjectGson.GsonForServer.SV_User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAO {
    public static SV_CheckUsername checkUsername(CL_UsernameToNotifi cl_usernameToNotifi){
        SV_CheckUsername svCheckUsername = new SV_CheckUsername();
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT idUser FROM user WHERE fullName = ?";
            try(PreparedStatement pstm = con.prepareStatement(sql)){
                pstm.setString(1,cl_usernameToNotifi.getUsername());
                ResultSet rs = pstm.executeQuery();
                if (rs.next()){
                    svCheckUsername.setCheck(true);
                    svCheckUsername.setIdUser(rs.getInt("idUser"));
                }
                System.out.println("da thuc hien query: "+sql);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return svCheckUsername;
    }
    public static void sendNotiToUser(int id, String content, Boolean status){
        try (Connection connection = DatabaseConnect.getConnect()){
            String sql = "INSERT INTO notifications(idUser,content,status) VALUES (?,?,?)";
            try(PreparedStatement pstm = connection.prepareStatement(sql)){
                pstm.setInt(1,id);
                pstm.setString(2,content);
                pstm.setBoolean(3,status);

                int ketqua = pstm.executeUpdate();
                System.out.println("da thuc thi query: "+sql);
                System.out.println("co "+ketqua+" dong duoc thay doi");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static SV_ListUser getAllUser(){
        SV_ListUser sv_listUser = new SV_ListUser();
        ArrayList<SV_User> list = new ArrayList<>();
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT * FROM user";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                ResultSet rs = pstm.executeQuery();
                while (rs.next()){
                    list.add(new SV_User(
                            rs.getInt("idUser")
                    ));
                }
                sv_listUser.setList(list);

                System.out.println("da thuc hien query: "+sql);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return sv_listUser;
    }
}
