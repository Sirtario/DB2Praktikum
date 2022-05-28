package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class addDiagnoseController {
    protected MainViewController mainViewController;

    @FXML
    protected ComboBox<String> PatientBox;
    @FXML
    protected TextField BezeichnungText;

    public addDiagnoseController(MainViewController mainViewController) {
        this.mainViewController =  mainViewController;
    }

    @FXML
    public void initialize(){
        try {
            populatePatientBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populatePatientBox() throws SQLException {
        ArrayList<String> Patient = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Patient;", "Nachname");
        for(String s : Patient){
            PatientBox.getItems().add(s);
        }
    }

    public String getPatientID(String Patient) throws SQLException {
        return mainViewController.getDb().returnID("SELECT PatientenID FROM Patient WHERE Nachname='"+Patient+"';","PatientenID");
    }

    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Diagnose (PatientenID, DiagnoseBezeichnung) VALUES ("+
                getPatientID(PatientBox.getSelectionModel().getSelectedItem())+", '"+BezeichnungText.getText()+"');";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }

}
