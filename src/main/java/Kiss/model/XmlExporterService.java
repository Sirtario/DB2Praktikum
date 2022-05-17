package Kiss.model;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.Connection;
import java.util.List;

public interface XmlExporterService {

    String getXMLStringFromDatabase(Connection con, String tableName);

}
