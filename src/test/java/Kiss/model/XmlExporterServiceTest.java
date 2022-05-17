package Kiss.model;

import Kiss.dto.dtoAbteilung;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class XmlExporterServiceTest {

    XmlExporterService underTest = new XmlExporterServiceImpl();


    @Test
    public void getXMLStringFromDatabaseTest() {
        String except = "";
        Connection con = null;

        try {
            con = DatabaseConnector.ConnectToOtherDB("SA","DbServer2!","TESTDB");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String actual = underTest.getXMLStringFromDatabase(con , "TestTable");

        assertEquals(except, actual);
        System.out.println("actual: \n" + actual);
    }
}
