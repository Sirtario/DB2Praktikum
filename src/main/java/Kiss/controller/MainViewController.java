package Kiss.controller;

import Kiss.Datenbank;
import Kiss.model.XmlExporterService;
import Kiss.model.XmlExporterServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainViewController {
    @FXML
    private Button addButton;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab Abteilung, Bett, Diagnose, Doktor, Kontaktdaten, Labor, Patient, Rechnung, Raum;
    @FXML
    private TableView AbteilungTable, BettTable, DiagnoseTable, DoktorTable, KontaktdatenTable, LaborTable, PatientTable, RechnungTable, RaumTable;

    private XmlExporterServiceImpl xmlExporterServiceImpl;
    private Datenbank db;

    @FXML
    private void onExportXML() {
        XmlExporterService xmlExporterService = new XmlExporterServiceImpl();

        xmlExporterService.createXMLFileFromDatabase(db.getConnection(), "Abteilung");
    }

    @FXML
    private void onDeleteButtonClick(){
        String table = tabPane.getSelectionModel().getSelectedItem().toString();
        switch(table){
            case "Abteilung": db.deleteEntry("Abteilung", "AbteilungsID", //TODO: Hier muss dann jeweils der Wert der ersten Zelle der ausgewählten Zeile rein(also halt die ID));
            case "Bett": db.deleteEntry("Bett","BettID",);
            case "Diagnose": db.deleteEntry("Diagnose","DiagnoseID",);
            case "Doktor": db.deleteEntry("Doktor","DoktorID",);
            case "Kontaktdaten": db.deleteEntry("Kontaktdaten","KontaktdatenID",);
            case "Labor": db.deleteEntry("Labor","LaborID",);
            case "Patient": db.deleteEntry("Patient","PatientID",);
            case "Rechnung": db.deleteEntry("Rechnung","RechnungID",);
            case "Raum": db.deleteEntry("Raum","RaumID",);

        }
    }
}
