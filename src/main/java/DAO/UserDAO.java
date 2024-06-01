package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForClient.CL_LoginInformation;
import Server.ObjectGson.GsonForClient.CL_RegisterInformation;
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
    public static void saveNewUser(CL_RegisterInformation cl_registerInformation, int idUser){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "INSERT INTO user(idUser,fullName,avatar) VALUES (?,?,?)";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setInt(1,idUser);
                pstm.setString(2, cl_registerInformation.getUsername());
                pstm.setString(3, "src/main/resources/image/icons8-tanjiro-kamado-48.png");
                int ketqua = pstm.executeUpdate();
                System.out.println("da thuc thi query: "+sql);
                System.out.println("co "+ketqua+" dong duoc thay doi");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static int getIdByUsername(CL_RegisterInformation cl_registerInformation){
        int idUser = 0;
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT idUser FROM account WHERE username = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                pstm.setString(1,cl_registerInformation.getUsername());
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        idUser = rs.getInt("idUser");
                        System.out.println("Da thuc thi: "+sql);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return idUser;
    }
    public static int getIdByUsernameLogin(CL_LoginInformation cl_loginInformation){
        int idUser = 0;
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT idUser FROM account WHERE username = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                pstm.setString(1,cl_loginInformation.getUsername());
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        idUser = rs.getInt("idUser");
                        System.out.println("Da thuc thi: "+sql);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return idUser;
    }
}
