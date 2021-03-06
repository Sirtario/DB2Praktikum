package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class addRechnungController {
    protected MainViewController mainViewController;

    @FXML
    protected TextField anzTageText;
    @FXML
    protected ComboBox<String> LaborBox, RaumBox, DoktorBox, PatientBox;

    public addRechnungController(MainViewController mainViewController) {
        this.mainViewController =  mainViewController;
    }

    @FXML
    public void initialize(){
        try {
            populatePatientenBox();
            populateDoktorBox();
            populateLaborBox();
            populateRaumBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populatePatientenBox() throws SQLException {
        ArrayList<String> Patient = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Patient;", "Nachname");
        for(String s : Patient){
            PatientBox.getItems().add(s);
        }
    }

    public String getPatientID(String Patient) throws SQLException {
        return mainViewController.getDb().returnID("SELECT PatientenID FROM Patient WHERE Nachname='"+Patient+"';","PatientenID");
    }

    public void populateDoktorBox() throws SQLException {
        ArrayList<String> Doktor = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Doktor;", "Nachname");
        for(String s : Doktor){
            DoktorBox.getItems().add(s);
        }
    }

    public String getDoktorID(String Doktor) throws SQLException {
        return mainViewController.getDb().returnID("SELECT DoktorID FROM Doktor WHERE Nachname='"+Doktor+"';","DoktorID");
    }

    public void populateLaborBox() throws SQLException {
        ArrayList<String> Labor = mainViewController.getDb().returnForeigKeys("SELECT LaborID FROM Labor;", "LaborID");
        for(String s : Labor){
            LaborBox.getItems().add(s);
        }
    }

    public void populateRaumBox() throws SQLException {
        ArrayList<String> Raum = mainViewController.getDb().returnForeigKeys("SELECT RaumID FROM Raum;", "RaumID");
        for(String s : Raum){
            RaumBox.getItems().add(s);
        }
    }

    @FXML
    protected void onClickSaveEntry() throws SQLException {
        if (mainViewController.fieldIsFilled(anzTageText.getText()) &&
                mainViewController.fieldIsFilled(LaborBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(RaumBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(DoktorBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(PatientBox.getSelectionModel().getSelectedItem())) {
            String querry = "INSERT INTO Rechnung (AnzahlTage, LaborID, RaumID, DoktorID, PatientenID) VALUES (" +
                    anzTageText.getText() + "," + LaborBox.getSelectionModel().getSelectedItem() + ", " + RaumBox.getSelectionModel().getSelectedItem() + ", " +
                    getDoktorID(DoktorBox.getSelectionModel().getSelectedItem()) + ", " +
                    getPatientID(PatientBox.getSelectionModel().getSelectedItem()) + ");";
            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        } else {
            mainViewController.showAlert(Alert.AlertType.ERROR, "Rechnung");
        }
    }
    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
