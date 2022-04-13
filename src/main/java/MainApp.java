import java.sql.*;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        ConnectToDB();
    }

    private static void ConnectToDB() {
        String connectionUrl =
                "jdbc:sqlserver://68.183.223.224;"
                        + "database=KIS;"
                        + "user=SA;"
                        + "password=DbServer2!;"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";


        try{
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM abteilung");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
