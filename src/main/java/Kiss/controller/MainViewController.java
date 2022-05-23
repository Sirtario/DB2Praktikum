package Kiss.controller;

import Kiss.Datenbank;
import Kiss.controller.add.addAbteilungController;
import Kiss.model.XmlExporterService;
import Kiss.model.XmlExporterServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    @FXML
    private Button exportButton;
    @FXML
    private Button reloadButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab Abteilung, Bett, Diagnose, Doktor, Kontaktdaten, Labor, Patient, Rechnung, Raum;
    @FXML
    private TableView AbteilungTable, BettTable, DiagnoseTable, DoktorTable, KontaktdatenTable, LaborTable, PatientTable, RechnungTable, RaumTable;

    private Stage popupStage;

    private XmlExporterServiceImpl xmlExporterServiceImpl;
    private Datenbank db;

    public void setDatenbank(Datenbank db){
        this.db = db;
    }

    /*
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
*/

    public Datenbank getDb(){
        return db;
    }

    public Stage getPopupStage(){
        return popupStage;
    }

    @FXML
    private void onAddButtonClick(){
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        if(tab.equals(Abteilung)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addAbteilung.fxml"));
            loader.setControllerFactory(addAbteilungController -> new addAbteilungController(this));

            try {
                AnchorPane rootElement = loader.load();

                Stage stage = new Stage();
                Scene scene = new Scene(rootElement);
                popupStage = stage;

                stage.setScene(scene);
                stage.sizeToScene();
                stage.showAndWait();

            } catch(IOException e) {
                e.printStackTrace();
            };
        } else if(tab.equals(Bett)){

        }
    }
}
