package Kiss.model;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public interface XmlExporterService {
     /**
      *
      * @param xmlDto
      * @return
      * @param <T>
      */
    <T> String xmlStringFromObject(T xmlDto);
    <T> String xmlStringFromList(List<T> xmlDtoList);

     /**
      *
      * @param xmlDto
      * @param <T>
      */
    <T> void xmlFileFromObject(T xmlDto);

    <T> void xmlFileFromList(List<T> xmlDtoList);
}
