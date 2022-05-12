package Kiss.model;

import Kiss.dto.dtoAbteilung;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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

        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<testDummy>\n" +
                "    <bescreibung>Das ist ein Test</bescreibung>\n" +
                "    <name>TestDummy</name>\n" +
                "</testDummy>\n";
        String actual = underTest.xmlStringFromObject(createTestDummy());

        assertEquals(expect, actual);

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
