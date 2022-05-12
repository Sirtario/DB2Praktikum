package Kiss.model;

import Kiss.dto.dtoAbteilung;
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
    public <T> void xmlFileFromObject (T dto) {

        JAXBContext jaxbContext = null;

        try {
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{dtoAbteilung.class}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(dto, new File("/ausgabe.xml"));

        } catch (JAXBException jaxbExc) {
            jaxbExc.printStackTrace();
        } catch ( Exception e) {

        }
    }


}
