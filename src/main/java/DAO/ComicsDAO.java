package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import Server.ObjectGson.GsonForServer.SV_ListComicsInformations;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComicsDAO {
    public static SV_ListComicsInformations selectALL() {
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_ListComicsInformations listComics = new SV_ListComicsInformations();
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `nameComics`, `avatarComcis`, `numberOfChapter` FROM `comicsinformation`";
            PreparedStatement st = connection.prepareStatement(querySQL);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            while (rs.next()) {
                String name = rs.getString(1);
                String avtComics = rs.getString(2);
                int numberOfChapter = rs.getInt(3);

                // tao doi tuong nhan du lieu tu server
                SV_ComicsInformation newInf = new SV_ComicsInformation(name, avtComics, numberOfChapter);

                //nhet cac doi tuong vao 1 arraylist cua doi tuong SV_ListComicsInformations
                listComics.getListComicsInfomations().add(newInf);
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listComics;
    }

    public static SV_ListComicsInformations () {
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_ListComicsInformations listComics = new SV_ListComicsInformations();
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `nameComics`, `avatarComcis`, `numberOfChapter` FROM `comicsinformation`";
            PreparedStatement st = connection.prepareStatement(querySQL);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            while (rs.next()) {
                String name = rs.getString(1);
                String avtComics = rs.getString(2);
                int numberOfChapter = rs.getInt(3);

                // tao doi tuong nhan du lieu tu server
                SV_ComicsInformation newInf = new SV_ComicsInformation(name, avtComics, numberOfChapter);

                //nhet cac doi tuong vao 1 arraylist cua doi tuong SV_ListComicsInformations
                listComics.getListComicsInfomations().add(newInf);
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listComics;
    }


}
