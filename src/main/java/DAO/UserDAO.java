package DAO;

import Connect.DatabaseConnect;
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
}
