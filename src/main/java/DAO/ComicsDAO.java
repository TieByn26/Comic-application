package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.SV_ComicsInformation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ComicsDAO {
    public static ArrayList<SV_ComicsInformation> selectALL() {
        Connection connection =DatabaseConnect.getConnect();
        ArrayList<SV_ComicsInformation> listComics = new ArrayList<>();


        try {
            // tao ra cau query sql
            String querySQL = "SELECT `nameComics`, `avatarComcis`, `numberOfChapter` FROM `comicsinformation`";
            PreparedStatement st = connection.prepareStatement(querySQL);

            ResultSet rs = st.executeQuery();

            // tao ra tung doi tuong Room de nhan du lieu tuong ung tu csdl truyen ve

            while (rs.next()) {
                String name = rs.getString(1);
                String avtComics = rs.getString(2);
                int numberOfChapter = rs.getInt(3);

                SV_ComicsInformation newInf = new SV_ComicsInformation(name, avtComics, numberOfChapter);

                listComics.add(newInf);
            }


            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        for (SV_ComicsInformation ls : listComics ){
            System.out.println(ls);
        }

        return listComics;
    }

}
