package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_Comments;
import Server.ObjectGson.GsonForServer.SV_listComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDAO {
    public static SV_listComment selectAllCommentByIdComics(String idComics) {  // ham tra ve tat ca comment theo truyen
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database va tra ve client
        SV_listComment allComment = null;
        //tao mang de luu tat ca cac comment
        ArrayList<SV_Comments> listComment = new ArrayList<>();
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idUser`,  `comments`, `like`, `dislike`,`idComment` FROM `comments` WHERE idComics = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,idComics);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            while(rs.next()) {
                int idUser = rs.getInt(1);
                String comment = rs.getString(2);
                int like = rs.getInt(3);
                int dislike = rs.getInt(4);
                String idComment = rs.getString(5);

                SV_Comments comments  = new SV_Comments(idUser,comment,like,dislike,idComment);
                listComment.add(comments);
            }
            // khoi tao doi tuong va nhet list comment vao mang de tra ve client
            allComment = new SV_listComment(listComment);

            // dong connect database
            DatabaseConnect.closeConnect(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + allComment);

        return allComment;
    }
    public static SV_CheckUpdate updateNumberOfLike(String idComment, int numberOfLike) {  // ham cap nhat lai so luot like
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        int checkPerform = 0;
        try {
            // tao ra cau query sql
            String querySQL = "UPDATE `comments` SET `like`= ? WHERE idComment = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,numberOfLike);
            st.setString(2,idComment);
            // thuc thi cau query
            checkPerform = st.executeUpdate();
            statusUpdate = new SV_CheckUpdate(checkPerform);
            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + checkPerform);
        return statusUpdate;
    }

    public static SV_CheckUpdate updateNumberOfDislike(String idComment, int numberOfDislike) {  // ham cap nhat lai so luot dislike
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        int checkPerform = 0;
        try {
            // tao ra cau query sql
            String querySQL = "UPDATE `comments` SET  `dislike` = ? WHERE idComment = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,numberOfDislike);
            st.setString(2,idComment);
            // thuc thi cau query
            checkPerform = st.executeUpdate();
            statusUpdate = new SV_CheckUpdate(checkPerform);
            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + checkPerform);
        return statusUpdate;
    }

    public static SV_CheckUpdate createNewComment( int idUser, String comment,String idComics) {  // ham cap nhat lai so luot dislike
        Connection connection = DatabaseConnect.getConnect();
        SV_CheckUpdate statusUpdate = null;
        int checkPerform = 0;
        try {
            // tao ra cau query sql
            String querySQL = "INSERT INTO `comments`( `idUser`, `idComics`, `comments`) VALUES (?,?,?)";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setInt(1,idUser);
            st.setString(2,idComics);
            st.setString(3,comment);
            // thuc thi cau query
            checkPerform = st.executeUpdate();
            statusUpdate = new SV_CheckUpdate(checkPerform);
            // dong connect database
            DatabaseConnect.closeConnect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + checkPerform);
        return statusUpdate;
    }
}
