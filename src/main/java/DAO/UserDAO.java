package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForClient.CL_LoginInformation;
import Server.ObjectGson.GsonForClient.CL_RegisterInformation;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
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
    public static void saveNewUser(CL_RegisterInformation cl_registerInformation, int idUser){
        try (Connection con = DatabaseConnect.getConnect()) {
            String sql = "INSERT INTO user(idUser,fullName,avatar) VALUES (?,?,?)";
            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setInt(1,idUser);
                pstm.setString(2, cl_registerInformation.getUsername());
                pstm.setString(3, "https://img.icons8.com/?size=100&id=otuYOSexWmVT&format=png&color=000000");
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

    //update experience and level user
    public static SV_CheckUpdate updateExperienceAndLevelUser(int idUser) {
        Connection connection = DatabaseConnect.getConnect();

        SV_CheckUpdate checkPerform = null;
        try {
            String querySQL = "UPDATE `user` \n" +
                    "SET `experience` = `experience` + 5, \n" +
                    "    `level` = CASE \n" +
                    "                WHEN `experience` + 5 >= 500 THEN 'Trúc cơ'\n" +
                    "                WHEN `experience` + 5 >= 5000 THEN 'Kim đan'\n" +
                    "                WHEN experience + 5 >= 10000 THEN 'Nguyên anh'\n" +
                    "                WHEN experience + 5 >= 10000 THEN 'Hóa thần'\n" +
                    "                WHEN experience + 5 >= 20000 THEN 'Luyện hư'\n" +
                    "                 WHEN experience + 5 >= 50000 THEN 'Hợp thể'\n" +
                    "                 WHEN experience + 5 >= 70000 THEN 'Đại thừa'\n" +
                    "                 WHEN experience + 5 >= 100000 THEN 'Độ kiếp'\n" +
                    "                 WHEN experience + 5 >= 120000 THEN 'Ngụy tiên'\n" +
                    "                 WHEN experience + 5 >= 150000 THEN 'Tán tiên'\n" +
                    "                 WHEN experience + 5 >= 120000 THEN 'tiên nhân'\n" +
                    "                 WHEN experience + 5 >= 200000 THEN 'Đại tiên'\n" +
                    "                ELSE `level`\n" +
                    "              END \n" +
                    "WHERE `idUser` = ? \n";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setInt(1,idUser);

            checkPerform = new SV_CheckUpdate(st.executeUpdate());

            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ket qua cap nhat kinh nghiem va level nguoi doc: " + checkPerform);
        return checkPerform;
    }

}
