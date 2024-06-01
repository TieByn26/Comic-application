package DAO;

import Connect.DatabaseConnect;
import Server.Controller.HashController;
import Server.ObjectGson.GsonForClient.CL_ChangePass;
import Server.ObjectGson.GsonForClient.CL_GetOtp;
import Server.ObjectGson.GsonForClient.CL_LoginInformation;
import Server.ObjectGson.GsonForClient.CL_RegisterInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public static void saveNewAccount(CL_RegisterInformation cl_registerInformation){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "INSERT INTO account(username,password,email) VALUES (?,?,?)";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1,cl_registerInformation.getUsername());
                pstm.setString(2,HashController.sha256(cl_registerInformation.getPassword()));
                pstm.setString(3,cl_registerInformation.getEmail());

                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi query: "+sql);
                System.out.println("Co "+ketqua+" dong duoc thay doi");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static Boolean checkLogin(CL_LoginInformation cl_loginInformation){
        try (Connection con = DatabaseConnect.getConnect()){
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1, cl_loginInformation.getUsername());
                pstm.setString(2, HashController.sha256(cl_loginInformation.getPassword()));
                //kiem tra neu dung tra true neu sai tra false
                try (ResultSet rs = pstm.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }
    public static String getEmailForUser(CL_GetOtp cl_getOtp) {
        String email = null;
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "SELECT email FROM account WHERE username = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1, cl_getOtp.getUsername());
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        email = rs.getString("email");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return email;
    }
    public static void changePassword(CL_ChangePass clChangePass){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "UPDATE account SET password = ? WHERE username = ?";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setString(1,HashController.sha256(clChangePass.getNewPass()));
                pstm.setString(2,clChangePass.getUsername());
                int ketqua = pstm.executeUpdate();
                System.out.println("Da thuc thi query: "+sql);
                System.out.println("Co "+ketqua+" dong duoc thay doi");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
