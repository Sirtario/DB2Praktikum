package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class addRechnungController {
    private MainViewController mainViewController;

    @FXML
    private TextField AnzTageText;
    @FXML
    private ComboBox<String> LaborBox, RaumBox, DoktorBox, PatientBox;

    public addRechnungController(MainViewController mainViewController) throws SQLException {
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
        return mainViewController.getDb().returnID("SELECT PatientenID FROM Patient WHERE Nachname='"+Patient+"'","PatientenID");
    }

    public void populateDoktorBox() throws SQLException {
        ArrayList<String> Doktor = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Doktor;", "Nachname");
        for(String s : Doktor){
            DoktorBox.getItems().add(s);
        }
    }

    public String getDoktorID(String Doktor) throws SQLException {
        return mainViewController.getDb().returnID("SELECT DoktorID FROM Doktor WHERE Nachname='"+Doktor+"'","DoktorID");
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
    private void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Rechnung (AnzahlTage, LaborID, RaumID, DoktorID, PatientID) VALUES ('"+
                AnzTageText.getText()+"',"+LaborBox.getSelectionModel().getSelectedItem()+", "+RaumBox.getSelectionModel().getSelectedItem()+", "+
                getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())+", "+
                getPatientID(PatientBox.getSelectionModel().getSelectedItem())+");";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
