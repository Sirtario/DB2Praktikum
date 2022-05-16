import Kiss.Datenbank;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatenbankTest {
    private Datenbank db;

    @BeforeEach
    void setup(){
        Connection con = null;
        String connectionUrl =
                "jdbc:sqlserver://68.183.223.224;"
                        + "database=KIS;"
                        + "user=SA;"
                        + "password=DbServer2!;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";


        try{
            con = DriverManager.getConnection(connectionUrl);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db = new Datenbank(con);
    }

    @Test
    void TestCreateEntry() throws SQLException {
        String table= "Abteilung";
        String[] keys = {"AbteilungsBeschreibung","AbteilungsStandort","AbteilungsName"};
        String[] values = {"Baum","Standort","Name"};
        db.createEntry(table, keys, values);
    }

}
