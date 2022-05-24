package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class addPatientController {
    private MainViewController mainViewController;

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField NachnameText, VornameText, VersicherungsnummerText, BehandlungsnummerText;
    @FXML
    private ComboBox<String> StationaerBox, KontaktdatenBox, DoktorBox, LaborBox, GeschlechtBox;

    public addPatientController(MainViewController mainViewController) throws SQLException {
        this.mainViewController =  mainViewController;
        datePicker = new DatePicker();
        populateGeschlechtBox();
        populateStationaerBox();
        populateKontaktdatenBox();
        populateDoktorBox();
        populateLaborBox();
    }

    public void populateGeschlechtBox() {
        GeschlechtBox.getItems().addAll("w","m");
    }

    public void populateStationaerBox() {
        StationaerBox.getItems().addAll("1","0");
    }

    public void populateKontaktdatenBox() throws SQLException {
        ArrayList<String> Kontaktdaten = mainViewController.getDb().returnForeigKeys("SELECT KontaktdatenID FROM Kontaktdaten;", "KontaktdatenID");
        for(String s : Kontaktdaten){
            KontaktdatenBox.getItems().add(s);
        }
    }

    public void populateDoktorBox() throws SQLException {
        ArrayList<String> Doktor = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Doktor;", "DoktorID");
        for(String s : Doktor){
            DoktorBox.getItems().add(s);
        }
    }

    public void populateLaborBox() throws SQLException {
        ArrayList<String> Labor = mainViewController.getDb().returnForeigKeys("SELECT LaborID FROM Labor;", "LaborID");
        for(String s : Labor){
            LaborBox.getItems().add(s);
        }
    }

    public String getDoktorID(String Doktor) throws SQLException {
        return mainViewController.getDb().returnID("SELECT DoktorID FROM Doktor WHERE Nachname='"+Doktor+"'","DoktorID");
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Patient (Nachname, Vorname, Versicherungsnummer, Behandlungsnummer, Geburtstag, Stationaer, KontaktdatenID, LaborID, DoktorID) VALUES ('"
                + NachnameText.getText()+"', '"+VornameText.getText()+"', '"+VersicherungsnummerText.getText()+"', '"+BehandlungsnummerText.getText()+"', '"+
                datePicker.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+"',"+StationaerBox.getSelectionModel().getSelectedItem()+", "+
                KontaktdatenBox.getSelectionModel().getSelectedItem()+", "+LaborBox.getSelectionModel().getSelectedItem()+", "+
                getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())+");";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
