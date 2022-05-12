package Kiss.model;

import Kiss.dto.dtoAbteilung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlExporterServiceTest {

    XmlExporterService underTest = new XmlExporterServiceImpl();

    @BeforeEach
    public void setup() {

    }


    @Test
   public void xmlFileFromObjectTest() {

        String expect = "";
        String actual = new String();
        underTest.xmlFileFromObject(createAbteilung());

        try{
            Path fileName = Path.of("/ausgabe.xml");
            actual = Files.readString(fileName);
        } catch (IOException ioe) {
            ioe.getMessage();
        }

        assertEquals(expect, actual);

   }

   private dtoAbteilung createAbteilung() {
       dtoAbteilung dto = new dtoAbteilung(0, "Beste Abteilung der Welt","Testland", "Testabteilung");

       return dto;
   }
}
