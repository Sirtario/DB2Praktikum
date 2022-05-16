package Kiss.model;

import Kiss.dto.dtoAbteilung;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XmlExporterServiceTest {

    XmlExporterService underTest = new XmlExporterServiceImpl();


    @Test
    public void getXMLStringFromDatabaseTest() {
        String except = "";
        String actual = underTest.getXMLStringFromDatabase("SA","DbServer2!", "Abteilung");

        assertEquals(except, actual);
        System.out.println("actual: \n" + actual);
    }
}
