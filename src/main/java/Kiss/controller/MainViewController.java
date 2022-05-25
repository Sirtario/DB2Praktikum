package Kiss.controller;

import Kiss.Datenbank;
import Kiss.controller.add.*;
import Kiss.model.XmlExporterService;
import Kiss.model.XmlExporterServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jdk.jshell.Diag;

import java.io.IOException;
import java.sql.SQLException;

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

    public Datenbank getDb(){
        return db;
    }

    public Stage getPopupStage(){
        return popupStage;
    }


    @FXML
    private void onDeleteButtonClick() throws SQLException {
        /*
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        String querry = "";
        if(tab.equals(Abteilung)){
            querry = "DELETE FROM Abteilung WHERE AbteilungsID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Bett)){
            querry = "DELETE FROM Bett WHERE BettID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Diagnose)){
            querry = "DELETE FROM Diagnose WHERE DiagnoseID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Doktor)){
            querry = "DELETE FROM Doktor WHERE DoktorID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Kontaktdaten)){
            querry = "DELETE FROM Kontaktdaten WHERE KontkatdatenID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Labor)){
            querry = "DELETE FROM Labor WHERE LaborID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Patient)){
            querry = "DELETE FROM Patient WHERE PatientID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Raum)){
            querry = "DELETE FROM Raum WHERE RaumID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Rechnung)){
            querry = "DELETE FROM Rechnung WHERE RechnungsID = "++";"; //TODO: ID des ausgewählten Elements
        }
        db.runQuerry(querry);

     */
    }

    @FXML
    private void onAddButtonClick() throws SQLException{
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        if(tab.equals(Abteilung)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addAbteilung.fxml"));
            loader.setControllerFactory(addAbteilungController -> new addAbteilungController(this));
            openPopup(loader);
        } else if(tab.equals(Bett)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addBett.fxml"));
            loader.setControllerFactory(addBettController -> new addBettController(this));
            openPopup(loader);
        } else if(tab.equals(Diagnose)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addDiagnose.fxml"));
            loader.setControllerFactory(addDiagnoseController -> new addDiagnoseController(this));
            openPopup(loader);
        } else if(tab.equals(Doktor)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addDoktor.fxml"));
            loader.setControllerFactory(addDoktorController -> new addDoktorController(this));
            openPopup(loader);
        } else if(tab.equals(Kontaktdaten)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addKontaktdaten.fxml"));
            loader.setControllerFactory(addKontaktdatenController -> new addKontaktdatenController(this));
            openPopup(loader);
        } else if(tab.equals(Labor)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addLabor.fxml"));
            loader.setControllerFactory(addLaborController -> {
                try {
                    return new addLaborController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        } else if(tab.equals(Patient)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addPatient.fxml"));
            loader.setControllerFactory(addPatientController -> {
                try {
                    return new addPatientController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        } else if(tab.equals(Raum)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRaum.fxml"));
            loader.setControllerFactory(addRaumController -> {
                try {
                    return new addRaumController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        } else if(tab.equals(Rechnung)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRechnung.fxml"));
            loader.setControllerFactory(addRechnungController -> {
                try {
                    return new addRechnungController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        }
    }

    private void openPopup(FXMLLoader loader){
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
    }

    @FXML
    private void onReloadButtonClick(){

    }

    @FXML
    private void onEditButtonClick(){

    }

    @FXML
    private void onExportButtonClick(){

    }
}
