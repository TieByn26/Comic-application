package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
    private static String jdbcUri = "jdbc:mySQL://localhost:3306/doancoso1?";
    private static String username = "root";
    private static String password ="";
    public DatabaseConnect(){

    }

    public static Connection getConnect(){
        Connection connection = null;
        try{
            //dang ky driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            //tao ket noi
            connection = DriverManager.getConnection(jdbcUri,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnect(Connection connection){
        try {
            if (connection != null){
                //ngat ket noi
                connection.close();
                System.out.printf("Ngắt kết nối thành công!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
