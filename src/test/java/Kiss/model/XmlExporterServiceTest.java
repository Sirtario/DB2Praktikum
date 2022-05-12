package Kiss.model;

import Kiss.dto.dtoAbteilung;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class XmlExporterServiceTest {

    private static final String EXPECTED_STRING_OBJECT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<testDummy>\n" +
            "    <bescreibung>Das ist ein Test</bescreibung>\n" +
            "    <name>TestDummy</name>\n" +
            "</testDummy>\n";



    XmlExporterService underTest = new XmlExporterServiceImpl();
    @Test
   public void xmlStringFromObjectTest() {
        String actual = underTest.xmlStringFromObject(createTestDummy());
        assertEquals(EXPECTED_STRING_OBJECT, actual);

   }

   @Test
   public void xmlFileFromObjectTest() {
        underTest.xmlFileFromObject(createTestDummy());
       Path fileName = Path.of("/ausgabe.xml");
       String actual = new String();
       try {
           actual = Files.readString(fileName);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       assertEquals(EXPECTED_STRING_OBJECT, actual);
   }

   private TestDummy createTestDummy() {
        return new TestDummy("TestDummy","Das ist ein Test");
    }

    @XmlRootElement
   private static class TestDummy {
        private String name;
        private String bescreibung;

        public TestDummy() {

        }

        public TestDummy(String name, String bescreibung) {
            this.name = name;
            this.bescreibung = bescreibung;
        }


        public String getName() {
            return name;
        }
        @XmlElement
        public void setName(String name) {
            this.name = name;
        }

        public String getBescreibung() {
            return bescreibung;
        }
        @XmlElement
        public void setBescreibung(String bescreibung) {
            this.bescreibung = bescreibung;
        }
    }
}
