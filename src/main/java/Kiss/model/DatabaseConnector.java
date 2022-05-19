package Kiss.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection ConnectToDB(String user, String pass) throws SQLException {

            Connection con = DriverManager.getConnection(buildConnectionUrl(user, pass, "KIS"));
            return con;
    }

    public static Connection ConnectToOtherDB(String user, String pass, String database) throws SQLException {

        Connection con = DriverManager.getConnection(buildConnectionUrl(user, pass, database));
        return con;
    }



    private static String buildConnectionUrl(String user, String pass, String database) {
        String connectionUrl =
                "jdbc:sqlserver://68.183.223.224;"
                        + "database="+database+";"
                        + "user="+user+";"
                        + "password="+pass+";"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";

        return connectionUrl;
    }
}
