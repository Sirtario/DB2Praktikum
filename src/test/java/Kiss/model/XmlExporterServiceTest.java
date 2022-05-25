package Kiss.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class XmlExporterServiceTest {

    private static final String EXCEPT = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>\n" +
            "<Results>\n" +
            "    <Row>\n" +
            "        <ID>0</ID>\n" +
            "        <NAME>Eins</NAME>\n" +
            "        <BESCHREIBUNG>Das ist Test 1</BESCHREIBUNG>\n" +
            "    </Row>\n" +
            "    <Row>\n" +
            "        <ID>1</ID>\n" +
            "        <NAME>Zwei</NAME>\n" +
            "        <BESCHREIBUNG>Das ist Test 2</BESCHREIBUNG>\n" +
            "    </Row>\n" +
            "    <Row>\n" +
            "        <ID>2</ID>\n" +
            "        <NAME>Drei</NAME>\n" +
            "        <BESCHREIBUNG>Das ist Test 3</BESCHREIBUNG>\n" +
            "    </Row>\n" +
            "</Results>\n";
    XmlExporterService underTest = new XmlExporterServiceImpl();

    Connection con;

    @BeforeEach
    public void setup() {
        try {
            con = DatabaseConnector.ConnectToOtherDB("SA","DbServer2!","TestDB");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void after(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getXMLStringFromDatabaseTest() {
        String actual = underTest.createXMLStringFromDatabase(con , "TestTable");
        assertEquals(EXCEPT, actual);
    }

    @Test
    public void createXMLFileFromDatabaseTest() {
        String actual = new String();
        Path fileName = Path.of("output_TestTable.xml");
        underTest.createXMLFileFromDatabase(con, "TestTable");

        try {
            actual = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(EXCEPT, actual);

        System.out.println(fileName.getParent());
    }
}
