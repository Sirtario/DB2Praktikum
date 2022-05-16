package Kiss.model;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public interface XmlExporterService {

    String getXMLStringFromDatabase(String name, String password, String tableName);

}
