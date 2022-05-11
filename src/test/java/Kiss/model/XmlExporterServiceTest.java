package Kiss.model;

import Kiss.dto.dtoAbteilung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class XmlExporterServiceTest {

    XmlExporterService underTest;

    @BeforeEach
    public void setup() {
        underTest = new XmlExporterServiceImpl();
    }


    @Test
   public void xmlFileFromObjectTest() {
    underTest.xmlFileFromObject(createAbteilung());


   }

   private dtoAbteilung createAbteilung() {
       dtoAbteilung dto = new dtoAbteilung(0, "Beste Abteilung der Welt","Testland", "Testabteilung");

       return dto;
   }
}
