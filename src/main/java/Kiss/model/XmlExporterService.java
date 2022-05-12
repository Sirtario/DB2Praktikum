package Kiss.model;

import javax.xml.parsers.ParserConfigurationException;

public interface XmlExporterService {
     public <T> String xmlStringFromObject(T dto);
}
