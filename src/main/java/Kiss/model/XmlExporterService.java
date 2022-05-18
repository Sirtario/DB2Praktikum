package Kiss.model;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.Connection;
import java.util.List;

public interface XmlExporterService {

    /**
     * Creates a string with an XML file as content. The content is the information of a database table.
     * @param con Database connection
     * @param tableName Database table
     * @return
     */
    String createXMLStringFromDatabase(Connection con, String tableName);

    /**
     * Creates a file. The content is the information of a database table.
     * @param con Database connection
     * @param tableName Database table
     */
    void createXMLFileFromDatabase(Connection con, String tableName);

}
