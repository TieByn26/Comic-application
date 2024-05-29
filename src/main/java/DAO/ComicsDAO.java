package org.Connect.DAO;

import org.Connect.DatabaseConnect;
import org.Connect.Server.ObjectGson.GsonForServer.SV_ComicsInformation;
import org.Connect.Server.ObjectGson.GsonForServer.SV_ListComicsInformations;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComicsDAO {
    public static SV_ListComicsInformations selectALL() {
        Connection connection =DatabaseConnect.getConnect();
        SV_ListComicsInformations listComics = new SV_ListComicsInformations();


        try {
            // tao ra cau query sql
            String querySQL = "SELECT `nameComics`, `avatarComcis`, `numberOfChapter` FROM `comicsinformation`";
            PreparedStatement st = connection.prepareStatement(querySQL);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                String avtComics = rs.getString(2);
                int numberOfChapter = rs.getInt(3);

                SV_ComicsInformation newInf = new SV_ComicsInformation(name, avtComics, numberOfChapter);

                listComics.getListComicsInfomations().add(newInf);
            }


            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        for (SV_ComicsInformation ls : listComics.getListComicsInfomations() ){
            System.out.println(ls);
        }

        return listComics;
    }

}
