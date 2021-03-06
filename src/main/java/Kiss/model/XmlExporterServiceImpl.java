package Kiss.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.*;

public class XmlExporterServiceImpl implements XmlExporterService{


    @Override
    public String createXMLStringFromDatabase(Connection con, String tableName) {

        String result = new String();

        try{
            //Vorbereitung des DOM-Dokuments
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element results = doc.createElement("Results");
            doc.appendChild(results);

            // Statement mit Benennung der Tablle
            String query = "SELECT * FROM " + tableName + ";";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            //Zählen der Zeilen
            int columns = rs.getMetaData().getColumnCount();

            //Zeile für Zeile iterieren
            while (rs.next()) {
                Element row = doc.createElement("Row");
                results.appendChild(row);
                for (int i = 1; i <= columns; i++) {
                    String columnName = rsmd.getColumnName(i);
                    Object value = rs.getObject(i);
                    Element node = doc.createElement(columnName);
                    node.appendChild(doc.createTextNode(value.toString()));
                    row.appendChild(node);
                }
            }

            //Erstellen von DOM Datei
            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);

            //Schreiben der XML Datei auf ein String
            result = sw.toString();

            rs.close();
            stmt.close();

        } catch (SQLException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void createXMLFileFromDatabase(Connection con, String tableName) {
        String currentPath = System.getProperty("user.dir");
        String filepath = currentPath + tableName +".xml";
        File xmlFile = new File(filepath);

        String xmlData = createXMLStringFromDatabase(con, tableName);

        try(FileOutputStream fos = new FileOutputStream(xmlFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            //Konvertierung String zu Byte Array
            byte[] bytes = xmlData.getBytes();

            //Schreiben von Byte Array auf Datei
            bos.write(bytes);

            bos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
