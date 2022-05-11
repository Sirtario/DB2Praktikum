package Kiss.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;


public class XmlExporterServiceImpl implements XmlExporterService{



    @Override
    public <T> void  xmlFileFromObject(T dto) {
        Document doc = createDomFile(dto);
    }

    private <T> Document createDomFile (T dto) {

        JAXBContext jaxbContext = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // Normal JAXB RI
            //jaxbContext = JAXBContext.newInstance(Fruit.class);

            // EclipseLink MOXy needs jaxb.properties at the same package with Fruit.class
            // Alternative, I prefer define this via eclipse JAXBContextFactory manually.
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{dto.getClass()}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // output to a xml file
            jaxbMarshaller.marshal(dto, doc);

            // output to console
            // jaxbMarshaller.marshal(o, System.out);

            return doc;

        } catch (JAXBException jaxbExc) {
            jaxbExc.printStackTrace();
        } catch (ParserConfigurationException parExc) {

        } catch ( Exception e) {

        }

        return null;
    }


}
