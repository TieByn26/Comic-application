package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForClient.CL_RegisterInformation;
import Server.ObjectGson.GsonForServer.SV_User;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public static void saveNewUser(CL_RegisterInformation cl_registerInformation){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "INSERT INTO user(fullName,avatar) VALUES (?,?)";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1, cl_registerInformation.getUsername());
                pstm.setString(2, "avatar");
                int ketqua = pstm.executeUpdate();
                System.out.println("da thuc thi query: "+sql);
                System.out.println("co "+ketqua+" dong duoc thay doi");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
