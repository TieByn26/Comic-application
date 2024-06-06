module Server {
    requires org.apache.commons.mail;
    requires java.sql;
    requires com.google.gson;
    requires mysql.connector.j;
    requires jdk.httpserver;


    opens Server.ObjectGson.GsonForClient to com.google.gson;
    opens Server.ObjectGson.GsonForServer to com.google.gson;
    

}