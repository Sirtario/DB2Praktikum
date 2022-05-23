package Kiss.controller;

import Kiss.Datenbank;
import Kiss.model.XmlExporterService;
import Kiss.model.XmlExporterServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import java.sql.Connection;

public class MainViewController {

    Connection dbConnection;

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @FXML
    MenuItem exportMenuButton;

    @FXML
    private void onExportXML(){
        XmlExporterService xmlExporterService = new XmlExporterServiceImpl();

        xmlExporterService.createXMLFileFromDatabase(dbConnection,"Abteilung");
    }
}
