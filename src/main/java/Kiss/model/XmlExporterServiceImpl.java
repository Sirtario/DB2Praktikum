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
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

class XmlExporterServiceImpl implements XmlExporterService{

    @Override
    public <T> String xmlStringFromObject (T xmlDto) {

        try {
            Marshaller jaxbMarshaller = xmlCreater(xmlDto);
            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(xmlDto, sw);

            return sw.toString();
        } catch (JAXBException jaxbExc) {
            jaxbExc.printStackTrace();
        } catch ( Exception e) {
            e.getMessage();
        }
        return new String();
    }

     @Override
    public <T> void xmlFileFromObject(T xmlDto) {
         try {
             Marshaller jaxbMarshaller = xmlCreater(xmlDto);
             jaxbMarshaller.marshal(xmlDto, new File("/ausgabe.xml"));

         } catch (JAXBException jaxbExc) {
             jaxbExc.printStackTrace();
         } catch ( Exception e) {
             e.getMessage();
         }
    }

    @Override
    public <T> String xmlStringFromList(List<T> xmlDtoList) {
        return null;
    }

    @Override
    public <T> void xmlFileFromList(List<T> xmlDtoList){

    }

    private <T> Marshaller xmlCreater(T xmlDto) throws JAXBException {

         JAXBContext jaxbContext = JAXBContext.newInstance(xmlDto.getClass());
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

         return jaxbMarshaller;
    }


}
