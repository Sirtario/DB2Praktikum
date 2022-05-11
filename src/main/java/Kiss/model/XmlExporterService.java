package Kiss.model;

import javax.xml.parsers.ParserConfigurationException;

public interface XmlExporterService {
    public <T> void xmlFileFromObject(T dto);
}
