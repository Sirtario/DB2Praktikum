package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class addPatientController {
    protected MainViewController mainViewController;

    @FXML
    protected DatePicker datePicker;
    @FXML
    protected TextField NachnameText, VornameText, VersicherungsnummerText, BehandlungsnummerText;
    @FXML
    protected ComboBox<String> StationaerBox, KontaktdatenBox, DoktorBox, LaborBox, GeschlechtBox;

    public addPatientController(MainViewController mainViewController) {
        this.mainViewController =  mainViewController;
    }

    @FXML
    public void initialize(){
        try {
            populateGeschlechtBox();
            populateStationaerBox();
            populateKontaktdatenBox();
            populateDoktorBox();
            populateLaborBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        ArrayList<String> Doktor = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Doktor;", "Nachname");
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
        return mainViewController.getDb().returnID("SELECT DoktorID FROM Doktor WHERE Nachname='"+Doktor+"';","DoktorID");
    }

    @FXML
    protected void onClickSaveEntry() throws SQLException {
        if(datePicker.getValue() != null &&
                mainViewController.fieldIsFilled(NachnameText.getText()) &&
                mainViewController.fieldIsFilled(VornameText.getText()) &&
                mainViewController.fieldIsFilled(VersicherungsnummerText.getText()) &&
                mainViewController.fieldIsFilled(BehandlungsnummerText.getText()) &&
                mainViewController.fieldIsFilled(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) &&
                mainViewController.fieldIsFilled(StationaerBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(KontaktdatenBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(LaborBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(DoktorBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(GeschlechtBox.getSelectionModel().getSelectedItem())
                ) {
            String querry = "INSERT INTO Patient (Nachname, Vorname, Versicherungsnummer, Behandlungsnummer, Geburtstag, Stationaer, KontaktdatenID, LaborID, DoktorID, Geschlecht) VALUES ('"
                    + NachnameText.getText()+"', '"+VornameText.getText()+"', '"+VersicherungsnummerText.getText()+"', '"+BehandlungsnummerText.getText()+"', '"+
                    datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"',"+StationaerBox.getSelectionModel().getSelectedItem()+", "+
                    KontaktdatenBox.getSelectionModel().getSelectedItem()+", "+LaborBox.getSelectionModel().getSelectedItem()+", "+
                    getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())+", '"+GeschlechtBox.getSelectionModel().getSelectedItem()+"');";

            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        } else {
            mainViewController.showAlert(Alert.AlertType.ERROR,"Patient");
        }
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
