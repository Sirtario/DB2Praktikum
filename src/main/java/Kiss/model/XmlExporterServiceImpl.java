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
import java.io.StringWriter;


public class XmlExporterServiceImpl implements XmlExporterService{



    @Override
    public <T> String xmlStringFromObject (T dto) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(dto.getClass());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(dto, sw);

            return sw.toString();

        } catch (JAXBException jaxbExc) {
            jaxbExc.printStackTrace();
        } catch ( Exception e) {

        }
        return new String();
    }


}
